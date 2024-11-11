package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GeneradorCSV {
    private ArrayList<String> identificadores;

    public GeneradorCSV(ArrayList<String> identificadores) {
        this.identificadores = identificadores;
    }

    public void generarArchivoCSV(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < 50000; i++) {
                // Selecciono un identificador aleatorio utilizando Math.random()
                String id = identificadores.get((int) (Math.random() * identificadores.size()));

                // Genero un número entre 0 y 20000 utilizando Math.random()
                int numero = (int) (Math.random() * 20001);

                // Escribo el par en el archivo CSV
                writer.write(id + "," + numero + "\n");
            }
            System.out.println("Archivo " + nombreArchivo + " generado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Creo una instancia de GeneradorIDs
        GeneradorIDs generadorIds = new GeneradorIDs();
        ArrayList<String> identificadores = generadorIds.generarID();

        // Creo una instancia de GeneradorCSV
        GeneradorCSV generadorCSV = new GeneradorCSV(identificadores);

        // Genero el archivo CSV
        generadorCSV.generarArchivoCSV("./src/Programacion/T02_Multihilo/Practica/Ejercicio2/pares_identificadores.csv");
    }
}