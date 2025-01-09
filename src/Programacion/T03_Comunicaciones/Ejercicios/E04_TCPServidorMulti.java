package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E04_TCPServidorMulti {
    public static void main(String[] args) {
        int puerto = 6000;
        int CLIENTES = 3; // Máximo número de clientes a atender

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            for (int i = 1; i <= CLIENTES; i++) {
                System.out.println("Esperando al cliente " + i + "...");

                // Aceptar la conexión del cliente
                Socket cliente = servidor.accept();
                System.out.println("\nCliente " + i + " conectado");

                // Atender al cliente
                atenderCliente(cliente, i);

                // Cerrar la conexión con el cliente
                cliente.close();
                System.out.println("Conexión con cliente " + i + " cerrada");
            }

            System.out.println("\nClientes atendidos: " + CLIENTES);

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Metodo para atender a un cliente específico
    private static void atenderCliente(Socket cliente, int clienteNumero) {
        try (DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
             DataInputStream entrada = new DataInputStream(cliente.getInputStream())) {

            // Enviar mensaje al cliente indicando su número
            String mensaje = "Hola número " + clienteNumero;
            salida.writeUTF(mensaje);
            System.out.println("Mensaje enviado al cliente " + clienteNumero + ": " + mensaje);

            // Leer la respuesta del cliente
            String respuestaCliente = entrada.readUTF();
            System.out.println("Respuesta del cliente " + clienteNumero + ": " + respuestaCliente);

        } catch (IOException e) {
            System.err.println("Error al atender al cliente " + clienteNumero + ": " + e.getMessage());
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