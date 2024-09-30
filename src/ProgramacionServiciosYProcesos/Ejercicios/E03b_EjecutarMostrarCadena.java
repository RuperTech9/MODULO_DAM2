package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.*;
import java.util.Scanner;

/*
 * Crea un segundo programa Java que introduzca por teclado una
 * cadena y ejecute el programa anterior para visualizar 5 veces esa cadena.
 */

public class E03b_EjecutarMostrarCadena {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que introduzca una cadena
        System.out.print("Introduce una cadena: ");
        String cadena = scanner.nextLine();

        try {
            // Crear el proceso para ejecutar MostrarCadena con la cadena introducida
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2", "ProgramacionServiciosYProcesos.Ejercicios.E03a_MostrarCadena", cadena);
            Process process = pb.start();

            // Capturar la salida del proceso (salida estándar)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Imprimir la salida del proceso
            }

            /*
            // Capturar la salida del proceso (usando InputStream de manera básica)
            InputStream is = process.getInputStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c); // Convertir el byte leído a un carácter y mostrarlo
            }
             */

            // Capturar la salida del proceso
            int exitCode = process.waitFor();

            if (exitCode == 1) {
                System.out.println("El proceso finalizó con un error (no se proporcionó una cadena).");
            } else {
                System.out.println("El proceso finalizó correctamente.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
