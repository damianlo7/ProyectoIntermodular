package com.example.chemin;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class AdaptadorPublicaciones extends RecyclerView.Adapter<AdaptadorPublicaciones.ViewHolder> {

    private List<Publicaciones> listaPublicaciones;
    private int idUsuarioLogueado;

    public AdaptadorPublicaciones(List<Publicaciones> listaPublicaciones, int idUsuarioLogueado) {
        this.listaPublicaciones = listaPublicaciones;
        this.idUsuarioLogueado = idUsuarioLogueado;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_publicacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Publicaciones publicacion = listaPublicaciones.get(position);

        holder.tvUsername.setText("@" + publicacion.getOtro());
        holder.tvUsername.setVisibility(View.VISIBLE);

        if (publicacion.getIdUsuario() == idUsuarioLogueado) {
            holder.btnOpciones.setVisibility(View.VISIBLE);
            holder.btnOpciones.setOnClickListener(v -> {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Opciones")
                        .setItems(new String[]{"Eliminar publicación"}, (dialog, which) -> {
                            if (which == 0) eliminarPublicacion(v.getContext(), publicacion.getId(), position);
                        })
                        .show();
            });
        } else {
            holder.btnOpciones.setVisibility(View.GONE);
        }

        if (publicacion.getTexto() != null && !publicacion.getTexto().isEmpty()) {
            holder.textView.setText(publicacion.getTexto());
            holder.textView.setVisibility(View.VISIBLE);
        } else {
            holder.textView.setVisibility(View.GONE);
        }

        if (publicacion.getImagen() != null && !publicacion.getImagen().isEmpty()) {
            byte[] decodedBytes = Base64.decode(publicacion.getImagen(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            if (bitmap != null) {
                holder.imageView.setImageBitmap(bitmap);
                holder.imageView.setVisibility(View.VISIBLE);
            } else {
                holder.imageView.setVisibility(View.GONE);
            }
        } else {
            holder.imageView.setVisibility(View.GONE);
        }
    }

    private void eliminarPublicacion(Context context, int id, int position) {
//        String url = "http://10.0.2.2:8080/tema5maven/rest/publicacion/eliminar/" + id;
        String url = "http://192.168.1.104:8080/tema5maven/rest/publicacion/eliminar/" + id;
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                response -> {
                    listaPublicaciones.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "Publicación eliminada", Toast.LENGTH_SHORT).show();
                },
                error -> Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

    @Override
    public int getItemCount() {
        return listaPublicaciones.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        TextView tvUsername;
        ImageButton btnOpciones;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvTexto);
            imageView = itemView.findViewById(R.id.ivImagen);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            btnOpciones = itemView.findViewById(R.id.btnOpciones);
        }
    }
}