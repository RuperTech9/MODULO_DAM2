package Programacion.T03_Comunicaciones.EjemplosHilosTCP;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                ManejadorClienteTCP_Palindromo manejador = new ManejadorClienteTCP_Palindromo(cliente);
                Thread hilo = new Thread(manejador); // Crear un hilo para el manejo
                hilo.start(); // Inicia el hilo para atender al cliente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
