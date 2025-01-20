package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E13_ClienteTCP {
    private static final String HOST = "localhost"; // Dirección del servidor
    private static final int PUERTO = 11223; // Puerto del servidor

    public static void main(String[] args) {
        try (Socket cliente = new Socket(HOST, PUERTO)) { // Conectar al servidor
            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);

            // Crear flujos de entrada y salida para la comunicación
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            Scanner sc = new Scanner(System.in);

            String mensaje;
            while (true) { // Bucle para enviar múltiples mensajes
                System.out.print("Introduce una cadena (* para salir): ");
                mensaje = sc.nextLine();

                // Enviar mensaje al servidor
                salida.writeUTF(mensaje);

                // Verificar si el usuario quiere terminar
                if (mensaje.equals("*")) {
                    System.out.println("Finalizando conexión con el servidor.");
                    break;
                }

                // Leer respuesta del servidor
                String respuesta = entrada.readUTF();
                System.out.println("Servidor respondió: " + respuesta);
            }

            // Cerrar flujos
            entrada.close();
            salida.close();
        } catch (IOException e) {
            e.printStackTrace(); // Manejar excepciones relacionadas con E/S
        }
    }
}
