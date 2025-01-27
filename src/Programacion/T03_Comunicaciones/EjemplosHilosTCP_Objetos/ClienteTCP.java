package Programacion.T03_Comunicaciones.EjemplosHilosTCP_Objetos;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP {
    private static final String HOST = "localhost";
    private static final int PUERTO = 11223;

    public static void main(String[] args) {
        try (Socket cliente = new Socket(HOST, PUERTO);
             ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);

            String comando;
            while (true) {
                // Leer comando y contenido desde el teclado
                System.out.print("Introduce un comando (invertir, mayusculas, palindromo, salir): ");
                comando = sc.nextLine();

                if (comando.equalsIgnoreCase("salir")) {
                    System.out.println("Desconectando del servidor...");
                    Mensaje mensaje = new Mensaje(comando, "");
                    salida.writeObject(mensaje);
                    break;
                }

                System.out.print("Introduce el contenido: ");
                String contenido = sc.nextLine();

                // Crear y enviar objeto Mensaje
                Mensaje mensaje = new Mensaje(comando, contenido);
                salida.writeObject(mensaje);

                // Recibir respuesta del servidor
                Mensaje respuesta = (Mensaje) entrada.readObject();
                System.out.println("Servidor respondió: " + respuesta.getRespuesta());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}