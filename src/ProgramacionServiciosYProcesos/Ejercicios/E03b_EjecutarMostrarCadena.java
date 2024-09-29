package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.IOException;
import java.util.Scanner;

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
