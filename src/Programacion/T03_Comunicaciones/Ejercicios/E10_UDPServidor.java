package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E10_UDPServidor {
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try (DatagramSocket servidor = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO);

            while (true) {
                // Recibir el objeto Numeros
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                servidor.receive(paqueteEntrada);

                // Deserializar el objeto recibido
                ByteArrayInputStream bais = new ByteArrayInputStream(bufferEntrada);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros numeros = (Numeros) ois.readObject();
                System.out.println("Servidor recibió: " + numeros);

                // Verificar si el número es <= 0 para finalizar
                if (numeros.getNumero() <= 0) {
                    System.out.println("Número <= 0 recibido. Finalizando servidor.");
                    break;
                }

                // Calcular cuadrado y cubo
                numeros.setCuadrado((long) Math.pow(numeros.getNumero(), 2));
                numeros.setCubo((long) Math.pow(numeros.getNumero(), 3));

                // Serializar el objeto modificado
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numeros);
                byte[] bufferSalida = baos.toByteArray();

                // Enviar el objeto modificado al cliente
                InetAddress direccionCliente = paqueteEntrada.getAddress();
                int puertoCliente = paqueteEntrada.getPort();
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
                servidor.send(paqueteSalida);
                System.out.println("Servidor envió: " + numeros);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
