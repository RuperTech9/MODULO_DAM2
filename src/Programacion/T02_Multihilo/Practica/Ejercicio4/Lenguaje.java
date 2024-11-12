package Programacion.T02_Multihilo.Practica.Ejercicio4;

import java.io.*;
import java.util.Scanner;

/**
 * La clase Lenguaje genera palabras aleatorias de longitud variable y las guarda en un archivo.
 * Permite al usuario definir el número de palabras que se generarán.
 *
 * Ejemplo de uso:
 * <pre>
 *     Lenguaje lenguaje = new Lenguaje(40);
 *     lenguaje.escribirEnArchivo();
 *     lenguaje.leerArchivo();
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class Lenguaje {
    int numPalabras;      // Número de palabras a generar
    final String RUTA_ARCHIVO = "./src/Programacion/T02_Multihilo/Practica/Ejercicio4/miFicheroLenguaje.txt"; // Nombre del archivo de salida

    /**
     * Constructor que recibe el número de palabras a generar.
     *
     * @param numPalabras Número de palabras a generar.
     */
    public Lenguaje(int numPalabras) {
        this.numPalabras = numPalabras;
    }

    /**
     * Genera una palabra aleatoria de entre 5 y 10 caracteres.
     *
     * @return Una palabra generada aleatoriamente.
     */
    public String generarPalabra() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder palabra = new StringBuilder();
        int longitud = (int) (Math.random() * 6) + 5;

        for (int i = 0; i < longitud; i++) {
            // Seleccionar un carácter aleatorio de letras usando Math.random()
            int indiceAleatorio = (int) (Math.random() * letras.length());
            palabra.append(letras.charAt(indiceAleatorio));
        }

        return palabra.toString();
    }

    /**
     * Escribe en el archivo cada palabra generada, una por línea.
     * El archivo es creado o sobrescrito en cada ejecución.
     */
    public void escribirEnArchivo() {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            for (int i = 0; i < numPalabras; i++) {
                writer.write(generarPalabra() + "\n");
            }
            System.out.println("Se escribieron " + numPalabras + " palabras en el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee el contenido del archivo generado y lo muestra en la consola.
     */
    public void leerArchivo() {
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            int caracter;
            System.out.println("\nContenido del fichero:");
            while ((caracter = reader.read()) != -1) {
                System.out.print((char) caracter);
            }
            System.out.println(); // Salto de línea después de leer el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo principal que solicita el número de palabras al usuario, genera el archivo y lo lee.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicitar el número de palabras al usuario
        System.out.print("Introduce el número de palabras a generar: ");
        int numPalabras = sc.nextInt();


        // Crear una instancia de Lenguaje y escribir en el archivo
        Lenguaje lenguaje = new Lenguaje(numPalabras);
        lenguaje.escribirEnArchivo();

        // Leer y mostrar el contenido del archivo generado
        lenguaje.leerArchivo();

        sc.close();
    }
}
