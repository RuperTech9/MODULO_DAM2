package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E13_ManejadorCliente implements Runnable {
    private Socket cliente; // Socket asociado al cliente conectado

    // Constructor que recibe el socket del cliente
    public E13_ManejadorCliente(Socket cliente) {
        this.cliente = cliente; // Asigna el socket recibido al atributo de la clase
    }

    @Override
    public void run() {
        try {
            // Inicializa los flujos para recibir y enviar datos
            DataInputStream entrada = new DataInputStream(cliente.getInputStream()); // Flujo para leer datos del cliente
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream()); // Flujo para enviar datos al cliente

            String mensaje; // Variable para almacenar los mensajes recibidos
            while (true) { // Bucle infinito para manejar la comunicación con el cliente
                mensaje = entrada.readUTF(); // Lee un mensaje enviado por el cliente
                System.out.println("Cliente envió: " + mensaje); // Muestra el mensaje recibido

                if (mensaje.equals("*")) { // Verifica si el mensaje es un asterisco
                    System.out.println("Cliente desconectado."); // Informa que el cliente cerrará la conexión
                    break; // Sale del bucle para cerrar la conexión
                }

                String procesado = mensaje.replaceAll("[aeiouAEIOU]", ""); // Procesa el mensaje eliminando las vocales
                System.out.println("Procesado: " + procesado); // Muestra el mensaje procesado

                salida.writeUTF(procesado); // Envía el mensaje procesado de vuelta al cliente
            }

            // Cerrar flujos y conexión
            entrada.close();
            salida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace(); // Manejar excepciones relacionadas con E/S
        }
    }
}
