package Programacion.T03_Comunicaciones.EjemplosHilosTCP;

import java.io.*;
import java.net.*;

public class ManejadorClienteTCP_Palindromo implements Runnable {
    private Socket cliente;

    public ManejadorClienteTCP_Palindromo(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            String mensaje;
            while (true) {
                mensaje = entrada.readUTF();
                System.out.println("Cliente envió: " + mensaje);

                if (mensaje.equals("*")) {
                    System.out.println("Cliente desconectado.");
                    break;
                }

                String limpio = mensaje.replaceAll("[^a-zA-Z]", "").toLowerCase();
                String inverso = new StringBuilder(limpio).reverse().toString();
                boolean esPalindromo = limpio.equals(inverso);
                String procesado = esPalindromo ? "Es un palíndromo" : "No es un palíndromo";
                System.out.println("Procesado: " + procesado);

                salida.writeUTF(procesado);
            }

            entrada.close();
            salida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
