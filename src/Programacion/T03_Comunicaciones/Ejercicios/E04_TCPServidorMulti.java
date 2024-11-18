package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E04_TCPServidorMulti {
    public static void main(String[] args) {
        int puerto = 6000;
        int maxClientes = 3; // Máximo número de clientes a atender

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            for (int i = 1; i <= maxClientes; i++) {
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

            System.out.println("\nClientes atendidos: " + maxClientes);

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