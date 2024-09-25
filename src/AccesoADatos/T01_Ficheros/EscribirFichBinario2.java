package AccesoADatos.T01_Ficheros;


/*
 * Crear un fichero binario que contenga 10 numeros reales que entran por teclado con la clase scanner y visualizarlo (Leer en el fichero,grabar en el teclado)
 */

import java.io.*;
import java.util.Scanner;

public class EscribirFichBinario2 {
	public static void main(String[] args) throws IOException {
        // Declara el fichero
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/numerosReales2.dat");
        
        // Crea flujo de salida hacia el fichero
        FileOutputStream fileout = new FileOutputStream(fichero);
        
        // Crea Scanner para leer los números desde el teclado
        Scanner sc = new Scanner(System.in);
        
        // Leer 10 números reales desde el teclado y escribirlos en el fichero
        System.out.println("Introduce 10 números reales:");
        for (int j = 0; j < 10; j++) {
            System.out.print("Número " + (j + 1) + ": ");
            double numero = sc.nextDouble();
            
            // Convertimos el número real a bytes y lo escribimos en el fichero
            long bits = Double.doubleToLongBits(numero);
            for (int i = 0; i < 8; i++) {
                fileout.write((int)(bits >> (i * 8)) & 0xFF);
            }
        }
        
        // Cerrar el stream de salida
        fileout.close();
        
        // Crea flujo de entrada desde el fichero
        FileInputStream filein = new FileInputStream(fichero);
        
        // Visualizar los datos del fichero
        System.out.println("\nContenido del fichero:");
        byte[] bytes = new byte[8];
        int bytesLeidos;
        while ((bytesLeidos = filein.read(bytes)) != -1) { // Lee datos del flujo de entrada
            if (bytesLeidos == 8) {
                long bits = 0;
                for (int i = 0; i < 8; i++) {
                    bits |= ((long) bytes[i] & 0xFF) << (i * 8);
                }
                double numero = Double.longBitsToDouble(bits);
                System.out.println(numero);
            }
        }
        
        // Cerrar el stream de entrada y el scanner
        filein.close();
        sc.close();
    }
}
