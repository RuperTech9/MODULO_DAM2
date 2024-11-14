package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.IOException;
import java.net.*;

public class E01_TCPServidor {
    public static void main(String[] args) throws IOException {
        int puerto = 6000;
        ServerSocket servidor = new ServerSocket(puerto); // El servidor se establece en el puerto 6000
        // Este objeto ServerSocket escucha las solicitudes de conexión de los clientes en el puerto especificado.
        System.out.println("Servidor escuchando en el puerto " + servidor.getLocalPort());

        // Espera al primer cliente que acepte
        Socket cliente1 = servidor.accept();
        System.out.println("Cliente 1 conectado");
        System.out.println("Puerto local: " + cliente1.getLocalPort());
        System.out.println("Puerto remoto: " + cliente1.getPort());

        // Espera al segundo cliente que acepte
        Socket cliente2 = servidor.accept();
        System.out.println("Cliente 2 conectado");
        System.out.println("Puerto local: " + cliente2.getLocalPort());
        System.out.println("Puerto remoto: " + cliente2.getPort());

        // Cerrar conexiones
        cliente1.close();
        cliente2.close();
        servidor.close();
    }
}
