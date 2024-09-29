package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.*;

public class E04a_MostrarCadenaFichero {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error: No se ha proporcionado ninguna cadena.");
            System.exit(1);
        }

        String cadena = args[0];

        try (FileWriter writer = new FileWriter("salida.txt")) {
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
