package ProgramacionServiciosYProcesos.Practica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ComprimirArchivos {

    public static void main(String[] args) {
        // Nombre del archivo .tar que se va a crear
        String archivoDestino = "./src/ProgramacionServiciosYProcesos/Practica/archivos_comprimidos.tar";

        // Lista de archivos a comprimir
        List<String> archivosAComprimir = new ArrayList<>();
        archivosAComprimir.add("./src/ProgramacionServiciosYProcesos/Practica/archivo1.txt");
        archivosAComprimir.add("./src/ProgramacionServiciosYProcesos/Practica/archivo2.txt");
        archivosAComprimir.add("./src/ProgramacionServiciosYProcesos/Practica/archivo3.txt");

        // Ejecutar la compresión
        ComprimirArchivos compresor = new ComprimirArchivos();
        compresor.comprimir(archivoDestino, archivosAComprimir);
    }

    public void comprimir(String archivoDestino, List<String> archivos) {
        try {
            // Crear el proceso para ejecutar el comando tar
            ProcessBuilder pb = new ProcessBuilder("tar", "-cf", archivoDestino, "-T", "-");
            Process proceso = pb.start();

            // Enviar la lista de archivos a comprimir al subproceso a través de la entrada estándar
            try (OutputStream os = proceso.getOutputStream();
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
                for (String archivo : archivos) {
                    writer.write(archivo);
                    writer.newLine();
                }
                writer.flush();
            }

            // Leer la respuesta del subproceso (éxito o error) desde la salida estándar
            try (InputStream is = proceso.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            // Leer la salida de error del subproceso, si la hay
            try (InputStream es = proceso.getErrorStream();
                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(es))) {
                String lineaError;
                while ((lineaError = errorReader.readLine()) != null) {
                    System.err.println("Error: " + lineaError);
                }
            }

            // Comprobar el resultado del proceso
            int exitCode = proceso.waitFor();
            if (exitCode == 0) {
                System.out.println("Compresión completada con éxito.");
            } else {
                System.err.println("Error en la compresión. Código de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
