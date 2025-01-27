package Programacion.T03_Comunicaciones.EjemplosHilosTCP;

import java.io.*;
import java.net.*;

public class ManejadorClienteTCP_ConteoVocalesConsonantes implements Runnable {
    private Socket cliente;

    public ManejadorClienteTCP_ConteoVocalesConsonantes(Socket cliente) {
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

                int vocales = mensaje.replaceAll("[^aeiouAEIOU]", "").length();
                int consonantes = mensaje.replaceAll("[^a-zA-Z]", "").length() - vocales;
                String procesado = "Vocales: " + vocales + ", Consonantes: " + consonantes;
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
