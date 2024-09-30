package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.*;

/*
 * Escribe un programa Java que lea dos números desde la entrada estándar y visualice
 * su suma.
 * Controla que lo introducido por teclado sean dos números.
 */

public class E05a_SumaNumeros {
    public static void main(String[] args) {
        double numero1 = 0;
        double numero2 = 0;

        // Usamos InputStreamReader y BufferedReader para leer desde la entrada estándar
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Leer el primer número
            System.out.print("Introduce el primer número: ");
            String input1 = reader.readLine();
            numero1 = Double.parseDouble(input1);  // Convertir a número

            // Leer el segundo número
            System.out.print("Introduce el segundo número: ");
            String input2 = reader.readLine();
            numero2 = Double.parseDouble(input2);  // Convertir a número

            // Calcular la suma
            double suma = numero1 + numero2;
            System.out.println("La suma de " + numero1 + " y " + numero2 + " es: " + suma);

        } catch (NumberFormatException e) {
            System.err.println("Error: Lo introducido no es un número válido.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error al leer de la entrada estándar.");
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }
}
