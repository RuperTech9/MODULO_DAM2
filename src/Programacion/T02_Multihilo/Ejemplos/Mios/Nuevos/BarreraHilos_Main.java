package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;

public class BarreraHilos_Main {
    public static void main(String[] args) {
        BarreraHilos barrera = new BarreraHilos(3);

        Runnable tarea = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " esperando en la barrera.");
                barrera.esperar();
                System.out.println(Thread.currentThread().getName() + " cruzó la barrera.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread hilo1 = new Thread(tarea, "Hilo 1");
        Thread hilo2 = new Thread(tarea, "Hilo 2");
        Thread hilo3 = new Thread(tarea, "Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
