package AccesoADatos.T01_Ficheros.Aleatorios;

import java.io.*;
import java.util.Scanner;

public class EmpleadoConsulta {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/Aleatorios/AleatorioEmple.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            //int identificador = 5; // ID del empleado a consultar
            // Crear un objeto Scanner para leer la entrada del usuario
            Scanner sc = new Scanner(System.in);
            // Solicitar el ID del empleado a buscar
            System.out.print("Introduce el ID del empleado que deseas consultar: ");
            int identificador = sc.nextInt();

            long posicion = (identificador - 1) * 36; // La fórmula (identificador - 1) * 36 determina la posición del registro correspondiente a ese ID.

            if (posicion >= file.length()) {
                System.out.printf("ID: %d, NO EXISTE EMPLEADO...\n", identificador);
            } else {
                file.seek(posicion); // seek para mover el puntero del archivo a la posición calculada.
                int id = file.readInt(); // Obtengo ID del empleado

                char[] apellido = new char[10];
                for (int i = 0; i < apellido.length; i++) {
                    apellido[i] = file.readChar(); // Obtener apellido
                }
                String apellidos = new String(apellido).trim();
                int dep = file.readInt(); // Obtengo departamento
                double salario = file.readDouble(); // Obtengo salario

                System.out.printf("ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f\n",
                        id, apellidos, dep, salario);
            }
            file.close(); // Cerramos fichero
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}