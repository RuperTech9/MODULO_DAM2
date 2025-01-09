package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class E06_UDPClienteMayusculas {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int puerto = 6000;        // Puerto del servidor

        try (DatagramSocket cliente = new DatagramSocket()) {
            cliente.setSoTimeout(4000); // Establecer timeout de 4000 ms
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Introduce una cadena ('*' para salir): ");
                String mensaje = sc.nextLine();

                // Enviar mensaje al servidor
                byte[] mensajeBytes = mensaje.getBytes();
                InetAddress direccionServidor = InetAddress.getByName(host);
                DatagramPacket paquete = new DatagramPacket(mensajeBytes, mensajeBytes.length, direccionServidor, puerto);
                cliente.send(paquete);

                // Terminar si se envía un asterisco
                if (mensaje.equals("*")) {
                    System.out.println("Terminando la conexión.");
                    break;
                }

                try {
                    // Recibir respuesta del servidor
                    byte[] buffer = new byte[1024];
                    DatagramPacket paqueteRespuesta = new DatagramPacket(buffer, buffer.length);
                    cliente.receive(paqueteRespuesta);

                    String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                    System.out.println("Respuesta del servidor: " + respuesta);

                } catch (SocketTimeoutException e) {
                    System.err.println("El paquete se ha perdido o el servidor no responde.");
                }
            }
        } catch (SocketException e) {
            System.err.println("Error en el cliente (SocketException): " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error en el cliente (IOException): " + e.getMessage());
            e.printStackTrace();
        }
    }
}

/*
Ejercicio 6: Comunicación UDP con manejo de tiempo
Objetivo:

El cliente envía texto al servidor mediante UDP.
El servidor devuelve el texto en mayúsculas.
El cliente maneja un timeout usando setSoTimeout para verificar si recibe respuesta en un tiempo límite.
Explicación:

Se usa DatagramSocket y DatagramPacket para enviar/recibir mensajes como datagramas.
El cliente configura un tiempo de espera con setSoTimeout.
Si el servidor no responde dentro del límite, se lanza una excepción (SocketTimeoutException), indicando que el paquete se perdió.
El cliente y servidor terminan la conexión cuando se envía un asterisco.
Aprendizaje:

Configurar y manejar el tiempo de espera para evitar bloqueos indefinidos en comunicaciones UDP.
Diferencias clave entre UDP (no orientado a conexión) y TCP (orientado a conexión).
Gestión de errores y casos de pérdida de datos.
 */