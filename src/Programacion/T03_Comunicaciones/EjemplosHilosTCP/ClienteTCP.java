package Programacion.T03_Comunicaciones.EjemplosHilosTCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (Socket cliente = new Socket(HOST, PUERTO)) {
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            Scanner sc = new Scanner(System.in);

            String mensaje;
            while (true) {
                System.out.print("Introduce una cadena (* para salir): ");
                mensaje = sc.nextLine();

                salida.writeUTF(mensaje);

                if (mensaje.equals("*")) {
                    System.out.println("Finalizando conexión con el servidor.");
                    break;
                }

                String respuesta = entrada.readUTF();
                System.out.println("Servidor respondió: " + respuesta);
            }

            entrada.close();
            salida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
