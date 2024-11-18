package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E03_TCPClienteNumero {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket cliente = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Flujos de entrada y salida
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            Scanner sc = new Scanner(System.in);

            // Solicita el número al usuario
            System.out.print("Introduce un número: ");
            int numero = sc.nextInt();

            // Enviar el número al servidor
            salida.writeInt(numero);
            System.out.println("Número enviado al servidor: " + numero);

            // Cuadrado del número del servidor
            int cuadrado = entrada.readInt();
            System.out.println("Cuadrado recibido del servidor: " + cuadrado);

            // Cierro conexiones
            entrada.close();
            salida.close();
            sc.close();

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}