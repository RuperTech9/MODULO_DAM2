package Programacion.T03_Comunicaciones.Examen;

import java.io.*;
import java.net.*;

public class ServidorMultihilo {
    private static final int PUERTO = 4040;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor TCP escuchando en el puerto " + PUERTO);

            while (true) {
                System.out.println("Esperando clientes...");
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                // Crear y ejecutar un hilo para manejar al cliente
                ManejadorCliente manejador = new ManejadorCliente(cliente);
                Thread hilo = new Thread(manejador); // Crear un hilo para el manejo
                hilo.start(); // Inicia el hilo para atender al cliente
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}