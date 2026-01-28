package com.example.chemin;

public class Chat {
    private String nombre;
    private String ultimoMensaje;
    private String hora;

    public Chat(String nombre, String ultimoMensaje, String hora) {
        this.nombre = nombre;
        this.ultimoMensaje = ultimoMensaje;
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    public String getHora() {
        return hora;
    }
}
