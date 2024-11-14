package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E04_TCPServidorMultiCliente {
    public static void main(String[] args) {
        int puerto = 6000;
        int maxClientes = 3; // Cambia este valor para definir N clientes permitidos
        ExecutorService pool = Executors.newFixedThreadPool(maxClientes);

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            int contadorClientes = 0;
            while (contadorClientes < maxClientes) {
                // Espera a un cliente y lanza un nuevo hilo para atenderlo
                Socket cliente = servidor.accept();
                contadorClientes++;
                System.out.println("Cliente " + contadorClientes + " conectado");
                pool.execute(new E04_ManejadorCliente(cliente, contadorClientes));
            }

            pool.shutdown();  // Cierra el pool una vez que se han atendido todos los clientes
            System.out.println("Se ha alcanzado el máximo de clientes: " + maxClientes);
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}