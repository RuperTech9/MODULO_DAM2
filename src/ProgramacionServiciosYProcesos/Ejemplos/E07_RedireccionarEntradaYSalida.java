package ProgramacionServiciosYProcesos.Ejemplos;

import java.io.*;

public class E07_RedireccionarEntradaYSalida {

    public static void main(String[] args) throws IOException {
        // Directorio donde se ejecutará el proceso
        File directorio = new File("./src/ProgramacionServiciosYProcesos/Ejemplos");

        // Construimos el comando para ejecutar el programa EjemploLectura
        ProcessBuilder pb = new ProcessBuilder("java",
                "-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                "ProgramacionServiciosYProcesos.Ejemplos.EjemploLectura");

        pb.directory(directorio);

        // Redirigir la entrada desde un archivo "entrada.txt"
        File fIn = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/entrada.txt"); // Este fichero contiene "Hola Mundo"
        pb.redirectInput(fIn);

        // Redirigir la salida del proceso a un archivo "salida.txt"
        File fOut = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/salida.txt");
        pb.redirectOutput(fOut);

        // Redirigir la salida de error a un archivo "error.txt"
        File fErr = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/error.txt");
        pb.redirectError(fErr);

        // Ejecutamos el proceso
        Process p = pb.start();

        // Esperar a que termine el proceso
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}