package Programacion.T02_Multihilo.Practica.Ejercicio3;

import java.util.Scanner;

public class CuentaVocales {
    final String texto;
    static int totalVocales = 0; // Variable compartida para el total de vocales

    public CuentaVocales(String texto) {
        this.texto = texto.toLowerCase(); // Convertimos a minúsculas para simplificar
    }

    // Metodo sincronizado para incrementar el conteo total de vocales
    public synchronized static void incrementarTotal(int cantidad) {
        totalVocales += cantidad;
    }

    // Clase interna para contar una vocal específica
    private class ContadorVocal implements Runnable {
        private final char vocal;

        public ContadorVocal(char vocal) {
            this.vocal = vocal;
        }

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

    // Metodo para iniciar el conteo con 5 hilos, uno por cada vocal
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el texto: ");
        String texto = sc.nextLine();
        System.out.println();

        CuentaVocales cuentaVocales = new CuentaVocales(texto);
        cuentaVocales.contarVocales();
    }
}


/*
Explicación del Código
Variable Compartida:

totalVocales: Es una variable estática que representa el número total de vocales en el texto. Se utiliza una única variable para que todos los hilos puedan acceder y actualizarla.
Sincronización:

El método incrementarTotal está sincronizado para evitar condiciones de carrera al actualizar totalVocales. Esto garantiza que solo un hilo pueda incrementar el total de vocales a la vez.
Asignación de Tareas:

La clase interna ContadorVocal implementa Runnable y representa un contador específico para cada vocal (a, e, i, o, u).
Cada hilo cuenta una vocal específica y luego llama a incrementarTotal para actualizar el conteo total.
Inicio y Ejecución de Hilos:

En el método contarVocales, se crean 5 hilos, cada uno con una instancia de ContadorVocal para una vocal específica.
Los hilos se inician y luego el método join se utiliza para esperar a que cada hilo termine antes de imprimir el total de vocales.
Salida:

El programa muestra el conteo de cada vocal en la consola y luego imprime el total de vocales al final.
 */