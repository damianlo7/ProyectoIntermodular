package com.example.chemin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class editarPerfil extends AppCompatActivity {

    EditText etNombreCompleto, etUsername, etEmail, etPassword;
    RadioGroup radioGroup;
    Button btnGuardar;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombreCompleto = findViewById(R.id.editTextText6);
        etUsername = findViewById(R.id.editTextText7);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextText5);
        radioGroup = findViewById(R.id.radioGroup);
        btnGuardar = findViewById(R.id.agregarCuenta);

        api = new Api();

        SharedPreferences prefs = getSharedPreferences("CHEMIN", MODE_PRIVATE);
        String username = prefs.getString("username", null);
        if (username == null) {
            finish();
            return;
        }

        api.obtenerDatosUsuario(username, usuario -> {
            runOnUiThread(() -> {
                if (usuario != null) {
                    etNombreCompleto.setText(usuario.getNombreCompleto());
                    etUsername.setText(usuario.getUsername());
                    etEmail.setText(usuario.getEmail());

                    switch (usuario.getGenero()) {
                        case "hombre": radioGroup.check(R.id.hombre); break;
                        case "mujer": radioGroup.check(R.id.mujer); break;
                        default: radioGroup.check(R.id.otro); break;
                    }
                }
            });
        });


        btnGuardar.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            String genero = "";
            if (selectedId != -1) {
                genero = ((RadioButton) findViewById(selectedId)).getText().toString();
            }

            String nombre = etNombreCompleto.getText() != null ? etNombreCompleto.getText().toString() : "";
            String usernameFinal = etUsername.getText() != null ? etUsername.getText().toString() : "";
            String email = etEmail.getText() != null ? etEmail.getText().toString() : "";
            String password = etPassword.getText() != null ? etPassword.getText().toString() : "";

            Usuario u = new Usuario(nombre, usernameFinal, email, password.isEmpty() ? null : password);
            u.setGenero(genero);

            api.actualizarUsuario(u, success -> runOnUiThread(() -> {
                if (success) {
                    Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                }
            }));
        });


    }
}
