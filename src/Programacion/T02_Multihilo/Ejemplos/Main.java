package Programacion.T02_Multihilo.Ejemplos;

public class Main {
    public static void main(String[] args) {
        ControlHilos hilo1 = new ControlHilos();
        ControlHilos hilo2 = new ControlHilos();

        System.out.println("Iniciando los hilos...");
        hilo1.start();
        hilo2.start();

        try {
            // Espera a que los hilos terminen
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Los hilos han terminado.");

        // Imprime el contenido del buffer después de que los hilos terminen
        Buffer buffer = new Buffer();
        buffer.imprimirBuffer();
    }
}