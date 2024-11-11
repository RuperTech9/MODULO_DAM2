package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GeneradorArchivos_b implements Runnable {
    private ArrayList<String> identificadores;
    private String nombreArchivo;

    public GeneradorArchivos_b(ArrayList<String> identificadores, String nombreArchivo) {
        this.identificadores = identificadores;
        this.nombreArchivo = nombreArchivo;
    }

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