package Programacion.T03_Comunicaciones.Examen;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 4040;

    public static void main(String[] args) {
        try (Socket cliente = new Socket(HOST, PUERTO);
             DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
             DataInputStream entrada = new DataInputStream(cliente.getInputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);

            String mensaje;
            while (true) {
                System.out.print("Introduce una cadena (* para salir): ");
                mensaje = sc.nextLine();

                // Enviar mensaje al servidor
                salida.writeUTF(mensaje);

                // Finalizar si es asterisco (*)
                if (mensaje.equals("*")) {
                    System.out.println("Esperando respuesta final del servidor...");
                    String respuestaFinal = entrada.readUTF();
                    System.out.println("Servidor respondió: " + respuestaFinal);
                    break;
                }

                // Recibir confirmación del servidor
                String respuesta = entrada.readUTF();
                System.out.println("Servidor respondió: " + respuesta);
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
