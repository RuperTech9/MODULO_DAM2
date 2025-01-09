package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E10_UDPCliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try (DatagramSocket cliente = new DatagramSocket()) {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Introduce un número (<= 0 para salir): ");
                int numero = sc.nextInt();

                // Crear objeto Numeros
                Numeros numeros = new Numeros(numero);
                System.out.println("Cliente envía: " + numeros);

                // Serializar el objeto
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numeros);
                byte[] bufferSalida = baos.toByteArray();

                // Enviar el objeto al servidor
                InetAddress direccionServidor = InetAddress.getByName(HOST);
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, PUERTO);
                cliente.send(paqueteSalida);

                // Finalizar si el número es <= 0
                if (numero <= 0) {
                    System.out.println("Número <= 0 enviado. Finalizando cliente.");
                    break;
                }

                // Recibir el objeto modificado del servidor
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                cliente.receive(paqueteEntrada);

                // Deserializar el objeto recibido
                ByteArrayInputStream bais = new ByteArrayInputStream(bufferEntrada);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros numerosModificados = (Numeros) ois.readObject();
                System.out.println("Cliente recibe: " + numerosModificados);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
