package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E08_TCPCliente {
    public static void main(String[] args) {
        String HOST = "localhost";
        int PUERTO = 6000;

        try (Socket cliente = new Socket(HOST, PUERTO)) {
            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);

            // Flujos para objetos
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Introduce un número (<= 0 para salir): ");
                int numero = sc.nextInt();

                // Crear objeto Numeros
                Numeros numeros = new Numeros(numero);

                // Enviar objeto al servidor
                salida.writeObject(numeros);
                salida.flush();

                // Finalizar si el número es <= 0
                if (numero <= 0) {
                    System.out.println("Número <= 0 enviado. Finalizando...");
                    break;
                }

                // Recibir objeto del servidor
                Numeros respuesta = (Numeros) entrada.readObject();
                System.out.println("Respuesta del servidor: " + respuesta);
            }

            // Cerrar flujos
            entrada.close();
            salida.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
