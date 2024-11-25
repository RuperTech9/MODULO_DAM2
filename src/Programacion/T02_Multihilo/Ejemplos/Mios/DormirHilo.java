package Programacion.T02_Multihilo.Ejemplos.Mios;

public class DormirHilo {
    public static void main(String[] args) {
        Thread hilo = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Mensaje " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Hilo interrumpido");
                }
            }
        });
        hilo.start();
    }
}