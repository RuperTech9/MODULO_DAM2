package Programacion.T01_Procesos.Practica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorTareas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<File> archivos = new ArrayList<>();

        // Ruta donde se guardarán los archivos comprimidos
        String rutaDestinoCompresion = "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/src/Programacion/T01_Procesos/Practica";

        // Paso 1: Selección de archivos
        System.out.println("Introduce las rutas de los archivos a comprimir (separados por enter). Introduce 'fin' para terminar:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("fin")) {
                break;
            }
            File archivo = new File(input);
            if (archivo.exists()) {
                archivos.add(archivo);
            } else {
                System.out.println("El archivo " + input + " no existe, por favor introduce una ruta válida.");
            }
        }

        if (archivos.isEmpty()) {
            System.out.println("No se seleccionaron archivos. Saliendo.");
            return;
        }

        // Paso 2: Crear y ejecutar los procesos de compresión en paralelo (usando PowerShell en Windows)
        List<Process> procesos = new ArrayList<>();
        for (File archivo : archivos) {
            try {
                // Nombre del archivo comprimido en la ruta destino
                String archivoComprimido = rutaDestinoCompresion + "\\" + archivo.getName() + ".zip";

                // Comandos para comprimir PowerShell
                String comando = String.format("powershell Compress-Archive -Path %s -DestinationPath %s", archivo.getAbsolutePath(), archivoComprimido);
                //String comando = String.format("tar -cvf %s %s", archivoComprimido, archivo.getAbsolutePath());

                // Iniciar el proceso con cmd.exe para ejecutar PowerShell
                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", comando);
                processBuilder.directory(archivo.getParentFile());
                Process proceso = processBuilder.start();
                procesos.add(proceso);

                System.out.println("Iniciando compresión de: " + archivo.getName());

            } catch (IOException e) {
                System.err.println("Error al iniciar el proceso para el archivo: " + archivo.getName());
                e.printStackTrace();
            }
        }

        // Paso 3: Esperar a que todos los procesos terminen
        for (int i = 0; i < procesos.size(); i++) {
            Process proceso = procesos.get(i);
            File archivo = archivos.get(i);

            try {
                int exitCode = proceso.waitFor();

                // Paso 4: Mostrar el estado de cada tarea
                if (exitCode == 0) {
                    System.out.println("Compresión exitosa: " + archivo.getName());
                } else {
                    System.err.println("Error en la compresión: " + archivo.getName());
                }

            } catch (InterruptedException e) {
                System.err.println("Proceso interrumpido para el archivo: " + archivo.getName());
                e.printStackTrace();
            }
        }

        System.out.println("Todas las tareas de compresión han finalizado.");
    }
}
