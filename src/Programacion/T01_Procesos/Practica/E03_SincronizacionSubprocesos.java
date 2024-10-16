package Programacion.T01_Procesos.Practica;

import java.io.*;

public class E03_SincronizacionSubprocesos {

    public static void main(String[] args) {

        // Archivos de texto para el conteo de líneas y palabras
        File archivoLineas = new File("./src/Programacion/T01_Procesos/Practica/archivo1.txt");  // Ruta del archivo para contar líneas
        File archivoPalabras = new File("./src/Programacion/T01_Procesos/Practica/archivo2.txt"); // Ruta del archivo para contar palabras

        // Verificar si los archivos existen
        if (!archivoLineas.exists() || !archivoPalabras.exists()) {
            System.err.println("Uno o ambos archivos no existen.");
            return;
        }

        // Ejecutar el subproceso que cuenta las líneas
        int lineas = ejecutarComandoPowerShell("(Get-Content " + archivoLineas.getAbsolutePath() + " | Measure-Object -Line).Lines");
        if (lineas != -1) {
            System.out.println("Número de líneas en " + archivoLineas.getName() + ": " + lineas);
        } else {
            System.err.println("Error al contar líneas en " + archivoLineas.getName());
        }

        // Ejecutar el subproceso que cuenta las palabras
        int palabras = ejecutarComandoPowerShell("(Get-Content " + archivoPalabras.getAbsolutePath() + " | Measure-Object -Word).Words");
        if (palabras != -1) {
            System.out.println("Número de palabras en " + archivoPalabras.getName() + ": " + palabras);
        } else {
            System.err.println("Error al contar palabras en " + archivoPalabras.getName());
        }

        // Mostrar el total combinado de líneas y palabras
        if (lineas != -1 && palabras != -1) {
            System.out.println("Total combinado de líneas y palabras: " + (lineas + palabras));
        }
    }

    // Metodo auxiliar para ejecutar comandos de PowerShell y obtener el resultado
    private static int ejecutarComandoPowerShell(String comando) {
        ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", comando);
        pb.redirectErrorStream(true); // Redirigir la salida de error

        try {
            Process proceso = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.matches("\\d+")) {
                    return Integer.parseInt(linea.trim()); // Devolver el número encontrado
                }
            }
            proceso.waitFor(); // Esperar a que termine el proceso
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return -1; // En caso de error, devolver -1
    }
}
