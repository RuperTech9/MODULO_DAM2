package Programacion.T01_Procesos.Practica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E04_GestorTareas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ruta donde se guardarán los archivos .tar comprimidos
        String rutaSalida = "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/src/Programacion/T01_Procesos/Practica/";

        // Paso 1: Solicitar al usuario que introduzca las rutas de los archivos para comprimir
        System.out.println("Introduce la ruta completa de los archivos que deseas comprimir (separados por comas):");
        String entrada = scanner.nextLine();

        // Dividir las rutas proporcionadas por el usuario
        String[] rutasArchivos = entrada.split(",");

        // Almacenar los archivos en una lista
        List<File> archivos = new ArrayList<>();
        for (String ruta : rutasArchivos) {
            File archivo = new File(ruta.trim());
            if (archivo.exists() && archivo.isFile()) {
                archivos.add(archivo);
            } else {
                System.err.println("El archivo " + archivo.getAbsolutePath() + " no existe o no es válido.");
            }
        }

        // Si no hay archivos válidos, terminar el programa
        if (archivos.isEmpty()) {
            System.err.println("No se encontraron archivos válidos para procesar.");
            return;
        }

        // Lista para almacenar los procesos
        List<Process> procesos = new ArrayList<>();
        List<String> nombresArchivosComprimidos = new ArrayList<>();

        // Paso 2: Iniciar un proceso de compresión para cada archivo
        for (File archivo : archivos) {
            try {
                // Nombre del archivo comprimido (.tar) con la ruta de salida
                String nombreArchivoComprimido = rutaSalida + archivo.getName().replace(".", "")+ "Comprimido" + ".tar";
                nombresArchivosComprimidos.add(nombreArchivoComprimido);

                // Crear el comando para comprimir el archivo
                ProcessBuilder pb = new ProcessBuilder("tar", "-czf", nombreArchivoComprimido, archivo.getAbsolutePath());
                pb.redirectErrorStream(true); // Redirigir la salida de error a la salida estándar

                // Iniciar el proceso
                Process proceso = pb.start();
                procesos.add(proceso);

            } catch (IOException e) {
                System.err.println("Error al iniciar el proceso de compresión para " + archivo.getName());
                e.printStackTrace();
            }
        }

        // Paso 3: Esperar a que todos los procesos terminen y verificar el estado de cada uno
        for (int i = 0; i < procesos.size(); i++) {
            Process proceso = procesos.get(i);
            String nombreArchivoComprimido = nombresArchivosComprimidos.get(i);

            try {
                // Esperar a que el proceso termine
                int exitCode = proceso.waitFor();
                if (exitCode == 0) {
                    System.out.println("Compresión del archivo " + nombreArchivoComprimido + " completada con éxito.");
                } else {
                    System.err.println("Error durante la compresión del archivo " + nombreArchivoComprimido + ". Código de salida: " + exitCode);
                }
            } catch (InterruptedException e) {
                System.err.println("El proceso de compresión fue interrumpido para el archivo " + nombreArchivoComprimido);
                e.printStackTrace();
            }

            // Leer la salida estándar del proceso
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea); // Imprimir cada línea de salida del proceso
                }
            } catch (IOException e) {
                System.err.println("Error al leer la salida del proceso para " + nombreArchivoComprimido);
                e.printStackTrace();
            }
        }

        System.out.println("Todos los procesos de compresión han terminado.");
    }
}
