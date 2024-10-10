package Programacion.T01_Procesos.Ejercicios;

import java.io.IOException;
import java.util.Scanner;

public class E04b_EjecutarMostarCadenaFichero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que introduzca una cadena
        System.out.print("Introduce una cadena: ");
        String cadena = scanner.nextLine();

        try {
            // Crear el proceso para ejecutar MostrarCadenaFichero con la cadena introducida
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                    "ProgramacionServiciosYProcesos.T01_Procesos.Ejercicios.E04a_MostrarCadenaFichero",
                    cadena);
            Process process = pb.start();

            int exitCode = process.waitFor();

            if (exitCode == 1) {
                System.out.println("El proceso finalizó con un error (no se proporcionó una cadena).");
            } else {
                System.out.println("La cadena se ha escrito correctamente en el archivo.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
