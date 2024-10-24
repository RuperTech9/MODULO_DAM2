package Programacion.T01_Procesos.Practica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La clase E04_GestorTareas permite al usuario seleccionar archivos para comprimirlos
 * en formato .zip utilizando procesos del sistema operativo. Utiliza PowerShell para
 * realizar la compresión de archivos de manera paralela, mejorando la eficiencia
 * en comparacion con la compresion secuencial.
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * E04_GestorTareas.main(new String[0]);
 * }
 * </pre>
 *
 * <p>Este programa solicita al usuario las rutas de los archivos que desea comprimir
 * y los comprime en archivos .zip en la ubicacion especificada.</p>
 *
 * @author Ruper
 * @version 1.0
 */
public class E04_GestorTareas {

    /**
     * El metodo main es el punto de entrada de la aplicacion. Permite al usuario
     * ingresar rutas de archivos a comprimir, verifica su existencia y
     * gestiona la compresion en paralelo.
     *
     * @param args Argumentos de linea de comandos (no utilizados en esta aplicacion).
     *
     * <p>Ejemplo de uso:</p>
     * <pre>
     * {@code
     * E04_GestorTareas.main(new String[0]);
     * }
     * </pre>
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<File> archivos = new ArrayList<>();

        // Ruta donde se guardarán los archivos comprimidos
        String rutaDestinoCompresion = "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/src/Programacion/T01_Procesos/Practica";

        // Selección de archivos
        System.out.println("Introduce las rutas de los archivos a comprimir (separados por enter). Introduce 'fin' para terminar:");
        while (true) {
            String ruta = scanner.nextLine();
            if (ruta.equalsIgnoreCase("fin")) {
                break;
            }
            File archivo = new File(ruta);
            if (archivo.exists()) {
                archivos.add(archivo);
            } else {
                System.err.println("El archivo " + ruta + " no existe, por favor introduce una ruta válida.");
            }
        }

        if (archivos.isEmpty()) {
            System.out.println("No se seleccionaron archivos. Saliendo.");
            return;
        }

        // Crear y ejecutar los procesos de compresión en paralelo
        List<Process> procesos = new ArrayList<>();
        for (int i = 0; i < archivos.size(); i++) {
            File archivo = archivos.get(i);
            try {
                // Nombre del archivo comprimido en la ruta destino
                String archivoComprimido = rutaDestinoCompresion + "\\" + archivo.getName() + ".zip";

                // Comandos para comprimir usando PowerShell
                String comando = String.format("powershell Compress-Archive -Path %s -DestinationPath %s", archivo.getAbsolutePath(), archivoComprimido);

                // Iniciar el proceso con cmd.exe para ejecutar PowerShell
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
                pb.directory(archivo.getParentFile());
                Process proceso = pb.start();
                procesos.add(proceso);

                System.out.println("Iniciando compresión de: " + archivo.getName());

            } catch (IOException e) {
                System.err.println("Error al iniciar el proceso para el archivo: " + archivo.getName());
                e.printStackTrace();
            }
        }

        // Esperar a que todos los procesos terminen
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

