package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E02_TCPServidorMayusculas {
    public static void main(String[] args) {
        int puerto = 6000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Espera a que se conecte el cliente
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");

            // Flujos de entrada y salida
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());

            // Enviar mensaje en mayúsculas
            String mensaje = "HOLA CLIENTE, ¿CÓMO ESTÁS?";
            System.out.println("Enviando mensaje en mayúsculas: " + mensaje);
            salida.writeUTF(mensaje);

            // Recibir el mensaje en minúsculas del cliente
            String respuestaCliente = entrada.readUTF();
            System.out.println("Mensaje recibido en minúsculas del cliente: " + respuestaCliente);

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