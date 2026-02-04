package com.example.chemin;

import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Api {

    public interface ApiCallback<T> {
        void onResult(T result);
    }

    public void login(String username, String password, ApiCallback<Boolean> callback) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuario/login");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("username", username);
                json.put("contrasenha", password);

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }

                int code = conn.getResponseCode();
                conn.disconnect();
                if (callback != null) callback.onResult(code == 200);
            } catch (Exception e) {
                Log.e("API", "Error en login", e);
                if (callback != null) callback.onResult(false);
            }
        }).start();
    }


    public void obtenerDatosUsuario(String username, ApiCallback<Usuario> callback) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuario?username=" + username);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                int code = conn.getResponseCode();
                if (code == 200) {
                    StringBuilder response = new StringBuilder();
                    try (var reader = new java.io.BufferedReader(
                            new java.io.InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = reader.readLine()) != null) response.append(line.trim());
                    }

                    JSONObject obj = new JSONObject(response.toString());
                    Usuario u = new Usuario(
                            obj.getString("nombreCompleto"),
                            obj.getString("username"),
                            obj.getString("email"),
                            obj.getString("contrasenha")
                    );
                    u.setGenero(obj.getString("genero"));
                    conn.disconnect();
                    if (callback != null) callback.onResult(u);
                    return;
                }

                conn.disconnect();
                if (callback != null) callback.onResult(null);

            } catch (Exception e) {
                Log.e("API", "Error obtenerDatosUsuario", e);
                if (callback != null) callback.onResult(null);
            }
        }).start();
    }


//    public void actualizarUsuario(Usuario u, ApiCallback<Boolean> callback) {
//        new Thread(() -> {
//            try {
//                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuario/actualizar");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("POST");
//                conn.setRequestProperty("Content-Type", "application/json");
//                conn.setDoOutput(true);
//
//                JSONObject json = new JSONObject();
//                json.put("nombreCompleto", u.getNombreCompleto());
//                json.put("username", u.getUsername());
//                json.put("email", u.getEmail());
//                json.put("contrasenha", u.getContrasenha());
//                json.put("genero", u.getGenero());
//
//                try (OutputStream os = conn.getOutputStream()) {
//                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
//                }
//
//                int code = conn.getResponseCode();
//                conn.disconnect();
//                if (callback != null) callback.onResult(code == 200);
//
//            } catch (Exception e) {
//                Log.e("API", "Error actualizarUsuario", e);
//                if (callback != null) callback.onResult(false);
//            }
//        }).start();
//    }
    public void actualizarUsuario(Usuario u, ApiCallback<Boolean> callback) {
        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuario/actualizar");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST"); // según tu backend
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // Construir JSON
                JSONObject json = new JSONObject();
                json.put("nombreCompleto", u.getNombreCompleto());
                json.put("username", u.getUsername());
                json.put("email", u.getEmail());
                json.put("contrasenha", u.getContrasenha());
                json.put("genero", u.getGenero());

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }

                int code = conn.getResponseCode();

                // Leer InputStream para evitar leaks
                try (var is = (code >= 200 && code < 400) ? conn.getInputStream() : conn.getErrorStream()) {
                    if (is != null) {
                        while (is.read() != -1); // leer todo el contenido aunque no lo uses
                    }
                }

                if (callback != null) callback.onResult(code == 200);

            } catch (Exception e) {
                Log.e("API", "Error actualizarUsuario", e);
                if (callback != null) callback.onResult(false);
            } finally {
                if (conn != null) conn.disconnect();
            }
        }).start();
    }

}