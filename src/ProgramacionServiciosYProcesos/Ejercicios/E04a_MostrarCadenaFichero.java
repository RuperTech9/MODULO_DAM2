package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.*;

/*
 * Partiendo del ejercicio 1.3 realiza los cambios necesarios para que la cadena
 * introducida por teclado se almacene en un fichero de texto, no mostrándola en
 * pantalla.
 */

public class E04a_MostrarCadenaFichero {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error: No se ha proporcionado ninguna cadena.");
            System.exit(1);
        }

        String cadena = args[0];

        // Modificamos la ruta para guardar en la ubicación solicitada
        String rutaArchivo = "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/src/ProgramacionServiciosYProcesos/Ejercicios/salida.txt";

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            for (int i = 0; i < 5; i++) {
                writer.write((i + 1) + ": " + cadena + "\n");
            }
            System.out.println("Cadena escrita en el archivo 'salida.txt'.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }

        System.exit(0);
    }
}
