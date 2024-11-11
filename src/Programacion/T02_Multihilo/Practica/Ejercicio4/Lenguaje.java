package Programacion.T02_Multihilo.Practica.Ejercicio4;

import java.io.*;
import java.util.Scanner;

public class Lenguaje {
    int numPalabras;      // Número de palabras a generar
    final String RUTA_ARCHIVO = "./src/Programacion/T02_Multihilo/Practica/Ejercicio4/miFichero.txt"; // Nombre del archivo de salida

    public Lenguaje(int numPalabras) {
        this.numPalabras = numPalabras;
    }

    // Metodo para generar una palabra aleatoria de entre 5 y 10 caracteres
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

    // Metodo para escribir las palabras generadas en el archivo
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
    // Metodo para leer el archivo y mostrar su contenido en la consola
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

    // Metodo principal que solicita datos al usuario e inicia el proceso
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
