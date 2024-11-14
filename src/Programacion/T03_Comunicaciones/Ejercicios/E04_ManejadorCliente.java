package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;


// Clase para manejar cada cliente en un hilo
public class E04_ManejadorCliente implements Runnable {
    private Socket cliente;
    private int numeroCliente;

    public E04_ManejadorCliente(Socket cliente, int numeroCliente) {
        this.cliente = cliente;
        this.numeroCliente = numeroCliente;
    }

    @Override
    public void run() {
        try (DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())) {
            // Envía el mensaje con el número de cliente
            String mensaje = "Eres el cliente número " + numeroCliente;
            salida.writeUTF(mensaje);
            System.out.println("Mensaje enviado a Cliente " + numeroCliente + ": " + mensaje);
        } catch (IOException e) {
            System.err.println("Error con el Cliente " + numeroCliente + ": " + e.getMessage());
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar la conexión del Cliente " + numeroCliente);
            }
        }
    }
}