package ar.org.centro8.curso.java.aplicaciones.services;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.tipoDocumento;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ClienteRepository;
import ar.org.centro8.curso.java.connectors.Connector;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/clientes/v1")
public class ClienteService {
    I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String info(){
        return "Servicio de Clientes v1 activo";
    }
    
    @GET
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    public String save
        (
                        @QueryParam("nombre") String nombre, 
                        @QueryParam("apellido") String apellido, 
                        @QueryParam("direccion") String direccion, 
                        @QueryParam("email") String email, 
                        @QueryParam("telefono") String telefono, 
                        @QueryParam("tipoDocumento") tipoDocumento tipoDocumento, 
                        @QueryParam("numeroDocumento") int numeroDocumento
        ){
        
        Cliente cliente = new Cliente(nombre, apellido, direccion, email, telefono, tipoDocumento, numeroDocumento);
        cr.save(cliente);
        
        return cliente.getId()+"";
    }
        
    @GET
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    public String remove(@QueryParam("id") int id){
        try {
            cr.remove(cr.getById(id));
            return "true";
        } catch (Exception e) {
            System.out.println(e);
            return "false";
        }
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){
        return new Gson().toJson(cr.getAll());
    }
    
    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@QueryParam("id") int id){
        return new Gson().toJson(cr.getById(id));
    }
    
    @GET
    @Path("/likeApellido")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLikeApellido(@QueryParam("apellido") String apellido){
        return new Gson().toJson(cr.getLikeApellido(apellido));
    }
}