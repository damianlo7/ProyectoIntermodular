package com.example.chemin;

import java.io.Serializable;
import java.util.ArrayList;

public class OpcionesUsuario implements Serializable {
    String nombre;
    String contraseña;


    public OpcionesUsuario(){
        nombre = "dloppin";
        contraseña = "abc123..";
    }


    public OpcionesUsuario(String nombre, String contraseña){
        this.nombre = nombre;
        this.contraseña= contraseña;
    }

    public static ArrayList<OpcionesUsuario> generarUsuarios(){
        ArrayList<OpcionesUsuario> usuarios = new ArrayList<>();
        usuarios.add(new OpcionesUsuario("prueba1","ab123"));
        return usuarios;
    }





}
