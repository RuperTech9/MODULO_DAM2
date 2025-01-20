package Programacion.T03_Comunicaciones.Practica;

import java.io.*;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private final Socket cliente;
    private final int id;
    private final String[][] tablero;
    private int intentos = 0;
    private int premiosGanados = 0;

    public ManejadorCliente(Socket cliente, int id, String[][] tablero) {
        this.cliente = cliente;
        this.id = id;
        this.tablero = tablero;
    }

    @Override
    public void run() {
        try {
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            // Enviar ID y estado del juego al cliente
            salida.writeInt(id);
            if (!quedanPremios()) {
                salida.writeUTF("No quedan premios en el tablero. Finalizando conexión.");
                cliente.close();
                System.out.println("Cliente desconectado -> " + id);
                return;
            }
            salida.writeUTF("Quedan premios. Puedes jugar.");

            while (intentos < 4 && quedanPremios()) {
                // Leer fila y columna del cliente
                int fila = entrada.readInt() - 1;
                int columna = entrada.readInt() - 1;

                // Buscar premio en la posición
                String resultado = buscarPremio(fila, columna);
                if (resultado.startsWith("Premio encontrado")) {
                    premiosGanados++;
                }
                intentos++;

                // Enviar resultado al cliente
                salida.writeUTF(resultado);
                salida.writeInt(intentos);
                salida.writeInt(premiosGanados);
            }

            if (!quedanPremios()) {
                salida.writeUTF("El juego ha terminado. No quedan premios.");
            } else if (intentos >= 4) {
                salida.writeUTF("Has agotado tus intentos. Finalizando conexión.");
            }

            cliente.close();
            System.out.println("Cliente desconectado -> " + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized String buscarPremio(int fila, int columna) {
        if (fila < 0 || fila >= 3 || columna < 0 || columna >= 4) {
            return "Posición incorrecta";
        }
        if (tablero[fila][columna] != null) {
            String premio = tablero[fila][columna];
            tablero[fila][columna] = null; // Eliminar premio
            return "Premio encontrado: " + premio;
        } else {
            return "No hay premio en esta posición";
        }
    }

    private synchronized boolean quedanPremios() {
        for (String[] fila : tablero) {
            for (String premio : fila) {
                if (premio != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
