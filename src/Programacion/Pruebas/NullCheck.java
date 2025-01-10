package Programacion.Pruebas;

public class NullCheck {
    public static void main(String[] args) {
        String[] nombres = {"Juan", null, "Ana"};
        String[] noInicializado = null;
        
        verificarArray(nombres);
        verificarArray(noInicializado);
    }

    public static void verificarArray(String[] array) {
        if (array == null) {
            System.out.println("\nEl array no está inicializado (es null).");
            return;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                System.out.println("El elemento en la posición " + i + " es null.");
            } else {
                System.out.println("Elemento en la posición " + i + ": " + array[i]);
            }
        }
    }
}

/*
Verificar si una referencia es null antes de usarla
Crea un metodo que reciba un array de cadenas y verifique si está inicializado
o si contiene elementos nulos. El metodo debe imprimir un mensaje para cada caso.
 */