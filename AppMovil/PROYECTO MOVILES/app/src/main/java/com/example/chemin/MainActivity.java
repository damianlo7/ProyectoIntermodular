package com.example.chemin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;


import java.util.ArrayList;
import androidx.appcompat.app.AppCompatDelegate;




public class MainActivity extends AppCompatActivity {

    EditText usuario;
    EditText contrasenha;
    Button registrarse;
    Button oldpass;
    Button btnentrar;
    ArrayList<OpcionesUsuario> usuarios;

//    Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuario = findViewById(R.id.usuario);
        contrasenha = findViewById(R.id.contrasenha);
        registrarse = findViewById(R.id.registrarse);
        oldpass = findViewById(R.id.olpass);
        btnentrar = findViewById(R.id.btnentrar);
//        toolbar2 = findViewById(R.id.toolbar2);


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new
                ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                Intent intent=result.getData();

                if (intent != null){
                    if (intent.hasExtra("usuarioNuevo")){
                        OpcionesUsuario u = (OpcionesUsuario) intent.getSerializableExtra("usuarioNuevo");
                        usuarios.add(u);
                    }
                }
            }
        });

        btnentrar.setOnClickListener(v -> {
            String user = usuario.getText().toString().trim();
            String pass = contrasenha.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                usuario.setError("Obligatorio");
                contrasenha.setError("Obligatorio");
                return;
            }

            Api api = new Api();
            api.login(user, pass, usuarioObj -> {
                runOnUiThread(() -> {
                    if (usuarioObj != null) {
                        SharedPreferences prefs = getSharedPreferences("CHEMIN", MODE_PRIVATE);
                        prefs.edit()
                                .putString("username", usuarioObj.getUsername())
                                .putString("nombreCompleto", usuarioObj.getNombreCompleto())
                                .putString("email", usuarioObj.getEmail())
                                .putString("genero", usuarioObj.getGenero())
                                .putInt("id", usuarioObj.getId())
                                .apply();

                        Intent intent = new Intent(MainActivity.this, principal.class);
                        startActivity(intent);
                        finish();
                    } else {
                        contrasenha.setText("");
                        contrasenha.setError("Usuario o contraseña incorrectos");
                    }
                });
            });

        });






        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CrearCuenta.class);
                launcher.launch(intent);
            }
        });

        oldpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), restaurarContrasenha.class);
                launcher.launch(intent);
            }
        });


    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menusuperior,menu);
//        return true;
//    }
}