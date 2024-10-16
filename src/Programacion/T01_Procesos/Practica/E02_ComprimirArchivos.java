package Programacion.T01_Procesos.Practica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class E02_ComprimirArchivos {

    public static void main(String[] args) {
        // Array de archivos a comprimir (en lugar de una lista)
        String[] archivosAComprimir = {
                "./src/Programacion/T01_Procesos/Practica/archivo1.txt",
                "./src/Programacion/T01_Procesos/Practica/archivo2.txt",
                "./src/Programacion/T01_Procesos/Practica/archivo3.txt"
        };

        // Nombre del archivo .tar que se va a crear
        String archivoDestino = "./src/Programacion/T01_Procesos/Practica/archivos_comprimidos.tar";

        // Crear el proceso para ejecutar el comando tar
        ProcessBuilder pb = new ProcessBuilder("tar", "-cf", archivoDestino, "-T", "-");

        // Iniciar el proceso
        try {
            Process proceso = pb.start();

            // Enviar la lista de archivos al subproceso a través de la entrada estándar
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
                for (String archivo : archivosAComprimir) {
                    writer.write(archivo);
                    writer.newLine();
                }
                writer.flush(); // Asegurarse de que los datos se envían al subproceso
            }

            // Leer la respuesta del subproceso (salida estándar)
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println("Subproceso: " + linea);
                }
            }

            // Leer los mensajes de error (si los hay)
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))) {
                String linea;
                while ((linea = errorReader.readLine()) != null) {
                    System.err.println("Error del subproceso: " + linea);
                }
            }

            // Comprobar si el subproceso ha terminado correctamente
            int exitCode = proceso.waitFor();
            if (exitCode == 0) {
                System.out.println("Compresión completada exitosamente.");
            } else {
                System.err.println("Error durante la compresión. Código de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}