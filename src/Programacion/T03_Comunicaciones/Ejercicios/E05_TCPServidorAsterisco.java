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

/*
Ejercicio 5: Intercambio continuo de mensajes
Objetivo:

El cliente envía cadenas al servidor de manera iterativa.
El servidor responde devolviendo las mismas cadenas.
La comunicación termina cuando el cliente envía un asterisco (*).
Explicación:

Se utiliza un bucle en el cliente para enviar mensajes continuamente.
El servidor también usa un bucle para responder a los mensajes.
Si el mensaje es "*", ambos terminan la conexión.
Aprendizaje:

Implementación de comunicaciones iterativas.
Condicionar la terminación de la conexión mediante señales (en este caso, "*").
Uso de flujos para leer y escribir datos en un ciclo continuo.
Práctica: Este patrón es esencial para sistemas de interacción continua como chats o aplicaciones de streaming.
 */