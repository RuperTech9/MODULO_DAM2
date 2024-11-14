package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E04_TCPClienteMulti {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket cliente = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Crear flujo de entrada para recibir el mensaje del servidor
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());

            // Leer el mensaje recibido del servidor
            String mensaje = entrada.readUTF();
            System.out.println("Mensaje recibido del servidor: " + mensaje);

            // Cerrar conexiones
            entrada.close();
            System.out.println("Conexión cerrada");

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}