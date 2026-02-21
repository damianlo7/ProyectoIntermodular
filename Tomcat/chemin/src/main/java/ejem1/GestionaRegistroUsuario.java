package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
public class GestionaRegistroUsuario {

    private static final String URL = "jdbc:mariadb://localhost:3306/prueba_chemin";
    private static final String USER = "root";
    private static final String PASS = "";

    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(RegistroUsuario u) {
        String sql = "INSERT INTO usuario (nombre, username, email, contrasenha) VALUES (?, ?, ?, ?)";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                    PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, u.getNombreCompleto());
                ps.setString(2, u.getUsername().toLowerCase());
                ps.setString(3, u.getEmail());

                String hash = BCrypt.hashpw(u.getContrasenha(), BCrypt.gensalt());
                ps.setString(4, hash);

                ps.executeUpdate();

                return Response.status(Response.Status.CREATED)
                        .entity("{\"status\":\"ok\",\"message\":\"Usuario registrado\"}")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(RegistroUsuario u) {
        String sql = "SELECT id_usuario, nombre, username, email, contrasenha, genero FROM usuario WHERE username = ?";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                    PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, u.getUsername().toLowerCase());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String hashBD = rs.getString("contrasenha");
                    if (BCrypt.checkpw(u.getContrasenha(), hashBD)) {
                        RegistroUsuario usuarioLogueado = new RegistroUsuario();
                        usuarioLogueado.setId(rs.getInt("id_usuario"));
                        usuarioLogueado.setNombreCompleto(rs.getString("nombre"));
                        usuarioLogueado.setUsername(rs.getString("username"));
                        usuarioLogueado.setEmail(rs.getString("email"));
                        usuarioLogueado.setGenero(rs.getString("genero"));
                        // No devolver la contraseña
                        return Response.ok(usuarioLogueado).build();
                    }
                }

                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"status\":\"error\",\"message\":\"Credenciales incorrectas\"}")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(RegistroUsuario u) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

                String sql = "UPDATE usuario SET nombre = ?, email = ?, contrasenha = ?, genero = ? WHERE LOWER(username) = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, u.getNombreCompleto());
                    ps.setString(2, u.getEmail());

                    String hash = null;
                    if (u.getContrasenha() != null && !u.getContrasenha().isEmpty()) {
                        hash = BCrypt.hashpw(u.getContrasenha(), BCrypt.gensalt());
                    }
                    ps.setString(3, hash);
                    ps.setString(4, u.getGenero());
                    ps.setString(5, u.getUsername().toLowerCase());

                    int rows = ps.executeUpdate();
                    System.out.println("Filas afectadas: " + rows);

                    if (rows > 0) {
                        return Response.ok("{\"status\":\"ok\",\"message\":\"Usuario actualizado\"}").build();
                    } else {
                        return Response.status(Response.Status.NOT_FOUND)
                                .entity("{\"status\":\"error\",\"message\":\"Usuario no encontrado\"}")
                                .build();
                    }
                }
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminarUsuario(@PathParam("id") int id) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                conn.setAutoCommit(false);

                // try (PreparedStatement ps = conn.prepareStatement(
                // "DELETE FROM like_publicacion WHERE id_usuario = ?")) {
                // ps.setInt(1, id); ps.executeUpdate();
                // }

                // try (PreparedStatement ps = conn.prepareStatement(
                // "DELETE FROM like_publicacion WHERE id_publicacion IN (SELECT id_publicacion
                // FROM publicacion WHERE id_usuario = ?)")) {
                // ps.setInt(1, id); ps.executeUpdate();
                // }

                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM texto WHERE id_publicacion IN (SELECT id_publicacion FROM publicacion WHERE id_usuario = ?)")) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM imagen WHERE id_usuario = ?")) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                // try (PreparedStatement ps = conn.prepareStatement(
                // "DELETE FROM republica WHERE id_usuario = ? OR id_publicacion IN (SELECT
                // id_publicacion FROM publicacion WHERE id_usuario = ?)")) {
                // ps.setInt(1, id); ps.setInt(2, id); ps.executeUpdate();
                // }

                // try (PreparedStatement ps = conn.prepareStatement(
                // "DELETE FROM mensaje WHERE id_usuarioE = ? OR id_usuarioR = ?")) {
                // ps.setInt(1, id); ps.setInt(2, id); ps.executeUpdate();
                // }

                // try (PreparedStatement ps = conn.prepareStatement(
                // "DELETE FROM seguidor WHERE id_seguidor = ? OR id_seguido = ? OR id_usuario =
                // ?")) {
                // ps.setInt(1, id); ps.setInt(2, id); ps.setInt(3, id); ps.executeUpdate();
                // }

                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM publicacion WHERE id_usuario = ?")) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM usuario WHERE id_usuario = ?")) {
                    ps.setInt(1, id);
                    int filas = ps.executeUpdate();

                    if (filas > 0) {
                        conn.commit();
                        return Response.ok("Usuario eliminado").build();
                    } else {
                        conn.rollback();
                        return Response.status(Response.Status.NOT_FOUND)
                                .entity("Usuario no encontrado").build();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al eliminar usuario: " + e.getMessage()).build();
        }

    }

    @POST
    @Path("/ubicacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarUbicacion(RegistroUsuario u) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                    PreparedStatement ps = conn.prepareStatement(
                            "UPDATE usuario SET latitud = ?, longitud = ? WHERE id_usuario = ?")) {
                ps.setDouble(1, u.getLatitud());
                ps.setDouble(2, u.getLongitud());
                ps.setInt(3, u.getId());
                ps.executeUpdate();
                return Response.ok("{\"status\":\"ok\"}").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
