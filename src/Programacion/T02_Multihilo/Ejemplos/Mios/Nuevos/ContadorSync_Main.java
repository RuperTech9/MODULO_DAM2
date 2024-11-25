package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;

public class ContadorSync_Main {
    public static void main(String[] args) throws InterruptedException {
        ContadorSync contador = new ContadorSync();

        Runnable tarea = () -> {
            for (int i = 0; i < 1000; i++) {
                contador.incrementar();
            }
        };

        Thread hilo1 = new Thread(tarea);
        Thread hilo2 = new Thread(tarea);
        Thread hilo3 = new Thread(tarea);
        Thread hilo4 = new Thread(tarea);
        Thread hilo5 = new Thread(tarea);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();
        hilo5.join();

        System.out.println("Valor final del contador: " + contador.getContador());
    }
}
