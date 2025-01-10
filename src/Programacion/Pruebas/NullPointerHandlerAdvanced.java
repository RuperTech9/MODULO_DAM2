package Programacion.Pruebas;

public class NullPointerHandlerAdvanced {
    public static void main(String[] args) {
        String[] cadenas = {"Hola", null, "", "Mundo"};

        int longitudTotal = calcularLongitudTotal(cadenas);
        System.out.println("Longitud total: " + longitudTotal);
    }

    public static int calcularLongitudTotal(String[] array) {
        int longitud = 0;
        int excepciones = 0;

        for (String cadena : array) {
            try {
                if (cadena.isEmpty()) {
                    System.out.println("Se encontró una cadena vacía.");
                }
                longitud += cadena.length();
            } catch (NullPointerException e) {
                System.out.println("Se encontró un valor null. Ignorando...");
                excepciones++;
            }
        }

        System.out.println("Excepciones capturadas: " + excepciones);
        return longitud;
    }
}

/*
Manejar una excepción NullPointerException
Crea un metodo que reciba un array de cadenas y calcule la longitud total de todas las cadenas.
Si una cadena es null, maneja la excepción e ignórala.

Revisión y Variaciones:
Caso extremo: Mezclar elementos válidos, nulos y valores vacíos ("").
Variación: Contar el número de excepciones capturadas.
 */