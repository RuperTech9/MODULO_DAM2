package Programacion.T02_Multihilo.Practica;

import java.util.ArrayList;

public class GeneradorIdentidficadores {
    final String CARACTERES = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789";
    final int LONGITUD = 6;
    final int CANTIDAD = 200;

    public ArrayList<String> generarIdentidad() {
        ArrayList<String> identificador = new ArrayList<>();

        while (identificador.size() < CANTIDAD) {
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < LONGITUD; i++) {
                id.append(CARACTERES.charAt((int) (Math.random() * CARACTERES.length())));

            }
            String nuevoID = id.toString();

            // Verificar si el identificador ya existe para asegurar la unicidad
            if (!identificador.contains(nuevoID)) {
                identificador.add(nuevoID);
            }
        }
        return identificador;
    }
}
