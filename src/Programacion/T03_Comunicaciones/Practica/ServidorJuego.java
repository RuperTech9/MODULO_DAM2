package Programacion.T03_Comunicaciones.Practica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ServidorJuego {
    private static final int PUERTO = 12345;
    private static final String[][] tablero = new String[3][4];
    private static final AtomicInteger clienteID = new AtomicInteger(1);

    public static void main(String[] args) {
        inicializarTablero();

        System.out.println("Servidor iniciado...");
        System.out.println("Posiciones de los premios:");
        mostrarTablero();

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            while (true) {
                System.out.println("Esperando clientes...");
                Socket cliente = servidor.accept();
                int id = clienteID.getAndIncrement();
                System.out.println("Cliente conectado -> " + id);

                // Crear y ejecutar un hilo para manejar al cliente
                Thread hilo = new Thread(new ManejadorCliente(cliente, id, tablero));
                hilo.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void inicializarTablero() {
        tablero[0][0] = "Crucero";
        tablero[1][2] = "Entradas";
        tablero[2][0] = "Masaje";
        tablero[2][3] = "1000€";
    }

    private static void mostrarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null) {
                    System.out.println("[" + (i + 1) + ", " + (j + 1) + "] -> " + tablero[i][j]);
                }
            }
        }
    }
}
