package Programacion.T03_Comunicaciones.EjemplosHilosUDP;

import java.net.*;
import java.io.*;

public class ManejadorClienteUDP_EliminarVocales implements Runnable {
    private DatagramSocket servidor;
    private DatagramPacket paqueteEntrada;

    public ManejadorClienteUDP_EliminarVocales(DatagramSocket servidor, DatagramPacket paqueteEntrada) {
        this.servidor = servidor;
        this.paqueteEntrada = paqueteEntrada;
    }

    @Override
    public void run() {
        try {
            // Leer mensaje del cliente
            String mensaje = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
            System.out.println("Cliente envió: " + mensaje);

            // Procesar mensaje (eliminar vocales)
            String procesado = mensaje.replaceAll("[aeiouAEIOU]", "");
            System.out.println("Procesado: " + procesado);

            // Preparar respuesta
            byte[] bufferSalida = procesado.getBytes();
            InetAddress direccionCliente = paqueteEntrada.getAddress();
            int puertoCliente = paqueteEntrada.getPort();

            // Enviar respuesta al cliente
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
            servidor.send(paqueteSalida);

        } catch (IOException e) {
            System.err.println("Error al procesar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}