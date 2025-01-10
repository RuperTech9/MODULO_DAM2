package Programacion.Pruebas;

public class NullPointerHandler {
    public static void main(String[] args) {
        String[] cadenas = {"Hola", null, "Mundo"};

        int longitudTotal = calcularLongitudTotal(cadenas);
        System.out.println("Longitud total: " + longitudTotal);
    }

    public static int calcularLongitudTotal(String[] array) {
        int longitud = 0;

        for (String cadena : array) {
            try {
                longitud += cadena.length();
            } catch (NullPointerException e) {
                System.out.println("Se encontró un valor null. Ignorando...");
            }
        }

        return longitud;
    }
}

/*
Manejar una excepción NullPointerException
Crea un metodo que reciba un array de cadenas y calcule la longitud total de todas las cadenas.
Si una cadena es null, maneja la excepción e ignórala.
 */