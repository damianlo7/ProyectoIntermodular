package com.example.chemin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        RecyclerView rv = findViewById(R.id.rvChats);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat("Juan", "Ey qué tal", "18:42"));
        chats.add(new Chat("Ana", "¿Vienes hoy?", "17:10"));
        chats.add(new Chat("Grupo DAM", "Entrega mañana", "16:30"));

        rv.setAdapter(new AdaptadorChats(chats));
    }
}
