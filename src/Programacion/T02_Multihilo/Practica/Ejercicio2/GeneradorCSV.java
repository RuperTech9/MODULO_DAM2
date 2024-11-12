package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La clase GeneradorCSV genera un archivo CSV con pares de un identificador y un número aleatorio.
 * Utiliza identificadores de una lista proporcionada.
 *
 * Ejemplo de uso:
 * <pre>
 *     ArrayList<String> ids = generador.generarID();
 *     GeneradorCSV generadorCSV = new GeneradorCSV(ids);
 *     generadorCSV.generarArchivoCSV("miArchivo.csv");
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class GeneradorCSV {
    private ArrayList<String> identificadores;

    /**
     * Constructor que recibe una lista de identificadores para usarlos en la generación del CSV.
     *
     * @param identificadores Lista de identificadores alfanuméricos.
     */
    public GeneradorCSV(ArrayList<String> identificadores) {
        this.identificadores = identificadores;
    }

    /**
     * Genera un archivo CSV con 50.000 líneas, cada una con un identificador y un número aleatorio.
     *
     * @param nombreArchivo Ruta y nombre del archivo CSV a generar.
     */
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

    /**
     * Genera dos archivos CSV con 50,000 líneas cada uno, cada línea contiene un identificador y un número aleatorio.
     *
     * @param nombreArchivo1 Ruta y nombre del primer archivo CSV.
     * @param nombreArchivo2 Ruta y nombre del segundo archivo CSV.
     */
    public void generar2ArchivosCSV(String nombreArchivo1, String nombreArchivo2) {
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

    /**
     * Metodo principal que genera un archivo CSV con identificadores y números aleatorios.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
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