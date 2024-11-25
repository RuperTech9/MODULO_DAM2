package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;
/*
Crea un programa que coordine tres hilos para imprimir mensajes en orden.
El primer hilo imprime "A", el segundo "B" y el tercero "C", repitiéndose en el mismo orden.
 */
public class Turnos {
    private int turno = 1;

    public synchronized void imprimirA() throws InterruptedException {
        while (turno != 1) wait();
        System.out.print("A");
        turno = 2;
        notifyAll();
    }

    public synchronized void imprimirB() throws InterruptedException {
        while (turno != 2) wait();
        System.out.print("B");
        turno = 3;
        notifyAll();
    }

    public synchronized void imprimirC() throws InterruptedException {
        while (turno != 3) wait();
        System.out.print("C");
        turno = 1;
        notifyAll();
    }
}
