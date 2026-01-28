package com.example.chemin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class mensajes extends AppCompatActivity {

    RecyclerView rvMessages;
    AdaptadorMensajes adapter;
    List<mensaje> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mensajes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvMessages = findViewById(R.id.rvMessages);

        // Lista de mensajes
        messageList = new ArrayList<>();
        messageList.add(new mensaje("Hola 👋"));
        messageList.add(new mensaje("¿Qué tal?"));

        // Adapter
        adapter = new AdaptadorMensajes(messageList);

        // LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        rvMessages.setLayoutManager(layoutManager);

        rvMessages.setAdapter(adapter);
    }
}
