package com.example.chemin;

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

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CrearCuenta extends AppCompatActivity {

//    ArrayList<Usuario> usuarios;

    EditText etNombreCompleto, etUsername, etEmail, etPassword;
    RadioGroup radioGroup;
    Button btnCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_cuenta);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


//        usuarios = (ArrayList<Usuario>) getIntent().getSerializableExtra("usuarios");


        etNombreCompleto = findViewById(R.id.editTextText6);
        etUsername = findViewById(R.id.editTextText7);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextText5);
        radioGroup = findViewById(R.id.radioGroup);
        btnCrearCuenta = findViewById(R.id.agregarCuenta);

        btnCrearCuenta.setOnClickListener(v -> registrarCuenta());
    }

    private void registrarCuenta() {
        String nombreCompleto = etNombreCompleto.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();


        int selectedId = radioGroup.getCheckedRadioButtonId();
        String genero = "";
        if (selectedId != -1) {
            RadioButton selected = findViewById(selectedId);
            genero = selected.getText().toString();
        }


        if (nombreCompleto.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || genero.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }


        Usuario u = new Usuario(nombreCompleto, username, email, password);
        u.setGenero(genero);
        new Thread(() -> {
            boolean registrado = registrarUsuarioBackend(u);

            runOnUiThread(() -> {
                if (registrado) {
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
//                    if (usuarios != null) usuarios.add(u);
                    finish();
                } else {
                    Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private boolean registrarUsuarioBackend(Usuario u) {
        try {
           URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuario/registro");
//            URL url = new URL("http://192.168.56.1:8080/tema5maven/rest/usuario/registro");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("nombreCompleto", u.getNombre());
            json.put("username", u.getUsername());
            json.put("email", u.getEmail());
            json.put("contrasenha", u.getContrasenha());


            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
            }


            int code = conn.getResponseCode();
            conn.disconnect();
            return code == 200 || code == 201;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
