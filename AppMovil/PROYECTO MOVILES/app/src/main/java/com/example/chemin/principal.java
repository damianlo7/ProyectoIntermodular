package com.example.chemin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

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
        adaptador = new AdaptadorPublicaciones(lista);
        recyclerView.setAdapter(adaptador);

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
            startActivity(new Intent(this, editarPerfil.class));
        } else if (id == R.id.menu_anhadirpublicaciones){
            startActivity(new Intent(this, gestionPublicacion.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void cargarPublicaciones() {
        String url = "http://10.0.2.2:8080/tema5maven/rest/publicacion/todas";
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
                            String texto = obj.optString("nombre","");
                            String imagenBase64 = obj.optString("imagen","");
                            lista.add(new Publicaciones(texto, imagenBase64));
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
}
