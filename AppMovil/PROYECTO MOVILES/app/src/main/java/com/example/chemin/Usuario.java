package com.example.chemin;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombreCompleto;
    private String username;
    private String email;
    private String contrasenha;
    private String genero;

    private int id;
    public Usuario(String nombreCompleto, String username, String email, String contrasenha) {
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.email = email;
        this.contrasenha = contrasenha;
    }

    public Usuario(String nombreCompleto, String username, String email, String contrasenha, int id) {
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.email = email;
        this.contrasenha = contrasenha;
        this.id = id;
    }

    public Usuario(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasenha() { return contrasenha; }
    public void setContrasenha(String contrasenha) { this.contrasenha = contrasenha; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombreCompleto;
    }
}
