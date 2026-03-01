package com.example.chemin.ui.dashboard;

import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chemin.databinding.FragmentDashboardBinding;

import org.json.JSONObject;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btnPublicar = binding.button2;
        EditText editText = binding.editTextText;

        btnPublicar.setOnClickListener(v -> {
            String texto = editText.getText().toString().trim();
            if (texto.isEmpty()) {
                Toast.makeText(getContext(), "Escribe algo primero", Toast.LENGTH_SHORT).show();
                return;
            }
            publicarTexto(texto);
        });

        return root;
    }

    private void publicarTexto(String texto) {
        SharedPreferences prefs = requireContext().getSharedPreferences("CHEMIN", Context.MODE_PRIVATE);
        int idUsuario = prefs.getInt("id", -1);

        if (idUsuario == -1) {
            Toast.makeText(getContext(), "Error: usuario no logueado", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:8080/tema5maven/rest/publicacion/texto";
//        String url = "http://192.168.1.104:8080/tema5maven/rest/publicacion/texto";
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        try {
            JSONObject body = new JSONObject();
            body.put("idUsuario", idUsuario);
            body.put("texto", texto);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST, url, body,
                    response -> {
                        Toast.makeText(getContext(), "Publicado correctamente", Toast.LENGTH_SHORT).show();
                        binding.editTextText.setText("");
                        requireActivity().getSupportFragmentManager().popBackStack();
                    },
                    error -> {
                        Toast.makeText(getContext(), "Publicado correctamente", Toast.LENGTH_SHORT).show();
                        binding.editTextText.setText("");
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }
            );

            queue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}