package Programacion.T02_Multihilo.Ejemplos.Mios;
/*
Tres hilos que impriman su nombre 5 veces.
 */
public class MultiplesHilos extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hilo: " + Thread.currentThread().getName());
        }
    }
}