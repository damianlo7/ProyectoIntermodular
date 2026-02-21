package com.example.chemin;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.Manifest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONObject;

import java.util.ArrayList;

public class principal extends AppCompatActivity {

    ArrayList<Publicaciones> lista = new ArrayList<>();
    AdaptadorPublicaciones adaptador;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences prefs = getSharedPreferences("CHEMIN", MODE_PRIVATE);
        int idSesion = prefs.getInt("id", -1);

        adaptador = new AdaptadorPublicaciones(lista, idSesion);
        recyclerView.setAdapter(adaptador);

        cargarPublicaciones();
        pedirUbicacion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarPublicaciones();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusuperior, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_mensajes){
            startActivity(new Intent(this, ChatsActivity.class));
        } else if (id == R.id.menu_perfil){
            startActivity(new Intent(this, ajustesUsuario.class));
        } else if (id == R.id.menu_anhadirpublicaciones){
            startActivity(new Intent(this, gestionPublicacion.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void cargarPublicaciones() {
        SharedPreferences prefs = getSharedPreferences("CHEMIN", MODE_PRIVATE);
        int idUsuario = prefs.getInt("id", -1);

//        String url = "http://10.0.2.2:8080/tema5maven/rest/publicacion/lista/" + idUsuario;
        String url = "http://192.168.1.104:8080/tema5maven/rest/publicacion/lista/" + idUsuario;
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    lista.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            String imagenBase64 = obj.optString("imagen", "");
                            String textoRaw = obj.optString("texto", "");
                            String texto = textoRaw.equals("null") ? "" : textoRaw;
                            String username = obj.optString("username", "");
                            int idUsuarioPub = obj.optInt("idUsuario", -1);
                            int id = obj.optInt("id", -1);

                            Publicaciones p = new Publicaciones(texto, imagenBase64, username);
                            p.setIdUsuario(idUsuarioPub);
                            p.setId(id);
                            lista.add(p);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    adaptador.notifyDataSetChanged();
                },
                error -> error.printStackTrace()
        );

        queue.add(request);
    }
    private void pedirUbicacion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        } else {
            obtenerUbicacion();
        }
    }

    private void obtenerUbicacion() {
        FusedLocationProviderClient fusedClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) return;

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(1);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    SharedPreferences prefs = getSharedPreferences("CHEMIN", MODE_PRIVATE);
                    int idUsuario = prefs.getInt("id", -1);
                    Api api = new Api();
                    api.actualizarUbicacion(idUsuario, location.getLatitude(), location.getLongitude(), principal.this);
                }
            }
        };

        fusedClient.requestLocationUpdates(locationRequest, locationCallback, getMainLooper());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            obtenerUbicacion();
        }
    }
}