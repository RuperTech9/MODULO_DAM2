package AccesoADatos.T01_Ficheros;

import java.io.*;
import java.util.Scanner;

public class E24_BorrarEmpleadoAleatorio {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/PruebaAleatorioEmpl.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el ID del empleado a borrar: ");
            int idEliminar = sc.nextInt();

            long posicion = (idEliminar - 1) * 36; // Calcular la posición del registro

            if (posicion >= file.length()) {
                System.out.println("El empleado con ID " + idEliminar + " no existe.");
            } else {
                file.seek(posicion); // Posicionarse en el registro
                int id = file.readInt();

                if (id == 1) {
                    System.out.println("El empleado ya ha sido eliminado.");
                } else {
                    file.seek(posicion); // Volver al inicio del registro
                    file.writeInt(1); // Marcar ID como 1

                    // Convertir el ID a String y usarlo como apellido
                    String idStr = String.valueOf(idEliminar);
                    StringBuffer buffer = new StringBuffer(idStr);
                    buffer.setLength(10); // Ajustar a 10 caracteres
                    file.writeChars(buffer.toString()); // Escribir el apellido como ID

                    file.writeInt(0); // Departamento a 0
                    file.writeDouble(0.0); // Salario a 0

                    System.out.println("Empleado con ID " + idEliminar + " ha sido eliminado.");
                }
            }

            file.close(); // Cerrar el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}