package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.*;

public class E06b_EjecutarLeerCadenasHastaAsterisco {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar LeerCadenasHastaAsterisco
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                    "ProgramacionServiciosYProcesos.Ejercicios.E06a_LeerCadenasHastaAsterisco");

            Process process = pb.start();

            // Redirigir la entrada estándar del proceso (System.in) para enviar cadenas
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                // Enviar varias cadenas al proceso
                bw.write("Hola\n");  // Primera cadena
                bw.write("Mundo\n"); // Segunda cadena
                bw.write("*\n");     // Asterisco para finalizar
                bw.flush();          // Asegurarse de enviar los datos
            }

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
