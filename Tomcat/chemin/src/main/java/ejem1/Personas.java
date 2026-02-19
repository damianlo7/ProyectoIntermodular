// package ejem1;

// // import java.io.ObjectInputFilter.Status;
// import java.util.ArrayList;

// // import jakarta.ws.rs.Consumes;
// // import jakarta.ws.rs.DefaultValue;
// // import jakarta.ws.rs.GET;
// // import jakarta.ws.rs.POST;
// // import jakarta.ws.rs.Produces;
// // import jakarta.ws.rs.QueryParam;
// // import jakarta.ws.rs.core.MediaType;
// // import jakarta.ws.rs.core.Response;

// import jakarta.ws.rs.Consumes;
// import jakarta.ws.rs.DefaultValue;
// import jakarta.ws.rs.FormParam;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.PathParam;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.QueryParam;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;
// import jakarta.ws.rs.core.Response.Status;
// import jakarta.xml.bind.annotation.XmlRootElement;

// @Path("/personas")
// public class Personas {

//     static ArrayList<Persona> personas = new ArrayList<Persona>();
//     @DefaultValue("valor por defecto")
//     @QueryParam("valor")
//     String text;

//     // Content-type
//     // application/xml
//     // acept
//     // body
//     // XML

//     // <persona>
//     // <casado>No</casado>
//     // <id>1</id>
//     // <nombre>Damian</nombre>
//     // <sexo>Hombre</sexo>
//     // </persona>

//     // 1
//     @POST
//     @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     public Response guardar(Persona p) {
//         personas.add(p);// Se añade el persona a la lista
//         return Response.ok(p).build(); // Se devuelve el persona
//     }

//     // GET
//     // Accept
//     // application/xml

//     // 2

//     @GET
//     @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     public ArrayList<Persona> listar() {
//         return this.personas;
//     }

//     // HACER RESPONSE

//     // 3
//     @GET
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     @Path("/{nombre}")
//     public Response ver(@PathParam("nombre") String name) {
//         for (Persona p : personas) {
//             if (p.getNombre().equals(name)) {
//                 return Response.ok(p).build();
//             }
//         }
//         return Response.status(Status.NOT_FOUND).entity("No existe").build();
//     }

//     // 4
//     @GET
//     @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//     @Path("/buscar")

//     public Response ver2(@QueryParam("cadena") String name) {

//         for (Persona persona : personas) {
//             if (persona.getNombre().equalsIgnoreCase(name)) {
//                 return Response.ok(persona).build();
//             }
//         }
//         return Response.status(Status.NOT_FOUND).entity("No exise").build();

//     }

//     // 5

//     @POST
//     @Path("/form/guardar")
//     @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     public Response insertarFormulario(
//             @FormParam("casado") String casado,
//             @FormParam("id") int id,
//             @FormParam("nombre") String nombre,
//             @FormParam("sexo") String sexo) {

//         Persona p = new Persona(id, nombre, casado, sexo);

//         return Response.status(Status.CREATED).entity(p).build();
//     }

//     // 6
//     @POST
//     @Path("/form/insertar")
//     @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     public Response guardarFormulario(
//             @FormParam("casado") String casado,
//             @FormParam("id") int id,
//             @FormParam("nombre") String nombre,
//             @FormParam("sexo") String sexo) {

//         Persona p = new Persona(id, nombre, casado, sexo);
//         personas.add(p);

//         return Response.status(Status.CREATED).entity(p).build();
//     }

//     // 7
//     @POST
//     @Path("/add")
//     @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//     public Response multiInsert(ArrayList<Persona> nuevasPersonas) {

//         if (nuevasPersonas == null || nuevasPersonas.isEmpty()) {
//             return Response.status(Status.BAD_REQUEST).entity("Lista de personas vacía").build();
//         }
//         personas.addAll(nuevasPersonas);
//         return Response.status(Status.CREATED).entity(nuevasPersonas).build();
//     }

//     // 8

//     @GET
//     @Path("/id")
//     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

//     public Response borrar(@PathParam("id") int idd) {
//         personas.remove(idd);
//         return Response.status(Status.CREATED).entity(personas).build();
//     }

//     // 9

//     //sin acabar
//     @GET
//     @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//     @Path("/buscar")

//     public Response verDefault(@QueryParam("cadena") String name) {

//         for (Persona persona : personas) {
//             if (persona.getNombre().equalsIgnoreCase(name)) {
//                 return Response.ok(persona).build();
//             }
//         }
//         return Response.status(Status.NOT_FOUND).entity("No exise").build();
//     }
// }

// // hasta el 9 luego 12 todos hasta el 19