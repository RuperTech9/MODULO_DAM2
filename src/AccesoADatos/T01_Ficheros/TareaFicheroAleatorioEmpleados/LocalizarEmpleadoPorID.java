package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioEmpleados;

import java.io.*;
import java.util.Scanner;

public class LocalizarEmpleadoPorID {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioEmpleados/EmpleadosAleatorio.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el ID del empleado: ");
            int idBuscado = sc.nextInt();

            long posicion = (idBuscado - 1) * 36;

            if (posicion >= file.length()) {
                System.out.println("El empleado con ID " + idBuscado + " no existe.");
            } else {
                file.seek(posicion);
                int id = file.readInt();

                char[] apellido = new char[10];
                for (int i = 0; i < apellido.length; i++) {
                    apellido[i] = file.readChar();
                }
                String apellidoStr = new String(apellido).trim();
                int departamento = file.readInt();
                double salario = file.readDouble();

                if (id > 0) {
                    System.out.printf("ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f\n",
                            id, apellidoStr, departamento, salario);
                } else {
                    System.out.println("El empleado con ID " + idBuscado + " no existe.");
                }
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}