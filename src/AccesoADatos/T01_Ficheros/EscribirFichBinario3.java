package AccesoADatos.T01_Ficheros;


/*
 * Crear un fichero binario que contenga 10 numeros reales que entran por teclado con la clase scanner y visualizarlo (Leer en el fichero,grabar en el teclado)
 * 
 */

import java.io.*;
import java.util.Scanner;

public class EscribirFichBinario3 {
	public static void main(String[] args) throws IOException {
        // Declara el fichero
        File fichero = new File("C:/Users/Ruper/eclipse-workspace/ACCESO_DATOS/src/FICHEROS/numerosReales3.dat");
        
        // Crea Scanner para leer los números desde el teclado
        Scanner scanner = new Scanner(System.in);
        
        // Crear un array de bytes para almacenar los números reales
        byte[] arrayBytes = new byte[10 * 8]; // 10 números double, cada uno ocupa 8 bytes
        int offset = 0; // Para mantener la posición actual en el buffer

        // Leer 10 números reales desde el teclado y almacenarlos en el array de bytes
        System.out.println("Introduce 10 números reales:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            double numero = scanner.nextDouble();
            
            // Convertimos el número real a su representación en bytes
            long bits = Double.doubleToLongBits(numero);
            for (int j = 0; j < 8; j++) {
            	arrayBytes[offset + j] = (byte)((bits >> (j * 8)) & 0xFF);
            }
            offset += 8; // Avanzamos al siguiente espacio en el buffer
        }

        // Escribir el contenido del buffer en el fichero de una sola vez
        try (FileOutputStream fileout = new FileOutputStream(fichero)) {
            fileout.write(arrayBytes);
        }

        // Leer y mostrar los datos del fichero
        try (FileInputStream filein = new FileInputStream(fichero)) {
            byte[] readBuffer = new byte[8]; // Para leer cada número real
            System.out.println("\nContenido del fichero:");
            while (filein.read(readBuffer) != -1) {
                long bits = 0;
                for (int i = 0; i < 8; i++) {
                    bits |= ((long) readBuffer[i] & 0xFF) << (i * 8);
                }
                double numero = Double.longBitsToDouble(bits);
                System.out.println(numero);
            }
        }

        // Cerrar el scanner
        scanner.close();
    }
}