package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E11_UDPCliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try (DatagramSocket cliente = new DatagramSocket()) {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Introduce el ID del Alumno (* para salir): ");
                String idAlumno = sc.nextLine();

                // Finalizar si se ingresa "*"
                if (idAlumno.equals("*")) {
                    System.out.println("Finalizando cliente.");
                    break;
                }

                // Enviar el idAlumno al servidor
                byte[] bufferSalida = idAlumno.getBytes();
                InetAddress direccionServidor = InetAddress.getByName(HOST);
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, PUERTO);
                cliente.send(paqueteSalida);

                // Recibir el objeto Alumno del servidor
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                cliente.receive(paqueteEntrada);

                // Deserializar el objeto recibido
                ByteArrayInputStream bais = new ByteArrayInputStream(bufferEntrada);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Alumno alumno = (Alumno) ois.readObject();
                System.out.println("Datos del Alumno: " + alumno);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
