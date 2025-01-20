package Programacion.T03_Comunicaciones.Practica;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteJuego {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (Socket cliente = new Socket(HOST, PUERTO)) {
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            Scanner sc = new Scanner(System.in);

            // Recibir ID y estado del juego
            int id = entrada.readInt();
            String mensaje = entrada.readUTF();
            System.out.println("ID Cliente: " + id);
            System.out.println(mensaje);

            if (mensaje.startsWith("No quedan premios")) {
                return;
            }

            while (true) {
                System.out.print("Introduce la fila (1-3): ");
                int fila = sc.nextInt();
                System.out.print("Introduce la columna (1-4): ");
                int columna = sc.nextInt();

                // Enviar fila y columna al servidor
                salida.writeInt(fila);
                salida.writeInt(columna);

                // Recibir resultado
                String resultado = entrada.readUTF();
                int intentos = entrada.readInt();
                int premiosGanados = entrada.readInt();

                System.out.println("Resultado: " + resultado);
                System.out.println("Intentos: " + intentos + "/4, Premios ganados: " + premiosGanados);

                // Finalizar si se alcanzan los intentos o no quedan premios
                if (resultado.startsWith("El juego ha terminado") || resultado.startsWith("Has agotado")) {
                    System.out.println(resultado);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
