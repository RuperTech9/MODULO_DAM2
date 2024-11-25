package Programacion.T02_Multihilo.Practica.Ejercicio1;

/**
 * La clase Ejercicio_1 lanza 30 instancias de TareaCalculo en hilos independientes.
 * Cada hilo calcula una suma acumulada de valores aleatorios y la imprime en la consola.
 *
 * @author Ruper
 * @version 1.0
 */
public class Ejercicio_1 {
    /**
     * Metodo principal que crea y lanza 30 hilos de la clase TareaCalculo.
     * Cada hilo ejecuta una tarea de cálculo independiente.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            TareaCalculo hilos = new TareaCalculo("Hilo " + i);
            hilos.start(); // Parte a

            Thread hilo = new Thread(hilos); // Parte b y c
            hilo.start();

        }
        System.out.println("30 HILOS INICIADOS...");
    }
}