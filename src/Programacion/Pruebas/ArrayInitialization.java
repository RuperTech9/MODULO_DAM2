package Programacion.Pruebas;

public class ArrayInitialization {
    public static void main(String[] args) {
        int[] numeros = new int[5]; // Inicialización con valores predeterminados (0)

        System.out.println("Valores iniciales:");
        for (int numero : numeros) {
            System.out.println(numero); // Salida: 0, 0, 0, 0, 0
        }

        // Asignación de valores
        numeros[0] = 10;
        numeros[1] = 20;
        numeros[2] = 30;

        System.out.println("Valores actualizados:");
        for (int numero : numeros) {
            System.out.println(numero); // Salida: 10, 20, 30, 0, 0
        }
    }
}

/*
Inicialización de un array con valores predeterminados
Declara un array de enteros de tamaño 5. Imprime sus valores iniciales y luego
asigna valores específicos a cada posición. Imprime el array actualizado.
 */