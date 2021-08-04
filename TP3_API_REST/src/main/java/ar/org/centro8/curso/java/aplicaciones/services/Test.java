package ar.org.centro8.curso.java.aplicaciones.services;

import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ArticuloRepository;
import ar.org.centro8.curso.java.connectors.Connector;
import com.google.gson.Gson;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/test")
@SessionScoped
public class Test implements Serializable {
    /*
        - Especificacion JaxRS        
        - Libreria Jersey (cumple con JaxRS) desde JavaEE 6 esta incluida
    */
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String info(){
        return "<h1>Hola Mundo! WS RS</h1>";
    }
    
    @GET
    @Path("/info")
    @Produces(MediaType.TEXT_PLAIN)
    public String info2(){
        return "Método info2()";
    }
    
    @GET
    @Path("/calculadora")
    @Produces(MediaType.TEXT_PLAIN)
    public String calculadora(@QueryParam("nro1") int numero1, @QueryParam("nro2") int numero2){
        int resultado = numero1 + numero2;
        return resultado+"";
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public String list(){
        return new Gson().toJson(new ArticuloRepository(Connector.getConnection()).getAll());
    }
}
