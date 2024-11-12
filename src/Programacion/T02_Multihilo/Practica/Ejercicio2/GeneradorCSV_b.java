package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La clase GeneradorCSV_b genera dos archivos CSV, cada uno con 50,000 líneas de pares
 * de identificadores y números aleatorios.
 * Utiliza una lista de identificadores proporcionada como entrada.
 *
 * Ejemplo de uso:
 * <pre>
 *     ArrayList<String> ids = generadorIds.generarID();
 *     GeneradorCSV_b generadorCSV = new GeneradorCSV_b(ids);
 *     generadorCSV.generarArchivosCSV("archivo1.csv", "archivo2.csv");
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class GeneradorCSV_b {
    private ArrayList<String> identificadores;

    /**
     * Constructor que recibe una lista de identificadores para usarlos en la generación de los archivos CSV.
     *
     * @param identificadores Lista de identificadores alfanuméricos.
     */
    public GeneradorCSV_b(ArrayList<String> identificadores) {
        this.identificadores = identificadores;
    }

    /**
     * Genera dos archivos CSV con 50,000 líneas cada uno, cada línea contiene un identificador y un número aleatorio.
     *
     * @param nombreArchivo1 Ruta y nombre del primer archivo CSV.
     * @param nombreArchivo2 Ruta y nombre del segundo archivo CSV.
     */
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

    /**
     * Metodo principal para ejecutar la generación de dos archivos CSV.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
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