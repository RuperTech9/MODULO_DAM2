package Programacion.T01_Procesos.Ejercicios;

import java.io.*;

/*
 * Crea un programa Java que ejecute el anterior.
 */

public class E05b_EjecutarSumaNumeros {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar E05a_SumaNumeros
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                    "ProgramacionServiciosYProcesos.T01_Procesos.DB4O.E05a_SumaNumeros");

            Process process = pb.start();

            // Redirigir la entrada estándar del proceso (System.in) y enviar los números
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write("5\n");  // Primer número
                writer.write("10\n");  // Segundo número
                writer.flush(); // Asegurarse de que los datos se envían al proceso hijo
            }

            // Capturar la salida estándar del proceso hijo (System.out)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linea;

            // Leer la salida del proceso hijo
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            // Capturar el valor de salida del proceso
            int exitCode = process.waitFor();

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
