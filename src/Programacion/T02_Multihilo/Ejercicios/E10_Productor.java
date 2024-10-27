package Programacion.T02_Multihilo.Ejercicios;

// Clase E10_Productor sin sleep (más rápido que el consumidor)
public class E10_Productor extends Thread {
    private E10_Cola cola;
    private int n;

    public E10_Productor(E10_Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i);
            System.out.println(i + " => E10_Productor : " + n + ", produce: " + i);
            // Eliminamos el sleep() aquí
        }
    }
}