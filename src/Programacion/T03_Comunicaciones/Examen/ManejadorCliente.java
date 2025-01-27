package Programacion.T03_Comunicaciones.Examen;

/*
El cliente solicitara al usuario la entrada de cadenas hasta que el usuario introduzca un asterisco.
Las cadenas se limpiaran de espacios y se enviaran al servidor. El servidor ira almacenando las cadenas en un Arraylist.
Cuando el cliente envie la ultima cadena el servidor ordenara el ArrayList por orden alfabetico inverso y devolvera en una unica cadena el contenido del ArrayList resultante
 */

import java.io.*;
import java.net.*;
import java.util.*;

// Clase ManejadorCliente
public class ManejadorCliente implements Runnable {
    private Socket cliente; // Socket para la conexión con el cliente
    private static final ArrayList<String> listaCadenas = new ArrayList<>(); // // Lista para almacenar cadenas de los clientes.

    // Constructor
    public ManejadorCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try (DataInputStream entrada = new DataInputStream(cliente.getInputStream()); // Flujo de entrada para leer datos del cliente.
             DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())) { // Flujo de salida para enviar datos al cliente.

            String mensaje; // Variable para almacenar los mensajes enviados por el cliente.
            while (true) {
                // Leer cadena del cliente
                mensaje = entrada.readUTF().trim(); // .trim para quitar espacios
                System.out.println("Cliente envió: " + mensaje);

                // Verificar si es asterisco (*) para finalizar
                if (mensaje.equals("*")) {
                    synchronized (listaCadenas) { // synchronized para evitar conflictos en accesos concurrentes.
                        System.out.println("Cliente finalizó, ordenando cadenas...");
                        Collections.sort(listaCadenas, Collections.reverseOrder()); // Ordenar alfabéticamente en orden inverso

                        // Concatenar cadenas para la respuesta
                        StringBuilder respuesta = new StringBuilder();
                        for (String cadena : listaCadenas) {
                            respuesta.append(cadena).append(" "); // Concatenar cada cadena con un espacio.
                        }

                        // Envio la respuesta al cliente
                        salida.writeUTF(respuesta.toString().trim());
                        listaCadenas.clear(); // Limpio la lista para el siguiente cliente
                    }
                    break;
                }

                // Añado la cadena a la lista (ya sin espacios)
                synchronized (listaCadenas) { // synchronized para evitar conflictos en accesos concurrentes.
                    listaCadenas.add(mensaje.replaceAll(" ", ""));
                }
                salida.writeUTF("Cadena recibida: " + mensaje); // Confirmación del cliente
            }

        } catch (IOException e) {
            System.err.println("Error al manejar cliente: " + e.getMessage());
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


