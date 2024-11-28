package Programacion.T02_Multihilo.Ejemplos;

public class ContadorMain {
    public static void main(String[] args) {
        ContadorSincronizado contador = new ContadorSincronizado();

        Runnable tareaIncrementar = () -> {
            for (int i = 0; i < 5; i++) {
                contador.incrementar();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable tareaDecrementar = () -> {
            for (int i = 0; i < 5; i++) {
                contador.decrementar();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread hilo1 = new Thread(tareaIncrementar, "Hilo 1");
        Thread hilo2 = new Thread(tareaIncrementar, "Hilo 2");
        Thread hilo3 = new Thread(tareaDecrementar, "Hilo 3");
        Thread hilo4 = new Thread(tareaDecrementar, "Hilo 4");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
    }
}

