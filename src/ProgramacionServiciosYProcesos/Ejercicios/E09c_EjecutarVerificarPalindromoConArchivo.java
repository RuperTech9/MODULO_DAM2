package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.File;
import java.io.IOException;

public class E09c_EjecutarVerificarPalindromoConArchivo {

    public static void main(String[] args) throws IOException {
        // Directorio donde se ejecutará el proceso
        File directorio = new File("./src/ProgramacionServiciosYProcesos/Ejercicios");

        // Usamos ProcessBuilder para ejecutar el programa VerificarPalindromo
        ProcessBuilder pb = new ProcessBuilder("java",
                "-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                "ProgramacionServiciosYProcesos.Ejercicios.VerificarPalindromo");

        pb.directory(directorio);

        // Redirigir la entrada desde un archivo "entrada.txt"
        File fIn = new File("./src/ProgramacionServiciosYProcesos/Ejercicios/entrada.txt");
        pb.redirectInput(fIn);

        // Redirigir la salida estándar a la consola
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        // Redirigir la salida de error a un archivo "error.txt"
        File fErr = new File("./src/ProgramacionServiciosYProcesos/Ejercicios/error.txt");
        pb.redirectError(fErr);

        // Ejecutamos el proceso
        Process p = pb.start();

        // Esperar a que termine el proceso
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("El proceso terminó con código: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
