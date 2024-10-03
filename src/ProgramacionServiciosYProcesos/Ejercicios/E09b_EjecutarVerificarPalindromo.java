package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.IOException;

public class E09b_EjecutarVerificarPalindromo {

    public static void main(String[] args) throws IOException {
        // Usamos ProcessBuilder para ejecutar el programa VerificarPalindromo
        ProcessBuilder pb = new ProcessBuilder("java",
                "-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                "ProgramacionServiciosYProcesos.Ejercicios.VerificarPalindromo");

        // Redirigir la salida estándar a la consola
        pb.inheritIO();

        // Ejecutamos el proceso
        Process p = pb.start();

        // Esperar a que termine el proceso
        try {
            int exitCode = p.waitFor();
            System.out.println("El proceso terminó con código: " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
