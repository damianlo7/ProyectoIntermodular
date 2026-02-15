package com.example.chemin;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.chemin.databinding.ActivityGestionPublicacionBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class gestionPublicacion extends AppCompatActivity {

    private ActivityGestionPublicacionBinding binding;
    private Api api;
    private ImageButton btnPublicacion;

    private static final int REQUEST_IMAGE = 101;
    private static final int PERMISO_IMAGEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGestionPublicacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        api = new Api();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        ).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_gestion_publicacion);
        NavigationUI.setupWithNavController(binding.navView, navController);

        btnPublicacion = findViewById(R.id.enviarImagen);
        btnPublicacion.setOnClickListener(v -> {
            if (verificarPermisos()) {
                abrirGaleria();
            }
        });
    }

    private boolean verificarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_MEDIA_IMAGES}, PERMISO_IMAGEN);
                return false;
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISO_IMAGEN);
                return false;
            }
        }
        return true;
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), REQUEST_IMAGE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO_IMAGEN && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            abrirGaleria();
        } else {
            Toast.makeText(this, "Permiso denegado para acceder a imágenes", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                subirImagenDesdeUri(imageUri);
            }
        }
    }

    private void subirImagenDesdeUri(Uri imageUri) {
        SharedPreferences prefs = getSharedPreferences("CHEMIN", MODE_PRIVATE);
        int idUsuario = prefs.getInt("id", -1);
        if (idUsuario == -1) {
            Toast.makeText(this, "Error: usuario no logueado", Toast.LENGTH_SHORT).show();
            return;
        }
        String nombreImagen = "img_" + idUsuario + "_" + System.currentTimeMillis() + ".jpg";
        api.subirImagen(imageUri, this, nombreImagen, idUsuario);
        Toast.makeText(this, "Subiendo imagen...", Toast.LENGTH_SHORT).show();
    }
}
