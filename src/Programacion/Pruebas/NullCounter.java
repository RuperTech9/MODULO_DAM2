package Programacion.Pruebas;

public class NullCounter {
    public static void main(String[] args) {
        String[] cadenas = new String[3];
        cadenas[0] = "Hola";
        cadenas[2] = "Mundo";

        int cantidadNull = contarNulos(cadenas);
        System.out.println("Cantidad de elementos null: " + cantidadNull);
    }

    public static int contarNulos(String[] array) {
        int contador = 0;

        for (String elemento : array) {
            if (elemento == null) {
                contador++;
            }
        }

        return contador;
    }
}

/*
 Trabajar con un array de objetos que puede contener null
 Declara un array de tipo String con 3 elementos, asigna valores solo a dos posiciones,
 y crea un metodo que cuente cuántos elementos son null.
 */