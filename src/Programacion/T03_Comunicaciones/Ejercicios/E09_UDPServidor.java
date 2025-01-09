package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E09_UDPServidor {
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try (DatagramSocket servidor = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO);

            while (true) {
                // Recibir el objeto Persona
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                servidor.receive(paqueteEntrada);

                // Deserializar el objeto recibido
                ByteArrayInputStream bais = new ByteArrayInputStream(bufferEntrada);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Persona persona = (Persona) ois.readObject();
                System.out.println("Servidor recibió: " + persona);

                // Modificar el objeto Persona
                persona.setNombre(persona.getNombre() + " Modificado");
                persona.setEdad(persona.getEdad() + 10);
                System.out.println("Servidor envía: " + persona);

                // Serializar el objeto modificado
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(persona);
                byte[] bufferSalida = baos.toByteArray();

                // Enviar el objeto modificado al cliente
                InetAddress direccionCliente = paqueteEntrada.getAddress();
                int puertoCliente = paqueteEntrada.getPort();
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
                servidor.send(paqueteSalida);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
