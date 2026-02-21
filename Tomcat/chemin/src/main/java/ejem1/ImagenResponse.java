package ejem1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImagenResponse {
    @JsonProperty("idUsuario")
    private int idUsuario;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("imagen")
    private String imagen;
    @JsonProperty("username")
    private String username;
    @JsonProperty("id")
    private int id;
    @JsonProperty("texto")
    private String texto;

    public ImagenResponse() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    
}