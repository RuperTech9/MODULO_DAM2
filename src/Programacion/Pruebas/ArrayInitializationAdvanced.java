package Programacion.Pruebas;

public class ArrayInitializationAdvanced {
    public static void main(String[] args) {
        int[] numeros = new int[5]; // Inicialización con valores predeterminados (0)

        System.out.println("Valores iniciales:");
        for (int numero : numeros) {
            System.out.println(numero); // Salida: 0, 0, 0, 0, 0
        }

        // Asignación de valores según la posición (pares e impares)
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (i % 2 == 0) ? 10 : 20;
        }

        System.out.println("Valores actualizados:");
        for (int numero : numeros) {
            System.out.println(numero); // Salida: 10, 20, 10, 20, 10
        }
    }
}

/*
Inicialización de un array con valores predeterminados
Declara un array de enteros de tamaño 5. Imprime sus valores iniciales y luego
asigna valores específicos a cada posición. Imprime el array actualizado.

Revisión y Variaciones:
Caso extremo: ¿Qué pasa si el tamaño del array es 0?
Variación: Cambiar valores predeterminados según la posición (pares e impares).
 */