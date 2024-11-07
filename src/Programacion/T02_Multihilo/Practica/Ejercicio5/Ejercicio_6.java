package Programacion.T02_Multihilo.Practica.Ejercicio5;

public class Ejercicio_6 {

    public static void main(String[] args) {
        // Crear tres hilos con diferentes prioridades
        HiloPrioridades hilo1 = new HiloPrioridades("Hilo 1", 1);
        HiloPrioridades hilo2 = new HiloPrioridades("Hilo 2", 3);
        HiloPrioridades hilo3 = new HiloPrioridades("Hilo 3", 5);

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Permito que los hilos se ejecuten durante 10 segundos
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrumpo los hilos después de 10 segundos
        hilo1.interrupt();
        hilo2.interrupt();
        hilo3.interrupt();

        System.out.println("\nTodos los hilos han sido interrumpidos.");
    }
}