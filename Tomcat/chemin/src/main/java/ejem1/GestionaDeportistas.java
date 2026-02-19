package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/deportista")
public class GestionaDeportistas {
    private static final String URL = "jdbc:mariadb://localhost:3306/ad_tema6";
    private static final String USER = "root";
    private static final String PASS = "";

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response obtenerTodos() {
        ArrayList<Deportista> listaDeportistas = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery("Select * from deportistas")) {
                while (rs.next()) {
                    listaDeportistas.add(new Deportista(rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getBoolean("activo"),
                            rs.getString("activo"),
                            rs.getString("genero")));
                }
                GenericEntity<List<Deportista>> entity = new GenericEntity<List<Deportista>>(listaDeportistas) {
                };

                Response response = Response.ok(entity).build();
                return Response.ok(listaDeportistas).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
            }
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    @Path("/android")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response subirDeportistaAndroid(Deportista d) throws ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(URL, USER, PASS);
            Statement st = conexion.createStatement();
            st.executeUpdate("INSERT INTO deportistas(nombre,deporte) VALUES ('" + d.getDeporte() + "','"
                    + d.getDeporte() + "')");
            return Response.ok("Subdo Correctamente").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nope").build();
        }
    }

    

}
