package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class E06_UDPServidorMayusculas {
    public static void main(String[] args) {
        int puerto = 6000; // Puerto del servidor

        try (DatagramSocket servidor = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP escuchando en el puerto " + puerto);

            while (true) {
                // Crear buffer para recibir el mensaje
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);

                // Recibir paquete
                servidor.receive(paqueteRecibido);
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                // Terminar si se recibe un asterisco
                if (mensaje.equals("*")) {
                    System.out.println("Conexión finalizada por el cliente.");
                    break;
                }

                // Convertir mensaje a mayúsculas
                String respuesta = mensaje.toUpperCase();
                byte[] respuestaBytes = respuesta.getBytes();

                // Enviar respuesta al cliente
                DatagramPacket paqueteRespuesta = new DatagramPacket(
                        respuestaBytes,
                        respuestaBytes.length,
                        paqueteRecibido.getAddress(),
                        paqueteRecibido.getPort()
                );
                servidor.send(paqueteRespuesta);
                System.out.println("Mensaje enviado: " + respuesta);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
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