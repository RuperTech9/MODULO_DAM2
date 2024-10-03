package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.*;

public class E07a_ {
    public static void main(String[] args) {
        File directorio = new File("./src/ProgramacionServiciosYProcesos/Ejercicios");

        try {
            // Configurando el ProcessBuilder para ejecutar EjemploLectura
            ProcessBuilder pb = new ProcessBuilder("java",
                    "-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                    "ProgramacionServiciosYProcesos.Ejemplos.EjemploLectura");

            pb.directory(directorio);

            // Redirigir la entrada del proceso desde un archivo
            File archivoEntrada = new File("./src/ProgramacionServiciosYProcesos/Ejercicio/entrada.txt");

            // Verificar si el archivo de entrada existe
            if (!archivoEntrada.exists()) {
                System.out.println("El archivo entrada.txt no existe. Creándolo con contenido predeterminado...");
                try (FileWriter writer = new FileWriter(archivoEntrada)) {
                    writer.write("Hola Manuel\n");  // Contenido predeterminado
                }
            }

            pb.redirectInput(archivoEntrada);

            // Redirigir la salida estándar del proceso a un archivo
            File archivoSalida = new File("./src/ProgramacionServiciosYProcesos/Ejercicios/salida.txt");
            pb.redirectOutput(archivoSalida);

            // Redirigir la salida de error del proceso a un archivo
            File archivoError = new File("./src/ProgramacionServiciosYProcesos/Ejercicios/error.txt");
            pb.redirectError(archivoError);

            // Ejecutar el proceso
            Process p = pb.start();

            // Esperar a que el proceso termine
            int exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);

            // Mostrar mensaje si hubo errores en el proceso hijo
            if (exitVal != 0) {
                System.out.println("Se produjo un error en el proceso. Verificar el archivo error.txt.");
            }

        } catch (IOException e) {
            System.err.println("Error durante la ejecución del proceso: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("El proceso fue interrumpido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}