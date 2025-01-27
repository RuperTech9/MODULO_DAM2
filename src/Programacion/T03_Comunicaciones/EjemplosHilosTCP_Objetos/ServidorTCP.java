package Programacion.T03_Comunicaciones.EjemplosHilosTCP_Objetos;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    private static final int PUERTO = 11223;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor TCP escuchando en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                // Crear y ejecutar un hilo para manejar al cliente
                Thread hilo = new Thread(new ManejadorCliente(cliente));
                hilo.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}