package Programacion.T02_Multihilo.Practica.Ejercicio3;

import java.util.Scanner;

/**
 * La clase CuentaVocales utiliza 5 hilos para contar el número total de vocales en un texto dado.
 * Cada hilo cuenta una vocal específica (a, e, i, o, u) y actualiza de manera sincronizada
 * una variable compartida que representa el total de vocales encontradas en el texto.
 *
 * Ejemplo de uso:
 * <pre>
 *     CuentaVocales cuentaVocales = new CuentaVocales("Este es un ejemplo de texto.");
 *     cuentaVocales.contarVocales();
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class CuentaVocales {
    final String texto;
    static int totalVocales = 0; // Variable compartida para el total de vocales

    /**
     * Constructor que recibe el texto en el cual se realizará el conteo de vocales.
     *
     * @param texto Texto sobre el cual se realizará el conteo de vocales.
     */
    public CuentaVocales(String texto) {
        this.texto = texto.toLowerCase(); // Convertimos a minúsculas para simplificar
    }

    /**
     * Incrementa el total de vocales de manera sincronizada para evitar problemas de concurrencia.
     *
     * @param cantidad Número de vocales encontradas que se sumarán al total.
     */
    public synchronized static void incrementarTotal(int cantidad) {
        totalVocales += cantidad;
    }

    /**
     * Clase interna ContadorVocal que implementa Runnable y se encarga de contar la frecuencia
     * de una vocal específica en el texto. Al finalizar, actualiza el total de vocales de manera
     * sincronizada.
     */
    private class ContadorVocal implements Runnable {
        private final char vocal;

        /**
         * Constructor que recibe la vocal específica a contar en el texto.
         *
         * @param vocal La vocal a contar.
         */
        public ContadorVocal(char vocal) {
            this.vocal = vocal;
        }

        /**
         * Ejecuta el conteo de la vocal específica en el texto y actualiza el total
         * de vocales. Imprime la cantidad encontrada de dicha vocal.
         */
        @Override
        public void run() {
            int contador = 0;
            for (char c : texto.toCharArray()) {
                if (c == vocal) {
                    contador++;
                }
            }
            incrementarTotal(contador); // Actualizamos el total de vocales de manera sincronizada
            System.out.println("Vocal '" + vocal + "' encontrada " + contador + " veces.");
        }
    }

    /**
     * Inicia el proceso de conteo de vocales usando 5 hilos, uno para cada vocal.
     * Cada hilo cuenta su vocal respectiva y al finalizar se muestra el total de vocales encontradas.
     */
    public void contarVocales() {
        Thread hiloA = new Thread(new ContadorVocal('a'));
        Thread hiloE = new Thread(new ContadorVocal('e'));
        Thread hiloI = new Thread(new ContadorVocal('i'));
        Thread hiloO = new Thread(new ContadorVocal('o'));
        Thread hiloU = new Thread(new ContadorVocal('u'));

        // Iniciar los hilos
        hiloA.start();
        hiloE.start();
        hiloI.start();
        hiloO.start();
        hiloU.start();

        // Esperar a que todos los hilos terminen
        try {
            hiloA.join();
            hiloE.join();
            hiloI.join();
            hiloO.join();
            hiloU.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTotal de vocales en el texto: " + totalVocales);
    }

    /**
     * Metodo principal para ejecutar el programa. Solicita al usuario que ingrese un texto,
     * crea una instancia de CuentaVocales y ejecuta el conteo de vocales.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el texto: ");
        String texto = sc.nextLine();
        System.out.println();

        CuentaVocales cuentaVocales = new CuentaVocales(texto);
        cuentaVocales.contarVocales();
    }
}