package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E03_TCPServidorCuadrado {
    public static void main(String[] args) {
        int puerto = 6000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Espera la conexión del cliente
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");

            // Flujos de entrada y salida
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            // Recibe el número del cliente
            int numero = entrada.readInt();
            System.out.println("Número recibido del cliente: " + numero);

            // Calcula el cuadrado del número y lo envía al cliente
            int cuadrado = numero * numero;
            System.out.println("Enviando el cuadrado del número: " + cuadrado);
            salida.writeInt(cuadrado);

            // Cerrar conexiones
            entrada.close();
            salida.close();
            cliente.close();
            System.out.println("Conexión cerrada");

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}