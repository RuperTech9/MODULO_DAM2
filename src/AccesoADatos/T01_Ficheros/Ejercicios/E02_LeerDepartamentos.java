package AccesoADatos.T01_Ficheros.Ejercicios;

import java.io.*;

public class E02_LeerDepartamentos {
    public static void main(String[] args) {
        File archivo = new File("./src//AccesoADatos//T01_Ficheros/DB4O/Departamentos.dat");

        // Verificar si el archivo existe
        if (!archivo.exists()) {
            System.out.println("El archivo Departamentos.dat no existe.");
            return;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivo))) {
            System.out.println("Lista de departamentos:");

            // Leer y mostrar todos los departamentos
            while (dis.available() > 0) {
                int numDept = dis.readInt();          // Leer número de departamento
                String nombreDept = dis.readUTF();    // Leer nombre de departamento
                String localidadDept = dis.readUTF(); // Leer localidad

                // Mostrar los datos leídos
                System.out.printf("Número de departamento: %d, Nombre: %s, Localidad: %s%n", numDept, nombreDept, localidadDept);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}