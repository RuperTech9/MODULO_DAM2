package Programacion.T02_Multihilo.Practica.Ejercicio4;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Colaborar utiliza múltiples hilos de la clase Lenguaje para generar palabras en un archivo común.
 * Cada hilo genera un número creciente de palabras y las escribe en el archivo, colaborando en la escritura de un archivo final.
 *
 * Ejemplo de uso:
 * <pre>
 *     Colaborar.main(new String[]{});
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class Colaborar {

    private static final String RUTA_ARCHIVO = "./src/Programacion/T02_Multihilo/Practica/Ejercicio4/miFicheroColaborar.txt";

    /**
     * Limpia el contenido del archivo antes de iniciar la escritura de los hilos.
     */
    private static void limpiarArchivo() {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            // Esto solo abre y cierra el archivo para borrarlo, no escribe nada
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Escribe palabras generadas por un hilo en el archivo, con un número de palabras específico.
     * Este metodo es sincronizado para evitar problemas de concurrencia.
     *
     * @param numPalabras Número de palabras que el hilo generará y escribirá en el archivo.
     */
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

    /**
     * Lee y muestra el contenido del archivo generado por múltiples hilos.
     */
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

    /**
     * Metodo principal que ejecuta la colaboración multihilo, creando y ejecutando 10 hilos,
     * cada uno con un número creciente de palabras, y asegura la sincronización.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
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
