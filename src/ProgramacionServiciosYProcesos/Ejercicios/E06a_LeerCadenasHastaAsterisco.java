package ProgramacionServiciosYProcesos.Ejercicios;

import java.util.Scanner;

public class E06a_LeerCadenasHastaAsterisco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cadena;

        System.out.println("Introduce cadenas (escribe * para salir):");

        while (true) {
            cadena = scanner.nextLine(); // Leer la cadena

            // Si la cadena es un asterisco, salir del bucle
            if (cadena.equals("*")) {
                System.out.println("Programa finalizado.");
                break;
            }

            // Imprimir la cadena
            System.out.println("Cadena recibida: " + cadena);
        }

        System.exit(0);
    }
}
