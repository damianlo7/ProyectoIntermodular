package com.example.chemin;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Api {

    public interface ApiCallback<T> {
        void onResult(T result);
    }

    // En Api.java
    public void login(String username, String password, ApiCallback<Usuario> callback) {
        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuario/login");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);


                JSONObject json = new JSONObject();
                json.put("username", username);
                json.put("contrasenha", password);

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                    os.flush();
                }

                int code = conn.getResponseCode();

                if (code == 200) {
                    BufferedReader reader = new BufferedReader(
                            new java.io.InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) response.append(line);

                    JSONObject obj = new JSONObject(response.toString());

                    Usuario u = new Usuario(
                            obj.optString("nombre", ""),
                            obj.optString("username", ""),
                            obj.optString("email", ""),
                            "",
                            obj.optInt("id", 0)
                    );
                    u.setGenero(obj.optString("genero", ""));

                    if (callback != null) callback.onResult(u);
                } else {
                    if (callback != null) callback.onResult(null);
                }

            } catch (Exception e) {
                e.printStackTrace();
                if (callback != null) callback.onResult(null);
            } finally {
                if (conn != null) conn.disconnect();
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
                    try (var reader = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = reader.readLine()) != null) response.append(line.trim());
                    }

                    JSONObject obj = new JSONObject(response.toString());
                    Usuario u = new Usuario(
                            obj.getString("nombreCompleto"),
                            obj.getString("username"),
                            obj.getString("email"),
                            obj.getString("contrasenha"),
                            obj.getInt("id")
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

    /*
    public void actualizarUsuario(Usuario u, ApiCallback<Boolean> callback) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuario/actualizar");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

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
                conn.disconnect();
                if (callback != null) callback.onResult(code == 200);

            } catch (Exception e) {
                Log.e("API", "Error actualizarUsuario", e);
                if (callback != null) callback.onResult(false);
            }
        }).start();
    }
    */

    public void actualizarUsuario(Usuario u, ApiCallback<Boolean> callback) {
        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuario/actualizar");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST"); // o PUT si cambias el backend
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("nombreCompleto", u.getNombreCompleto());
                json.put("username", u.getUsername());
                json.put("email", u.getEmail());
                if (u.getContrasenha() != null && !u.getContrasenha().isEmpty()) {
                    json.put("contrasenha", u.getContrasenha());
                }
                json.put("genero", u.getGenero());

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }

                int code = conn.getResponseCode();

                InputStream is = (code >= 400) ? conn.getErrorStream() : conn.getInputStream();
                if (is != null) {
                    StringBuilder resp = new StringBuilder();
                    try (var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = reader.readLine()) != null) resp.append(line);
                    }
                    Log.d("API", "Actualizar usuario - code: " + code + " resp: " + resp);
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

    public void subirImagen(Uri imageUri, Context context, String nombre, int idUsuario) {
        new Thread(() -> {
            HttpURLConnection con = null;
            try {
                InputStream is = context.getContentResolver().openInputStream(imageUri);
                if (is == null) {
                    Log.e("UPLOAD", "InputStream nulo");
                    return;
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
                is.close();

                String base64Imagen = Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP);

                JSONObject json = new JSONObject();
                json.put("nombre", nombre);
                json.put("imagen", base64Imagen);
                json.put("idUsuario", idUsuario);

                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/publicacion/imagen");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setConnectTimeout(15000);
                con.setReadTimeout(15000);
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                con.connect();
                System.out.println("1");

                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input);
                    os.flush();
                }

                int code = con.getResponseCode();
                Log.i("UPLOAD", "Código respuesta: " + code);

            } catch (Exception e) {
                Log.e("UPLOAD", "Error al subir la imagen", e);
            } finally {
                if (con != null) con.disconnect();
            }
        }).start();
    }
}
