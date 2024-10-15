package AccesoADatos.T01_Ficheros.Ejercicios;


/*
 * Crear un fichero binario que contenga 10 numeros reales que entran por teclado con la clase scanner y visualizarlo (Leer en el fichero,grabar en el teclado)
 */

import java.io.*;
import java.util.Scanner;

public class EjercicioProfe2_NumerosReales {
    public static void main(String[] args) {
        // Ruta del archivo binario
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/Ejercicios/PruebaProfe2_numerosReales.dat");
        
        // Utilizamos Scanner para leer la entrada por teclado
        Scanner scanner = new Scanner(System.in);
        
        // Declaramos los streams de salida y entrada
        DataOutputStream dataOut = null;
        DataInputStream dataIn = null;
        
        try {
            // Crear flujo de salida hacia el fichero
            dataOut = new DataOutputStream(new FileOutputStream(fichero));
            
            // Pedir al usuario que ingrese 10 números reales
            System.out.println("Introduce 10 números reales:");
            for (int i = 0; i < 10; i++) {
                System.out.print("Número " + (i + 1) + ": ");
                double numero = scanner.nextDouble();
                dataOut.writeDouble(numero); // Escribimos cada número real en el fichero
            }
            
            // Cerrar el flujo de salida
            dataOut.close();
            
            // Crear flujo de entrada desde el fichero
            dataIn = new DataInputStream(new FileInputStream(fichero));
            
            // Leer y mostrar los datos del fichero
            System.out.println("\nContenido del archivo 'numerosReales.dat':");
            while (true) {
                try {
                    double numero = dataIn.readDouble(); // Leer cada número real del fichero
                    System.out.println(numero);
                } catch (EOFException eof) {
                    // Se alcanza el final del archivo
                    break;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataOut != null) dataOut.close();
                if (dataIn != null) dataIn.close();
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
