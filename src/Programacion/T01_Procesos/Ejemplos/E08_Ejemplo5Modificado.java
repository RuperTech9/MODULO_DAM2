package Programacion.T01_Procesos.Ejemplos;

/*
Usando ProcessBuilder.Redirect, modifica el E05_EjecutarClaseConEntradaEstandar.java para que la salida del proceso se muestre en la consola,
la entrada la tome desde un fichero de texto, y la salida la lleve a un fichero de texto.
 */

import java.io.*;

public class E08_Ejemplo5Modificado {

    public static void main(String[] args) throws IOException {
        // Directorio donde se ejecutará el proceso
        File directorio = new File("./src/ProgramacionServiciosYProcesos/Ejemplos");

        // Construimos el comando para ejecutar el programa EjemploLectura
        ProcessBuilder pb = new ProcessBuilder("java",
                "-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                "ProgramacionServiciosYProcesos.T01_Procesos.Ejemplos.EjemploLectura");

        pb.directory(directorio);

        // Redirigir la entrada desde un archivo "entrada.txt"
        File fIn = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/entrada.txt"); // Este fichero contiene "Hola Mundo"
        pb.redirectInput(fIn);

        // Redirigir la salida estándar a la consola
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        // Redirigir la salida de error a un archivo "error.txt"
        File fErr = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/error.txt");

        /* OTRA FORMA
        // Verifica que el archivo de error pueda ser creado
        if (!fErr.exists()) {
            fErr.createNewFile();
        }

        // Aseguramos la redirección de errores
        pb.redirectError(ProcessBuilder.Redirect.appendTo(fErr)); // Cambiado a appendTo para no sobreescribir contenido
         */
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
