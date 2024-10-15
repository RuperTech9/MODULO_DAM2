package AccesoADatos.T01_Ficheros;

import java.io.*;
import java.util.Scanner;

public class E21_BuscarEmpleadoAleatorio {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src/AccesoADatos/T01_Ficheros/PruebaAleatorioEmpl.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el ID del empleado que deseas buscar: ");
            int idBuscado = sc.nextInt();

            // Calcular la posición basándose en el ID
            long posicionBusqueda = (idBuscado - 1) * 36;

            // Verificar si la posición es válida
            if (posicionBusqueda >= file.length()) {
                System.out.println("El empleado con ID " + idBuscado + " no existe.");
            } else {
                file.seek(posicionBusqueda); // Posicionar el puntero en la posición calculada
                int id = file.readInt(); // Leer el ID del empleado

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

            file.close(); // Cerrar el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}