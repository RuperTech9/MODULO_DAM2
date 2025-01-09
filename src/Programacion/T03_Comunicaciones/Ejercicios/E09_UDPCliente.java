package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E09_UDPCliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try (DatagramSocket cliente = new DatagramSocket()) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Introduce el nombre de la persona: ");
            String nombre = sc.nextLine();
            System.out.print("Introduce la edad de la persona: ");
            int edad = sc.nextInt();

            // Crear objeto Persona
            Persona persona = new Persona(nombre, edad);
            System.out.println("Cliente envía: " + persona);

            // Serializar el objeto
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);
            byte[] bufferSalida = baos.toByteArray();

            // Enviar el objeto al servidor
            InetAddress direccionServidor = InetAddress.getByName(HOST);
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, PUERTO);
            cliente.send(paqueteSalida);

            // Recibir el objeto modificado del servidor
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            cliente.receive(paqueteEntrada);

            // Deserializar el objeto recibido
            ByteArrayInputStream bais = new ByteArrayInputStream(bufferEntrada);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona personaModificada = (Persona) ois.readObject();
            System.out.println("Cliente recibe: " + personaModificada);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
