package ProgramacionServiciosYProcesos.Ejercicios;

import java.util.Scanner;

public class E05a_SumaNumeros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double numero1 = 0;
        double numero2 = 0;

        try {
            // Leer el primer número
            System.out.print("Introduce el primer número: ");
            numero1 = Double.parseDouble(scanner.nextLine());

            // Leer el segundo número
            System.out.print("Introduce el segundo número: ");
            numero2 = Double.parseDouble(scanner.nextLine());

            // Calcular la suma
            double suma = numero1 + numero2;
            System.out.println("La suma de " + numero1 + " y " + numero2 + " es: " + suma);

        } catch (NumberFormatException e) {
            System.err.println("Error: Lo introducido no es un número válido.");
            System.exit(1);
        }

        System.exit(0);
    }
}
