package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.*;

public class E02b_LanzaValidador {
    public static void main(String[] args) throws IOException {
        // Directorio donde se encuentra el archivo .class compilado
        File directorio = new File("C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2");

        // Proceso a ejecutar con el nombre del programa y el argumento
        ProcessBuilder pb = new ProcessBuilder("java", "-cp",
                "C:\\Users\\Ruper\\IdeaProjects\\MODULO_DAM2\\out\\production\\MODULO_DAM2",
                "ValidadorArgumentos", "5"); // Cambia el argumento "5" según sea necesario.

        // Establecemos el directorio de trabajo
        pb.directory(directorio);

        // Ejecutamos el proceso
        Process p = pb.start();

        // Capturar la salida del proceso
        try (InputStream is = p.getInputStream()) {
            int c;
            while ((c = is.read()) != -1)
                System.out.print((char) c);
        }

        // Capturar la salida de error del proceso
        try (InputStream es = p.getErrorStream()) {
            int c;
            while ((c = es.read()) != -1)
                System.err.print((char) c);
        }

        // Comprobación del valor de salida - 0, 1, 2, o 3
        int exitVal;
        try {
            exitVal = p.waitFor();
            switch (exitVal) {
                case 0:
                    System.out.println("El proceso terminó correctamente con valor 0.");
                    break;
                case 1:
                    System.out.println("Error: No se pasó ningún argumento (valor 1).");
                    break;
                case 2:
                    System.out.println("Error: El argumento es una cadena de texto (valor 2).");
                    break;
                case 3:
                    System.out.println("Error: El argumento es un número menor que 0 (valor 3).");
                    break;
                default:
                    System.out.println("El proceso terminó con un valor desconocido.");
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
