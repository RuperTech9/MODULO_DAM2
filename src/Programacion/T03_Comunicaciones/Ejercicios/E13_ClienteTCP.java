package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E13_ClienteTCP {
    private static final String HOST = "localhost"; // Dirección del servidor
    private static final int PUERTO = 11223; // Puerto del servidor

    public static void main(String[] args) {
        try (Socket cliente = new Socket(HOST, PUERTO)) { // Crea un socket cliente conectado al servidor
            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO); // Informa que la conexión fue exitosa

            // Inicializa los flujos para enviar y recibir datos
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream()); // Flujo para enviar datos al servidor
            DataInputStream entrada = new DataInputStream(cliente.getInputStream()); // Flujo para recibir datos del servidor
            Scanner sc = new Scanner(System.in);

            String mensaje; // Variable para almacenar los mensajes enviados
            while (true) { // Bucle infinito para comunicarse con el servidor
                System.out.print("Introduce una cadena (* para salir): ");
                mensaje = sc.nextLine();

                salida.writeUTF(mensaje); // Envía el mensaje al servidor

                if (mensaje.equals("*")) { // Verifica si el mensaje es un asterisco
                    System.out.println("Finalizando conexión con el servidor."); // Informa que se cerrará la conexión
                    break; // Rompe el bucle para cerrar la conexión
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
