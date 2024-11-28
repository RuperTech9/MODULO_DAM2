package Programacion.T02_Multihilo.Ejemplos;

public class ComunicacionHilosMain {
    public static void main(String[] args) {
        ComunicacionHilos alternar = new ComunicacionHilos();

        Thread hilo1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) alternar.imprimirA();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread hilo2 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) alternar.imprimirB();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        hilo1.start();
        hilo2.start();
    }
}
