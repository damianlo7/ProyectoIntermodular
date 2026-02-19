package com.example.chemin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ajustesUsuario extends AppCompatActivity {

    private int idUsuario;
    private Button btnBtnEditarPerfil;
    private Button btnConfiguracion;
    private Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_usuario);

        Button btnEliminarCuenta = findViewById(R.id.btnEliminarCuenta);
        btnBtnEditarPerfil = findViewById(R.id.btnEditarPerfil);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);
        btnCerrarSesion = findViewById(R.id.btnCarrarSesion);

        SharedPreferences prefs = getSharedPreferences("CHEMIN", MODE_PRIVATE);
        idUsuario = prefs.getInt("id", -1);


        btnEliminarCuenta.setOnClickListener(v -> mostrarDialogoEliminar());
        btnBtnEditarPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(this, editarPerfil.class);
            startActivity(intent);
        });
        btnConfiguracion.setOnClickListener(v -> {
            Intent intent = new Intent(this, principal.class);
            startActivity(intent);
        });
        btnCerrarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void mostrarDialogoEliminar() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar cuenta")
                .setMessage("¿Estás seguro de que quieres eliminar tu cuenta? Esta acción no se puede deshacer.")
                .setCancelable(false)
                .setPositiveButton("Eliminar", (dialog, which) -> eliminarCuenta())
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void eliminarCuenta() {
        if (idUsuario == -1) {
            Toast.makeText(this, "Error: usuario no identificado", Toast.LENGTH_SHORT).show();
            return;
        }

        Api api = new Api();

        api.eliminarUsuario(idUsuario, exito -> runOnUiThread(() -> {

            if (exito) {

                SharedPreferences prefs = getSharedPreferences("CHEMIN", MODE_PRIVATE);
                prefs.edit().clear().apply();

                Toast.makeText(this, "Cuenta eliminada correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class); // ← tu pantalla de login
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            } else {
                Toast.makeText(this, "No se pudo eliminar la cuenta", Toast.LENGTH_SHORT).show();
            }

        }));

    }

}
