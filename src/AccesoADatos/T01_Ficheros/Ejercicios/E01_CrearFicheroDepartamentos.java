package AccesoADatos.T01_Ficheros.Ejercicios;

import java.io.*;
import java.util.Scanner;

public class E01_CrearFicheroDepartamentos {
    public static void main(String[] args) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("./src//AccesoADatos//T01_Ficheros/DB4O/Departamentos.dat"))) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce los datos de los departamentos (escribe -1 para terminar):");

            while (true) {
                System.out.print("Número de departamento: ");
                int numDept = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                if (numDept == -1) {
                    break;
                }

                System.out.print("Nombre del departamento: ");
                String nombre = sc.nextLine();

                System.out.print("Localidad del departamento: ");
                String localidad = sc.nextLine();

                // Escribir en el archivo
                dos.writeInt(numDept);
                dos.writeUTF(nombre);
                dos.writeUTF(localidad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}