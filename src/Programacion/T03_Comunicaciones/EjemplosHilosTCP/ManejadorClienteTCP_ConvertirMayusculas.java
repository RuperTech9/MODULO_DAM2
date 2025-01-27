package Programacion.T03_Comunicaciones.EjemplosHilosTCP;

import java.io.*;
import java.net.*;

public class ManejadorClienteTCP_ConvertirMayusculas implements Runnable {
    private Socket cliente;

    public ManejadorClienteTCP_ConvertirMayusculas(Socket cliente) {
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

                String procesado = mensaje.toUpperCase();
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
