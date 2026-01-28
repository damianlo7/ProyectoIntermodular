package com.example.chemin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class principal extends AppCompatActivity {

    ArrayList<Publicaciones> publicaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
            Intent intent = new Intent(this, ChatsActivity.class);
            startActivity(intent);

        }else if (id == R.id.menu_perfil){
            Intent intent = new Intent(this, ajustesUsuario.class);
            startActivity(intent);
        }else if (id == R.id.menu_anhadirpublicaciones){
            Intent intent = new Intent(this, gestionPublicacion.class);
            startActivity(intent);
        }


//        if (id == R.id.menu_home) {
//            // Acción Inicio
//            return true;
//        }
//
//        if (id == R.id.menu_settings) {
//            // Ejemplo: abrir otra activity
//            // Intent intent = new Intent(this, SettingsActivity.class);
//            // startActivity(intent);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    public class MyviewHolder extends RecyclerView.ViewHolder{


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
