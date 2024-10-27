package Programacion.T02_Multihilo.Ejercicios;

// Clase E10_Productor sin sleep (más rápido que el consumidor)
public class E11_Productor extends Thread {
    private E11_Cola cola;
    private int n;

    public E11_Productor(E11_Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i);
            System.out.println(i + " => E12_Productor : " + n + ", produce: " + i);
            try {
                Thread.sleep(100); // Simulación de pausa en la producción
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fin productor...");
    }
}