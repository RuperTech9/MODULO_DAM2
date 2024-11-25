package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.util.ArrayList;

/**
 * La clase GeneradorIDs genera una lista de identificadores alfanuméricos únicos de 6 caracteres.
 * Cada identificador es único en una lista de tamaño definido.
 *
 * Ejemplo de uso:
 * <pre>
 *     GeneradorIDs generador = new GeneradorIDs();
 *     ArrayList<String> ids = generador.generarID();
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class GeneradorIDs {
    final String CARACTERES = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789";
    final int LONGITUD = 6;
    final int CANTIDAD = 200;

    /**
     * Genera una lista de IDs alfanuméricos de 6 caracteres, garantizando que todos sean únicos.
     *
     * @return Un ArrayList<String> que contiene los IDs generados.
     */
    public ArrayList<String> generarID() {
        ArrayList<String> ID = new ArrayList<>();

        while (ID.size() < CANTIDAD) {
            StringBuilder identificador = new StringBuilder();
            for (int i = 0; i < LONGITUD; i++) {
                identificador.append(CARACTERES.charAt((int) (Math.random() * CARACTERES.length())));

            }
            String nuevoID = identificador.toString();

            // Verificamos si el ID ya existe para asegurar la unicidad
            if (!ID.contains(nuevoID)) {
                ID.add(nuevoID);
            }
        }
        return ID;
    }
    /**
     * Metodo principal para ejecutar la generación de identificadores y mostrar el resultado.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        // Crear una instancia de GeneradorIDs
        GeneradorIDs generador = new GeneradorIDs();
        // Generar los identificadores
        ArrayList<String> identificadores = generador.generarID();

        // Mostrar el total y cada identificador generado
        System.out.println("Se generaron " + identificadores.size() + " identificadores únicos:");
        for (int i = 0; i < identificadores.size(); i++) {
            System.out.println(identificadores.get(i));
        }
    }
}
