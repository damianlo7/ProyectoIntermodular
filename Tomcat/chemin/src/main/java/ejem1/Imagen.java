package ejem1;

public class Imagen {
    private String nombre;   
    private String imagen;   
    private int idUsuario;

    public Imagen() {}

    public Imagen(String nombre, String imagen, int idUsuario) {
        this.nombre = nombre;
        this.imagen = imagen;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
