package com.example.chemin;

public class Publicaciones {

    private int id;
    private int idUsuario;
    private String nombre;
    private String texto;
    private String imagen;
    private String otro;

    public Publicaciones(int id, int idUsuario, String nombre, String texto, String imagen, String otro) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.texto = texto;
        this.imagen = imagen;
        this.otro = otro;
    }

    public Publicaciones(String texto, String imagen, String otro) {
        this.texto = texto;
        this.imagen = imagen;
        this.otro = otro;
        this.id = 0;
        this.idUsuario = 0;
        this.nombre = "";
    }

    public Publicaciones(String texto, String imagen) {
        this.texto = texto;
        this.imagen = imagen;
        this.otro = "";
        this.id = 0;
        this.idUsuario = 0;
        this.nombre = "";
    }

    public Publicaciones(String texto) {
        this.texto = texto;
        this.imagen = null;
        this.otro = "";
        this.id = 0;
        this.idUsuario = 0;
        this.nombre = "";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getOtro() { return otro; }
    public void setOtro(String otro) { this.otro = otro; }
}
