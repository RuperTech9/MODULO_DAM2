package Programacion.Pruebas;

public class AvoidNull {
    public static void main(String[] args) {
        String[] cadenas = new String[5];

        // Inicialización con cadenas vacías
        for (int i = 0; i < cadenas.length; i++) {
            cadenas[i] = "";
        }

        for (int i = 0; i < cadenas.length; i++) {
            System.out.println("Elemento " + i + ": '" + cadenas[i] + "'");
        }
    }
}

/*
Evitar null con inicialización adecuada
Declara un array de cadenas y asegura que no tenga valores null inicializándolo con cadenas vacías.
Luego, imprime cada elemento.
 */