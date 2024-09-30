package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class E06b_EjecutarLeerCadenasHastaAsterisco1 {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar LeerCadenasHastaAsterisco
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                    "ProgramacionServiciosYProcesos.Ejercicios.E06a_LeerCadenasHastaAsterisco");

            // Iniciar el proceso
            Process process = pb.inheritIO().start();

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("El proceso terminó correctamente.");
            } else {
                System.out.println("El proceso terminó con errores.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
