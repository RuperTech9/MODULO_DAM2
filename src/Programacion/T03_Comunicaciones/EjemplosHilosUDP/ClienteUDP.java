package Programacion.T03_Comunicaciones.EjemplosHilosUDP;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClienteUDP {
    private static final String HOST = "localhost";
    private static final int PUERTO = 11223;

    public static void main(String[] args) {
        try (DatagramSocket cliente = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            InetAddress direccionServidor = InetAddress.getByName(HOST);

            byte[] bufferEntrada = new byte[1024];
            String mensaje;

            while (true) {
                System.out.print("Introduce una cadena (* para salir): ");
                mensaje = sc.nextLine();

                // Enviar mensaje al servidor
                byte[] bufferSalida = mensaje.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, PUERTO);
                cliente.send(paqueteSalida);

                // Finalizar si el mensaje es asterisco (*)
                if (mensaje.equals("*")) {
                    System.out.println("Finalizando conexión con el servidor.");
                    break;
                }

                // Recibir respuesta del servidor
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                cliente.receive(paqueteEntrada);

                String respuesta = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("Servidor respondió: " + respuesta);
            }

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}