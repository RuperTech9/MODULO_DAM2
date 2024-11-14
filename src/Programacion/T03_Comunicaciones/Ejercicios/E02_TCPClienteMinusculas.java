package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E02_TCPClienteMinusculas {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket cliente = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Flujos de entrada y salida
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            // Recibir el mensaje en mayúsculas del servidor
            String mensajeServidor = entrada.readUTF();
            System.out.println("Mensaje recibido del servidor en mayúsculas: " + mensajeServidor);

            // Convertir a minúsculas y enviar de vuelta al servidor
            String mensajeEnMinusculas = mensajeServidor.toLowerCase();
            salida.writeUTF(mensajeEnMinusculas);
            System.out.println("Mensaje enviado en minúsculas al servidor: " + mensajeEnMinusculas);

            // Cerrar conexiones
            entrada.close();
            salida.close();
            System.out.println("Conexión cerrada");

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}