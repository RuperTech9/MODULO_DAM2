package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E05_TCPClienteEcho {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket cliente = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Flujos de entrada y salida
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            Scanner scanner = new Scanner(System.in);

            // Enviar cadenas hasta introducir un asterisco
            String mensaje;
            while (true) {
                System.out.print("Introduce una cadena (o '*' para salir): ");
                mensaje = scanner.nextLine();

                // Enviar el mensaje al servidor
                salida.writeUTF(mensaje);

                // Terminar si el mensaje es un asterisco
                if (mensaje.equals("*")) {
                    System.out.println("Terminando la conexión.");
                    break;
                }

                // Leer la respuesta del servidor y mostrarla
                String respuesta = entrada.readUTF();
                System.out.println("Respuesta del servidor: " + respuesta);
            }

            // Cerrar conexiones
            entrada.close();
            salida.close();
            scanner.close();
            System.out.println("Conexión cerrada");

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}