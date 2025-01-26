package Programacion.T03_Comunicaciones.EjemplosHilosTCP;

import java.io.*;
import java.net.*;

public class ManejadorCliente_CifradoCesar implements Runnable {
    private Socket cliente;

    public ManejadorCliente_CifradoCesar(Socket cliente) {
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

                StringBuilder cifrado = new StringBuilder();
                for (char c : mensaje.toCharArray()) {
                    if (Character.isLetter(c)) {
                        char base = Character.isLowerCase(c) ? 'a' : 'A';
                        c = (char) ((c - base + 3) % 26 + base);
                    }
                    cifrado.append(c);
                }
                System.out.println("Procesado: " + cifrado);

                salida.writeUTF(cifrado.toString());
            }

            entrada.close();
            salida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
