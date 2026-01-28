package com.example.chemin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorChats extends RecyclerView.Adapter<AdaptadorChats.ChatViewHolder> {

    private List<Chat> chats;

    public AdaptadorChats(List<Chat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.tvNombre.setText(chat.getNombre());
        holder.tvUltimoMensaje.setText(chat.getUltimoMensaje());
        holder.tvHora.setText(chat.getHora());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvUltimoMensaje, tvHora;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvUltimoMensaje = itemView.findViewById(R.id.tvUltimoMensaje);
            tvHora = itemView.findViewById(R.id.tvHora);
        }
    }
}
