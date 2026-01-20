package com.example.chemin;

import java.io.Serializable;
import java.util.ArrayList;

//implementar genero cuando este el api final 
public class Usuario implements Serializable {
    private int id;

    public Usuario(String user, String user1, int i) {
    }

    public int getId(){
        return  id;
    }
    
    private String nombre;
    public String getNombre() {
        return nombre;
    }
    
    private String contrasenha;

    public String getContrasenha() {
        return contrasenha;
    }
    
//    private String genero;
//
//    public String getGenero() {
//        return genero;
//    }
    
    public Usuario(){
        id= 0;
        nombre = "root";
        contrasenha = "";
    }
    
    public static ArrayList<Usuario> implementarUsuario(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("user","user",1));
        return usuarios;
    }
}
