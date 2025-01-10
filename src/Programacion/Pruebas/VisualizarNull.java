package Programacion.Pruebas;

public class VisualizarNull {
    public static void main(String[] args) {
        Object[] elementos = {null, "Texto", 123, null, true};

        for (int i = 0; i < elementos.length; i++) {
            System.out.println("Elemento en la posición " + i + ": " + elementos[i]);
        }
    }
}

/*
Visualizar null en un array
Declara un array de tipo Object con elementos de diferentes tipos, incluidos valores null.
Imprime el contenido del array.
 */