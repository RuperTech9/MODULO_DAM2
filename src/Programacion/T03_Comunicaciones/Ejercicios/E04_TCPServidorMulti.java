package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E04_TCPServidorMulti {
    public static void main(String[] args) {
        int puerto = 6000;
        int N = 3; // Número máximo de clientes que puede atender el servidor

        // Crear un pool de hilos para manejar múltiples clientes
        ExecutorService pool = Executors.newFixedThreadPool(N);

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);
            System.out.println("Esperando hasta " + N + " clientes...");

            for (int i = 1; i <= N; i++) {
                // Aceptar una conexión del cliente
                Socket cliente = servidor.accept();
                int clienteNumero = i; // Guardar el número del cliente
                System.out.println("\nCliente " + clienteNumero + " conectado");

                // Manejar al cliente en un hilo separado
                pool.execute(() -> atenderCliente(cliente, clienteNumero));
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    // Metodo para atender a cada cliente
    private static void atenderCliente(Socket cliente, int clienteNumero) {
        try (DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
             DataInputStream entrada = new DataInputStream(cliente.getInputStream())) {

            // Enviar mensaje al cliente
            String mensaje = "Cliente número " + clienteNumero;
            salida.writeUTF(mensaje);
            System.out.println("Mensaje enviado al cliente " + clienteNumero + ": " + mensaje);

            // Leer mensaje del cliente (si aplica)
            String respuestaCliente = entrada.readUTF();
            System.out.println("Respuesta del cliente " + clienteNumero + ": " + respuestaCliente);

        } catch (IOException e) {
            System.err.println("Error al atender al cliente " + clienteNumero + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
                System.out.println("Conexión con cliente " + clienteNumero + " cerrada");
            } catch (IOException e) {
                System.err.println("Error al cerrar la conexión con el cliente " + clienteNumero + ": " + e.getMessage());
            }
        }
    }
}