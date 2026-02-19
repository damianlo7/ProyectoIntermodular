package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/publicacion")
public class GestionaPublicaciones {

    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/prueba_chemin";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    @POST
    @Path("/imagen")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response subirImagen(Imagen imagen) {
        System.out.println(
                "Recibida imagen: " + imagen.getNombre() + ", longitud Base64: " + imagen.getImagen().length());

        if (imagen.getNombre() == null || imagen.getImagen() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Datos de imagen incompletos")
                    .build();
        }

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String sql = "INSERT INTO imagen (id_usuario,nombre,contenido) VALUES (?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                    PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, imagen.getIdUsuario());
                stmt.setString(2, imagen.getNombre());
                stmt.setBytes(3, Base64.getDecoder().decode(imagen.getImagen()));
                stmt.executeUpdate();
            }

            return Response.ok("Imagen subida").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al subir imagen")
                    .build();
        }
    }

    // @GET
    // @Path("/lista")
    // @Produces(MediaType.APPLICATION_JSON)
    // public List<ImagenResponse> obtenerPublicaciones2() {
    // List<ImagenResponse> publicaciones = new ArrayList<>();
    // try {
    // Class.forName("org.mariadb.jdbc.Driver");
    // String sql = "SELECT id_usuario, nombre, contenido FROM imagen";

    // try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER,
    // JDBC_PASSWORD);
    // PreparedStatement stmt = conn.prepareStatement(sql)) {

    // ResultSet rs = stmt.executeQuery();
    // while (rs.next()) {
    // ImagenResponse imgRes = new ImagenResponse();
    // imgRes.setIdUsuario(rs.getInt("id_usuario"));
    // imgRes.setNombre(rs.getString("nombre"));

    // byte[] bytes = rs.getBytes("contenido");
    // if (bytes != null) {
    // String base64 = Base64.getEncoder().encodeToString(bytes);
    // imgRes.setImagen(base64);
    // }

    // publicaciones.add(imgRes);
    // }
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return publicaciones;
    // }

    @GET
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ImagenResponse> obtenerPublicaciones2() {
        List<ImagenResponse> publicaciones = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            // String sql = "SELECT i.id_usuario, i.nombre, i.contenido, u.username " +
            //         "FROM imagen i JOIN usuario u ON i.id_usuario = u.id_usuario";
            String sql = "SELECT i.id, i.id_usuario, i.nombre, i.contenido, u.username " +
            "FROM imagen i JOIN usuario u ON i.id_usuario = u.id_usuario";
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                    PreparedStatement stmt = conn.prepareStatement(sql)) {

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    ImagenResponse imgRes = new ImagenResponse();
                    imgRes.setId(rs.getInt("id"));
                    imgRes.setIdUsuario(rs.getInt("id_usuario"));
                    imgRes.setNombre(rs.getString("nombre"));
                    imgRes.setUsername(rs.getString("username"));
                    System.out.println(">>> username: " + rs.getString("username"));

                    byte[] bytes = rs.getBytes("contenido");
                    if (bytes != null) {
                        imgRes.setImagen(Base64.getEncoder().encodeToString(bytes));
                    }

                    publicaciones.add(imgRes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicaciones;
    }

    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminarPublicacion(@PathParam("id") int id) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                    PreparedStatement ps = conn.prepareStatement(
                            "DELETE FROM imagen WHERE id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
                return Response.ok("Eliminada").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
