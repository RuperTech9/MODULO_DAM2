package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E04_TCPClienteMulti {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket socket = new Socket(host, puerto);
             DataInputStream entrada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream())) {

            // Recibir mensaje del servidor
            String mensajeServidor = entrada.readUTF();
            System.out.println("Mensaje del servidor: " + mensajeServidor);

            // Responder al servidor
            String mensajeCliente = "Listo!";
            salida.writeUTF(mensajeCliente);

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}