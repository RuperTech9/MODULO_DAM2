package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E13_ManejadorCliente implements Runnable {
    private Socket cliente; // Socket que representa la conexión con el cliente

    // Constructor que recibe el socket del cliente
    public E13_ManejadorCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            // Crear flujos de entrada y salida para la comunicación
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            String mensaje;
            while (true) { // Bucle para manejar múltiples mensajes
                // Leer mensaje enviado por el cliente
                mensaje = entrada.readUTF();
                System.out.println("Cliente envió: " + mensaje);

                // Verificar si el cliente quiere desconectarse
                if (mensaje.equals("*")) {
                    System.out.println("Cliente desconectado.");
                    break;
                }

                // Procesar cadena (eliminar vocales)
                String procesado = mensaje.replaceAll("[aeiouAEIOU]", "");
                System.out.println("Procesado: " + procesado);

                // Enviar el mensaje procesado al cliente
                salida.writeUTF(procesado);
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
