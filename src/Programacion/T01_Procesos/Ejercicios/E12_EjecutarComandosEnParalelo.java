package Programacion.T01_Procesos.Ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class E12_EjecutarComandosEnParalelo {
    public static void main(String[] args) {
        // Lista para almacenar los procesos
        List<ProcessBuilder> processBuilders = new ArrayList<>();

        // Comando 1: Listado de archivos del usuario en la carpeta personal con subcarpetas
        processBuilders.add(new ProcessBuilder("cmd", "/C", "dir", "/s","C:/Users/Ruper"));

        // Comando 2: Listado de las interfaces de red con sus direcciones IP
        processBuilders.add(new ProcessBuilder("cmd", "/C", "ipconfig"));

        // Comando 3: Fecha y hora del sistema
        processBuilders.add(new ProcessBuilder("cmd", "/C", "date", "/t", "&&", "time", "/t"));

        // Lista para almacenar los procesos en ejecución
        List<Process> processes = new ArrayList<>();

        // Ejecutar cada comando en un proceso separado
        for (ProcessBuilder pb : processBuilders) {
            try {
                Process process = pb.start();
                processes.add(process);
            } catch (IOException e) {
                System.err.println("Error al iniciar el proceso: " + e.getMessage());
            }
        }

        // Esperar a que todos los procesos terminen
        for (Process process : processes) {
            try {
                int exitCode = process.waitFor();
                System.out.println("Proceso finalizado con código: " + exitCode);
                // Imprimir la salida del proceso
                imprimirSalidaProceso(process);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para imprimir la salida de un proceso
    private static void imprimirSalidaProceso(Process process) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        }

        // Imprimir la salida de error si existe
        try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String errorLinea;
            while ((errorLinea = errorReader.readLine()) != null) {
                System.err.println("Error: " + errorLinea);
            }
        }
    }
}
