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
