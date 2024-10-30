package Programacion.T01_Procesos.Ejercicios;

import java.io.*;

/*
 * Crea un programa Java que ejecute el anterior.
 */

public class E05b_EjecutarSumaNumeros1 {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar E05a_SumaNumeros
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                    "ProgramacionServiciosYProcesos.T01_Procesos.DB4O.E05a_SumaNumeros");

            // Iniciar el proceso
            Process process = pb.inheritIO().start();

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();

            // Verificar si el proceso terminó correctamente o con un error
            if (exitCode == 1) {
                System.out.println("El proceso terminó con un error (no se introdujeron números válidos).");
            } else {
                System.out.println("El proceso terminó correctamente.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}