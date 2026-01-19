package com.example.chemin;

import android.widget.Button;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Usuario implements Serializable {
    String nombre;
    String contraseña;


    public Usuario(){
        nombre = "dloppin";
        contraseña = "abc123..";
    }


    public Usuario(String nombre, String contraseña){
        this.nombre = nombre;
        this.contraseña= contraseña;
    }

    public static ArrayList<Usuario> generarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("prueba1","ab123"));
        return usuarios;
    }




}
