package Programacion.T03_Comunicaciones.EjemplosHilosTCP_Objetos;

import java.io.*;
import java.net.*;

public class ManejadorCliente implements Runnable {
    private Socket cliente;

    public ManejadorCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try (ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
             ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream())) {

            while (true) {
                // Leer objeto Mensaje del cliente
                Mensaje mensaje = (Mensaje) entrada.readObject();
                System.out.println("Mensaje recibido: " + mensaje);

                // Procesar el mensaje según el comando
                String respuesta;
                switch (mensaje.getComando().toLowerCase()) {
                    case "invertir":
                        respuesta = new StringBuilder(mensaje.getContenido()).reverse().toString();
                        break;
                    case "mayusculas":
                        respuesta = mensaje.getContenido().toUpperCase();
                        break;
                    case "palindromo":
                        String contenidoLimpio = mensaje.getContenido().replaceAll("[^a-zA-Z]", "").toLowerCase();
                        respuesta = contenidoLimpio.equals(new StringBuilder(contenidoLimpio).reverse().toString()) ?
                                "Es un palíndromo" : "No es un palíndromo";
                        break;
                    default:
                        respuesta = "Comando no reconocido.";
                        break;
                }

                // Preparar el objeto de respuesta
                mensaje.setRespuesta(respuesta);
                salida.writeObject(mensaje);
                System.out.println("Respuesta enviada: " + mensaje);

                // Finalizar si el cliente envía "salir"
                if (mensaje.getComando().equalsIgnoreCase("salir")) {
                    System.out.println("Cliente desconectado.");
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
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
