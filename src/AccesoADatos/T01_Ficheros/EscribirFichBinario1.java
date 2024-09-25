package AccesoADatos.T01_Ficheros;


/*
 * Crear un fichero binario que contenga 10 numeros reales que entran por teclado con la clase scanner y visualizarlo (Leer en el fichero,grabar en el teclado)
 */

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class EscribirFichBinario1 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        // Archivo donde se escribirán los números reales
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/numerosReales2.dat");
        FileOutputStream fileout = new FileOutputStream(fichero);
        FileInputStream filein = new FileInputStream(fichero);

        double numero;
        int contador = 0;
        final int TOTAL_NUMEROS = 10;

        // Bucle para capturar 10 números reales desde el teclado
        System.out.println("Introduce 10 números reales:");
        while (contador < TOTAL_NUMEROS) {
            System.out.print("Número (" + (contador + 1) + "/" + TOTAL_NUMEROS + "): ");
            numero = sc.nextDouble();

            // Convertimos el número real a bytes y lo escribimos en el archivo
            byte[] bytes = ByteBuffer.allocate(8).putDouble(numero).array();
            fileout.write(bytes);
            contador++;
        }

        // Cerramos el flujo de escritura
        fileout.close();

        // Leemos y mostramos los números almacenados en el fichero
        System.out.println("\nContenido del fichero:");
        byte[] bytesLeidos = new byte[8]; // Un double ocupa 8 bytes
        while (filein.read(bytesLeidos) != -1) {
            double numLeido = ByteBuffer.wrap(bytesLeidos).getDouble(); // Convertimos los bytes leídos a un double
            System.out.println(numLeido);
        }

        // Cerramos el flujo de lectura
        filein.close();
    }
}