package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E05_TCPClienteAsterisco {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket cliente = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Flujos de entrada y salida
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            Scanner sc = new Scanner(System.in);

            // Enviar cadenas hasta introducir un asterisco
            String cadena;
            while (true) {
                System.out.print("Introduce una cadena ('*' para salir): ");
                cadena = sc.nextLine();

                // Enviar el cadena al servidor
                salida.writeUTF(cadena);

                // Terminar si el cadena es un asterisco
                if (cadena.equals("*")) {
                    System.out.println("Terminando la conexión.");
                    break;
                }

                // Leer la respuesta del servidor y mostrarla
                String respuesta = entrada.readUTF();
                System.out.println("Respuesta del servidor: " + respuesta);
            }

            // Cerrar conexiones
            entrada.close();
            salida.close();
            sc.close();


        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
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