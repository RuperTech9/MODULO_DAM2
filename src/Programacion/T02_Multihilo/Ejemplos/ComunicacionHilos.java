package Programacion.T02_Multihilo.Ejemplos;

public class ComunicacionHilos {
    private boolean turno = true;

    public synchronized void imprimirA() throws InterruptedException {
        while (!turno) wait();
        System.out.println("A");
        turno = false;
        notify();
    }

    public synchronized void imprimirB() throws InterruptedException {
        while (turno) wait();
        System.out.println("B");
        turno = true;
        notify();
    }
}
