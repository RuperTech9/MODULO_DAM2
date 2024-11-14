package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.IOException;
import java.net.*;

public class E01_TCPCliente {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 6000;

        // Abrir socket
        Socket cliente = new Socket(host, puerto);

        // Mostrar información de la conexión
        InetAddress direccion = cliente.getInetAddress();
        System.out.println("Puerto local: " + cliente.getLocalPort());
        System.out.println("Puerto remoto: " + cliente.getPort());
        System.out.println("Host remoto: " + direccion.getHostName());
        System.out.println("IP host remoto: " + direccion.getHostAddress());

        // Cerrar conexión
        cliente.close();
    }
}
