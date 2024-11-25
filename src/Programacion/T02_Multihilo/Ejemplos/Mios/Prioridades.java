package Programacion.T02_Multihilo.Ejemplos.Mios;

public class Prioridades {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hilo 1");
            }
        });

        Thread hilo2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hilo 2");
            }
        });

        hilo1.setPriority(Thread.MIN_PRIORITY);
        hilo2.setPriority(Thread.MAX_PRIORITY);

        hilo1.start();
        hilo2.start();
    }
}
