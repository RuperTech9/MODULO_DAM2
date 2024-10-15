package AccesoADatos.T01_Ficheros.Ejercicios;

import java.io.*;
import java.util.Scanner;

public class E05_EliminarDepartamento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicitar el número de departamento a eliminar desde la consola
        System.out.print("Introduce el número de departamento a eliminar: ");
        int numDeptEliminar = sc.nextInt();

        // Archivos para el original y temporal
        File archivoOriginal = new File("./src//AccesoADatos//T01_Ficheros/Ejercicios/Departamentos.dat");
        File archivoTemporal = new File("./src//AccesoADatos//T01_Ficheros/Ejercicios/TempDepartamentos.dat");

        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivoOriginal));
             DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoTemporal))) {

            boolean encontrado = false;
            int totalDepartamentos = 0;

            // Leer el archivo original y escribir en el archivo temporal
            while (dis.available() > 0) {
                int numDept = dis.readInt(); // Leer número de departamento
                String nombreDept = dis.readUTF(); // Leer nombre de departamento
                String localidadDept = dis.readUTF(); // Leer localidad

                if (numDept != numDeptEliminar) {
                    // Si no es el departamento a eliminar, copiarlo al archivo temporal
                    dos.writeInt(numDept);
                    dos.writeUTF(nombreDept);
                    dos.writeUTF(localidadDept);
                    totalDepartamentos++; // Contar departamentos restantes
                } else {
                    // Si es el departamento a eliminar, mostrar mensaje de eliminación
                    encontrado = true;
                    System.out.printf("Eliminando departamento: Número: %d, Nombre: %s, Localidad: %s%n", numDept, nombreDept, localidadDept);
                }
            }

            // Si no se encontró el departamento
            if (!encontrado) {
                System.out.println("El departamento no existe.");
            } else {
                // Mostrar el número total de departamentos restantes
                System.out.printf("Número total de departamentos restantes: %d%n", totalDepartamentos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reemplazar el archivo original por el archivo temporal si se encontró el departamento
        if (archivoOriginal.delete()) {
            archivoTemporal.renameTo(archivoOriginal);
        }
    }
}