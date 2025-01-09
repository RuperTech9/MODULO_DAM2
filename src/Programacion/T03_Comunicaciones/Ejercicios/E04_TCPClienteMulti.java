package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E04_TCPClienteMulti {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket socket = new Socket(host, puerto);
             DataInputStream entrada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream())) {

            // Recibir mensaje del servidor
            String mensajeServidor = entrada.readUTF();
            System.out.println("Mensaje del servidor: " + mensajeServidor);

            // Responder al servidor
            String mensajeCliente = "Listo!";
            salida.writeUTF(mensajeCliente);

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

/*
Ejercicio 4: Servidor Multicliente con TCP
Objetivo:

Crear un servidor que acepte conexiones de múltiples clientes (N, definido previamente).
Enviar un mensaje personalizado a cada cliente indicando su número de cliente.
Explicación:

Se usa un bucle en el servidor para aceptar conexiones secuenciales hasta alcanzar el límite de N clientes.
Para cada cliente conectado:
Se envía un mensaje único (e.g., "Eres el cliente 1").
El cliente puede responder si es necesario.
Se cierran las conexiones al finalizar.
Aprendizaje:

Gestión de múltiples clientes usando bucles.
Comprensión de cómo diferenciar clientes en un entorno servidor único.
Práctica: Esto es la base para cualquier servidor que necesite atender a más de un cliente.
 */