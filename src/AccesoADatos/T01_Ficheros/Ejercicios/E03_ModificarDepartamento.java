package AccesoADatos.T01_Ficheros.Ejercicios;

import java.io.*;
import java.util.Scanner;

public class E03_ModificarDepartamento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicitar los datos desde la consola
        System.out.print("Introduce el número de departamento a modificar: ");
        int numDeptModificar = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        System.out.print("Introduce el nuevo nombre del departamento: ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Introduce la nueva localidad del departamento: ");
        String nuevaLocalidad = sc.nextLine();

        // Archivo que contiene los departamentos
        File archivo = new File("./src//AccesoADatos//T01_Ficheros/DB4O/Departamentos.dat");

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            boolean encontrado = false;

            // Recorrer el archivo
            while (raf.getFilePointer() < raf.length()) {
                long posicion = raf.getFilePointer(); // Guardar la posición para modificar
                int numDept = raf.readInt(); // Leer número de departamento
                String nombreDept = raf.readUTF(); // Leer nombre de departamento
                String localidadDept = raf.readUTF(); // Leer localidad

                // Si el número de departamento coincide, lo modificamos
                if (numDept == numDeptModificar) {
                    encontrado = true;

                    // Mostrar datos antiguos
                    System.out.printf("Datos antiguos: Número: %d, Nombre: %s, Localidad: %s%n", numDept, nombreDept, localidadDept);

                    // Posicionar de nuevo en el inicio del registro para modificarlo
                    raf.seek(posicion);
                    raf.writeInt(numDeptModificar); // Sobrescribir número de departamento
                    raf.writeUTF(nuevoNombre); // Sobrescribir nombre del departamento
                    raf.writeUTF(nuevaLocalidad); // Sobrescribir localidad

                    // Mostrar datos nuevos
                    System.out.printf("Datos nuevos: Número: %d, Nombre: %s, Localidad: %s%n", numDeptModificar, nuevoNombre, nuevaLocalidad);
                    break;
                }
            }

            // Si no se encontró el departamento
            if (!encontrado) {
                System.out.println("El departamento no existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
