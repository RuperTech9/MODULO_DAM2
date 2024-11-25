package Programacion.T02_Multihilo.Ejemplos.Mios;

public class DetenerHilo implements Runnable {
    private boolean correr = true;

    public void detener() {
        correr = false;
    }

    public void run() {
        while (correr) {
            System.out.println("Hilo corriendo...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Hilo detenido");
    }
}

