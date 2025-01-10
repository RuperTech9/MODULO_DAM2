package Programacion.Pruebas;

public class NullCheckAdvanced {
    public static void main(String[] args) {
        String[] nombres = {"Juan", null, "Ana"};
        String[] noInicializado = null;
        String[] vacio = {};

        verificarArray(nombres);
        verificarArray(noInicializado);
        verificarArray(vacio);
    }

    public static void verificarArray(String[] array) {
        if (array == null) {
            System.out.println("El array no está inicializado (es null).");
            return;
        }

        if (array.length == 0) {
            System.out.println("El array está vacío.");
            return;
        }

        int noNullCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                System.out.println("El elemento en la posición " + i + " es null.");
            } else {
                System.out.println("Elemento en la posición " + i + ": " + array[i]);
                noNullCount++;
            }
        }

        System.out.println("Número de elementos no null: " + noNullCount);
    }
}

/*
Verificar si una referencia es null antes de usarla
Crea un metodo que reciba un array de cadenas y verifique si está inicializado
o si contiene elementos nulos. El metodo debe imprimir un mensaje para cada caso.

Revisión y Variaciones:
Caso extremo: ¿Qué pasa si el array está vacío pero no es null?
Variación: Contar cuántos elementos en el array no son null.
 */