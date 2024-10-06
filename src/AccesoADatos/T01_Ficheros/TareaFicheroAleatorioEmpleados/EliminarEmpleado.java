package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioEmpleados;

import java.io.*;
import java.util.Scanner;

public class EliminarEmpleado {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioEmpleados/EmpleadosAleatorio.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el ID del empleado a eliminar: ");
            int idEliminar = sc.nextInt();

            long posicion = (idEliminar - 1) * 36;

            if (posicion >= file.length()) {
                System.out.println("El empleado con ID " + idEliminar + " no existe.");
            } else {
                file.seek(posicion);
                int id = file.readInt();
                if (id == -1) {
                    System.out.println("El empleado ya ha sido eliminado.");
                } else {
                    file.seek(posicion);
                    file.writeInt(-1);
                    System.out.println("Empleado eliminado correctamente.");
                }
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}