package Programacion.T03_Comunicaciones.EjemplosHilosUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ManejadorClienteUDP_InvertirCadena implements Runnable {
    private DatagramSocket servidor;
    private DatagramPacket paqueteEntrada;

    public ManejadorClienteUDP_InvertirCadena(DatagramSocket servidor, DatagramPacket paqueteEntrada) {
        this.servidor = servidor;
        this.paqueteEntrada = paqueteEntrada;
    }

    @Override
    public void run() {
        try {
            // Leer mensaje del cliente
            String mensaje = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
            System.out.println("Cliente envió: " + mensaje);

            // Lógica: Invertir la cadena
            String procesado = new StringBuilder(mensaje).reverse().toString();
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