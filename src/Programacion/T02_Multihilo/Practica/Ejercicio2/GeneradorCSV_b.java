package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GeneradorCSV_b {
    private ArrayList<String> identificadores;

    public GeneradorCSV_b(ArrayList<String> identificadores) {
        this.identificadores = identificadores;
    }

    public void generarArchivosCSV(String nombreArchivo1, String nombreArchivo2) {
        // Generar el primer archivo
        try (FileWriter writer = new FileWriter(nombreArchivo1)) {
            for (int i = 0; i < 50000; i++) {
                // Seleccionar un identificador aleatorio utilizando Math.random()
                String id = identificadores.get((int) (Math.random() * identificadores.size()));

                // Generar un número entre 0 y 20000 utilizando Math.random()
                int numero = (int) (Math.random() * 20001);

                // Escribir el par en el archivo CSV
                writer.write(id + "," + numero + "\n");
            }
            System.out.println("Archivo " + nombreArchivo1 + " generado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generar el segundo archivo
        try (FileWriter writer = new FileWriter(nombreArchivo2)) {
            for (int i = 0; i < 50000; i++) {
                // Seleccionar un identificador aleatorio utilizando Math.random()
                String id = identificadores.get((int) (Math.random() * identificadores.size()));

                // Generar un número entre 0 y 20000 utilizando Math.random()
                int numero = (int) (Math.random() * 20001);

                // Escribir el par en el archivo CSV
                writer.write(id + "," + numero + "\n");
            }
            System.out.println("Archivo " + nombreArchivo2 + " generado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Generar identificadores
        GeneradorIDs generadorIds = new GeneradorIDs();
        ArrayList<String> identificadores = generadorIds.generarID();

        // Generar archivo CSV con pares
        GeneradorCSV_b generadorCSV = new GeneradorCSV_b(identificadores);
        generadorCSV.generarArchivosCSV(
                "./src/Programacion/T02_Multihilo/Practica/Ejercicio2/pares_identificadores_1.csv",
                "./src/Programacion/T02_Multihilo/Practica/Ejercicio2/pares_identificadores_2.csv"
        );
    }
}