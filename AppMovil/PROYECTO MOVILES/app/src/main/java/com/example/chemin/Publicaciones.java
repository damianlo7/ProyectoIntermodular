package com.example.chemin;

public class Publicaciones {
    private int id_publicacion;
    private int id_usuario;
    private int fecha_publicacion;

    public int getId_publicacion(){
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(int fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }


   public Publicaciones(int id_publicacion, int id_usuario, int fecha_publicacion){
        this.fecha_publicacion = fecha_publicacion;
        this.id_publicacion = id_publicacion;
        this.id_usuario = id_usuario;
   }
}
