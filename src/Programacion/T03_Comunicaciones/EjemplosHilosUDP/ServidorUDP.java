package Programacion.T03_Comunicaciones.EjemplosHilosUDP;

import java.net.*;
import java.io.*;

public class ServidorUDP {
    private static final int PUERTO = 11223;

    public static void main(String[] args) {
        try (DatagramSocket servidor = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO);

            byte[] buffer = new byte[1024];

            while (true) {
                // Recibir datagrama del cliente
                DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
                servidor.receive(paqueteEntrada);

                // Crear y ejecutar un hilo para manejar al cliente
                ManejadorClienteUDP_EliminarVocales manejador = new ManejadorClienteUDP_EliminarVocales(servidor, paqueteEntrada);
                Thread hilo = new Thread(manejador);
                hilo.start();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}