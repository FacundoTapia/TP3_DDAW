package tp3_Client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientTester {
    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8088/server/webresources/clientes/v1";
        System.out.println("***********************************************");
        System.out.println("Servicio Info Clientes.");
        System.out.println(responseBody(url));     

        url = "http://localhost:8088/server/webresources/clientes/v1/save"
                + "?nombre=Horacio&apellido=Perez&direccion=Laprida1200"
                + "&email=horacio@gmail&telefono=1156346712&tipoDocumento=DNI&numeroDocumento=38163477";
        System.out.println("***********************************************");
        System.out.println("Servicio Alta Clientes.");
        System.out.println(responseBody(url)); 
        
        url = "http://localhost:8088/server/webresources/clientes/v1/all";
        System.out.println("***********************************************");
        System.out.println("Servicio ClientesAll.");
        System.out.println(responseBody(url));         

        url = "http://localhost:8088/server/webresources/clientes/v1/byId?id=70";
        System.out.println("***********************************************");
        System.out.println("Servicio ClientesById.");
        System.out.println(responseBody(url));         

        url = "http://localhost:8088/server/webresources/clientes/v1/likeApellido?apellido=gar";
        System.out.println("***********************************************");
        System.out.println("Servicio ClientesLikeApellido.");
        System.out.println(responseBody(url));         
        
        url = "http://localhost:8088/server/webresources/clientes/v1/remove?id=75";
        System.out.println("***********************************************");
        System.out.println("Servicio Baja Clientes.");
        System.out.println(responseBody(url));        
    }
    
    private static String responseBody(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        response.headers().map().forEach((k, v)->System.out.println(k + " " + v));
        return response.body();
    }    
}
