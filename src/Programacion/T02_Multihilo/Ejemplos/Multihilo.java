package Programacion.T02_Multihilo.Ejemplos;

public class Multihilo extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hilo: " + Thread.currentThread().getName());
        }
    }
}
