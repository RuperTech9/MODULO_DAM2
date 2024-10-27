package Programacion.T02_Multihilo.Ejercicios;

// Clase E10_Consumidor con sleep (más lento que el productor)
public class E11_Consumidor extends Thread {
    private E11_Cola cola;
    private int n;

    public E11_Consumidor(E11_Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        int valor;
        for (int i = 0; i < 5; i++) {
            valor = cola.get();
            System.out.println(i + " => E10_Consumidor: " + n + ", consume: " + valor);
            try {
                Thread.sleep(100); // Añadimos sleep() para hacer al consumidor más lento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}