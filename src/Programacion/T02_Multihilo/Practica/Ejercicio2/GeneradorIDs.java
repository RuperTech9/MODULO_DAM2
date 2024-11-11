package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.util.ArrayList;

public class GeneradorIDs {
    final String CARACTERES = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789";
    final int LONGITUD = 6;
    final int CANTIDAD = 200;

    //  Metodo que crea una lista de IDs alfanuméricos de 6 caracteres cada uno, asegurando que todos sean únicos. Devuelve un ArrayList<String> con los IDs generados.
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
    public static void main(String[] args) {
        // Crear una instancia de GeneradorIDs
        GeneradorIDs generador = new GeneradorIDs();
        // Generar los identificadores
        ArrayList<String> idebtificadores = generador.generarID();

        // Mostrar el total y cada identificador generado
        System.out.println("Se generaron " + idebtificadores.size() + " identificadores únicos:");
        for (int i = 0; i < idebtificadores.size(); i++) {
            System.out.println(idebtificadores.get(i));
        }
    }
}
