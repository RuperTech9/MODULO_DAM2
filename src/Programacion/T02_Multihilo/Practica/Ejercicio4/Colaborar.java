package Programacion.T02_Multihilo.Practica.Ejercicio4;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Colaborar {

    private static final String RUTA_ARCHIVO = "./src/Programacion/T02_Multihilo/Practica/Ejercicio4/miFicheroColaborar.txt";

    // Metodo para limpiar el archivo al inicio
    private static void limpiarArchivo() {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            // Esto solo abre y cierra el archivo para borrarlo, no escribe nada
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo sincronizado que garantiza que solo un hilo pueda escribir en el archivo a la vez, evitando problemas de concurrencia.
    private static synchronized void escribirPalabrasEnArchivo(int numPalabras) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO, true)) { // Modo append para agregar sin sobrescribir
            Lenguaje lenguaje = new Lenguaje(numPalabras);
            for (int i = 0; i < numPalabras; i++) {
                writer.write(lenguaje.generarPalabra() + "\n");
            }
            System.out.println("Hilo con " + numPalabras + " palabras escritas en el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para leer y mostrar el contenido del archivo final
    private static void leerArchivoFinal() {
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            int caracter;
            System.out.println("\nContenido del fichero final:");
            while ((caracter = reader.read()) != -1) {
                System.out.print((char) caracter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        limpiarArchivo();
        List<Thread> hilos = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int numPalabras = i * 10;
            Thread hilo = new Thread(() -> escribirPalabrasEnArchivo(numPalabras));
            hilos.add(hilo);
            hilo.start();
        }
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Se generaron 550 palabras en el archivo " + RUTA_ARCHIVO);

        // Leer y mostrar el contenido del archivo final
        leerArchivoFinal();
    }
}
