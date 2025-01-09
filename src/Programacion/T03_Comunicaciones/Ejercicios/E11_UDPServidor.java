package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E11_UDPServidor {
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        // Inicialización del array de alumnos
        Curso curso1 = new Curso("C1", "Matemáticas");
        Curso curso2 = new Curso("C2", "Historia");
        Curso curso3 = new Curso("C3", "Física");
        Curso curso4 = new Curso("C4", "Química");
        Curso curso5 = new Curso("C5", "Literatura");

        Alumno[] alumnos = {
                new Alumno("A1", "Juan", curso1, 85),
                new Alumno("A2", "María", curso2, 90),
                new Alumno("A3", "Pedro", curso3, 78),
                new Alumno("A4", "Lucía", curso4, 88),
                new Alumno("A5", "Carlos", curso5, 92)
        };

        try (DatagramSocket servidor = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO);

            while (true) {
                // Recibir el idAlumno
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                servidor.receive(paqueteEntrada);

                String idAlumno = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("ID Alumno solicitado: " + idAlumno);

                // Buscar el alumno correspondiente
                Alumno respuesta = null;
                for (Alumno alumno : alumnos) {
                    if (alumno.getIdAlumno().equalsIgnoreCase(idAlumno)) {
                        respuesta = alumno;
                        break;
                    }
                }

                // Si no existe el alumno
                if (respuesta == null) {
                    respuesta = new Alumno("N/A", "No existe", new Curso("N/A", "N/A"), 0);
                }

                // Serializar el objeto respuesta
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(respuesta);
                byte[] bufferSalida = baos.toByteArray();

                // Enviar el objeto al cliente
                InetAddress direccionCliente = paqueteEntrada.getAddress();
                int puertoCliente = paqueteEntrada.getPort();
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
                servidor.send(paqueteSalida);
                System.out.println("Enviado: " + respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
