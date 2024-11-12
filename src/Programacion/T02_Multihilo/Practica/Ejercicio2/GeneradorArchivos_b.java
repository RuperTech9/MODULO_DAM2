package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La clase GeneradorArchivos_b implementa Runnable y genera un archivo CSV en un hilo independiente.
 * Esta clase está diseñada para crear múltiples archivos en paralelo con pares de identificador y número aleatorio.
 * En esta versión, se crean 50 hilos en lugar de 100 para observar el tiempo de ejecución y el rendimiento.
 *
 * Ejemplo de uso:
 * <pre>
 *     ArrayList<String> ids = generadorIds.generarID();
 *     Thread hilo = new Thread(new GeneradorArchivos_b(ids, "archivo.csv"));
 *     hilo.start();
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class GeneradorArchivos_b implements Runnable {
    private ArrayList<String> identificadores;
    private String nombreArchivo;

    /**
     * Constructor que recibe una lista de identificadores y el nombre del archivo de salida.
     *
     * @param identificadores Lista de identificadores alfanuméricos.
     * @param nombreArchivo Nombre del archivo CSV a generar.
     */
    public GeneradorArchivos_b(ArrayList<String> identificadores, String nombreArchivo) {
        this.identificadores = identificadores;
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Metodo run() que genera un archivo CSV con 100,000 líneas de pares de identificador y número aleatorio.
     * Este metodo se ejecuta en un hilo independiente.
     */
    @Override
    public void run() {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < 100000; i++) {
                // Seleccionar un identificador aleatorio
                String id = identificadores.get((int) (Math.random() * identificadores.size()));

                // Generar un número entre 0 y 20000
                int numero = (int) (Math.random() * 20001);

                // Escribir el par en el archivo CSV
                writer.write(id + "," + numero + "\n");
            }
            System.out.println("Archivo " + nombreArchivo + " generado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo principal que genera múltiples archivos CSV en hilos independientes.
     * En esta versión se crean 50 hilos para generar 50 archivos CSV y se mide el tiempo de ejecución.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Crear una instancia de GeneradorIDs
        GeneradorIDs generador = new GeneradorIDs();
        // Generar los identificadores
        ArrayList<String> identificadores = generador.generarID();

        // Crear y ejecutar 50 hilos para generar 50 archivos CSV
        for (int i = 1; i < 51; i++) {
            String nombreArchivo = "./src/Programacion/T02_Multihilo/Practica/Ejercicio2/Archivos/archivo_" + i + ".csv";
            Thread hilo = new Thread(new GeneradorArchivos_b(identificadores, nombreArchivo));
            hilo.start();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");
    }
}