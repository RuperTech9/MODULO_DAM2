package Programacion.T02_Multihilo.Ejemplos.Mios;

/*
hilo que imprima números del 1 al 5.
 */
public class RunnableHilo implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Número: " + i);
        }
    }
}
