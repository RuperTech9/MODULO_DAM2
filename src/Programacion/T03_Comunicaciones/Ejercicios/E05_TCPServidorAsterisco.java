package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E05_TCPServidorAsterisco {
    public static void main(String[] args) {
        int puerto = 6000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Espera la conexión del cliente
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");

            // Flujos de entrada y salida
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            // Leer mensajes del cliente hasta que envíe un asterisco
            String mensaje;
            while (true) {
                mensaje = entrada.readUTF();
                if (mensaje.equals("*")) {
                    System.out.println("Cliente ha terminado la conexión.");
                    break;
                }

                // Mostrar el mensaje recibido y enviarlo de vuelta al cliente
                System.out.println("Mensaje recibido: " + mensaje);
                salida.writeUTF(mensaje);
            }

            // Cerrar conexiones
            entrada.close();
            salida.close();
            cliente.close();

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}