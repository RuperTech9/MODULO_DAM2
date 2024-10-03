package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.*;

public class E07_ {

    public static void main(String[] args) throws IOException {

        // Establecer el directorio de trabajo del proceso
        File directorio = new File("./src/ProgramacionServiciosYProcesos/Ejercicios");

        // Archivo de entrada
        File archivoEntrada = new File("./src/ProgramacionServiciosYProcesos/Ejercicios/entrada.txt");

        // Si el archivo de entrada no existe, crearlo con contenido predeterminado
        if (!archivoEntrada.exists()) {
            System.out.println("El archivo entrada.txt no existe. Creándolo con contenido predeterminado...");
            try (FileWriter writer = new FileWriter(archivoEntrada)) {
                writer.write("Hola Manuel\n");  // Contenido predeterminado
            }
        }

        // Configurar ProcessBuilder para ejecutar EjemploLectura
        ProcessBuilder pb = new ProcessBuilder("java",
                "-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
                "ProgramacionServiciosYProcesos.Ejercicios.EjemploLectura");

        pb.directory(directorio);

        // Redirigir la entrada del proceso desde el archivo de entrada
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
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}