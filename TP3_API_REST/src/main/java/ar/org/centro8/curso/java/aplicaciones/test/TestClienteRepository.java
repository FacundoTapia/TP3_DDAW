package ar.org.centro8.curso.java.aplicaciones.test;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.tipoDocumento;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ClienteRepository;
import ar.org.centro8.curso.java.connectors.Connector;

public class TestClienteRepository {
    public static void main(String[] args) {
        I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
        
        cr.save(new Cliente("Facundo", "Tapia", "Lavalle 3571", "facutapia06@gmail.com", "1141848451", tipoDocumento.DNI, 42643579));
        cr.save(new Cliente("Bernardo", "Garcia", "Medrano 150", "berni@gmail.com", "1141457861", tipoDocumento.DNI, 34526789));
        cr.save(new Cliente("Violeta", "Lopez", "Charcas 3000", "violeta@gmail.com", "1141347328", tipoDocumento.DNI, 22789654));
        
        cr.remove(cr.getById(27));
        
        Cliente cliente = cr.getById(28);
        if (cliente != null && cliente.getId()!= 0) {
            cliente.setDireccion("Peru 276");
            cr.update(cliente);
        }
        
        System.out.println("*********************************");
        cr.getAll().forEach(System.out::println);
    }
}
