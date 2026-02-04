package com.example.chemin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ajustesUsuario extends AppCompatActivity {

    private Button btnBtnEditarPerfil;
    private Button btnConfiguracion;
    private Button btnModoOscuro;
    private Button btnCerrarSesion;
    private Button btnEliminarCuenta;


    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajustes_usuario);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prefs = getSharedPreferences("ajustes_app", MODE_PRIVATE);

        btnBtnEditarPerfil = findViewById(R.id.btnEditarPerfil);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);
        btnModoOscuro = findViewById(R.id.btnModoOscuro);
        btnCerrarSesion = findViewById(R.id.btnCarrarSesion);
        btnEliminarCuenta = findViewById(R.id.btnEliminarCuenta);



        boolean modoOscuro = prefs.getBoolean("modo_oscuro", false);
        AppCompatDelegate.setDefaultNightMode(
                modoOscuro ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        btnModoOscuro.setOnClickListener(v -> {
            boolean actual = prefs.getBoolean("modo_oscuro", false);
            boolean nuevo = !actual;

            prefs.edit().putBoolean("modo_oscuro", nuevo).apply();

            AppCompatDelegate.setDefaultNightMode(
                    nuevo ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
        });

        btnBtnEditarPerfil.setOnClickListener(v ->{
            Intent intent = new Intent(this, editarPerfil.class);
            startActivity(intent);
        });
        btnConfiguracion.setOnClickListener(v ->{
            Intent intent = new Intent(this, principal.class);
            startActivity(intent);
        });
        btnModoOscuro.setOnClickListener(v ->{
            Intent intent = new Intent(this, principal.class);
            startActivity(intent);
        });
        btnCerrarSesion.setOnClickListener(v ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        btnEliminarCuenta.setOnClickListener(v ->{
            Intent intent = new Intent(this, principal.class);
            startActivity(intent);
        });



    }
}
