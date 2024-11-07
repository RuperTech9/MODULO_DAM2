package Programacion.T02_Multihilo.Practica.Ejercicio5;

public class Ejercicio_6 {

    public static void main(String[] args) {
        // Crear tres hilos con diferentes prioridades
        HiloPrioridades hiloBajo = new HiloPrioridades("HiloBajo", 1);
        HiloPrioridades hiloMedio = new HiloPrioridades("HiloMedio", 3);
        HiloPrioridades hiloAlto = new HiloPrioridades("HiloAlto", 5);

        // Iniciar los hilos
        hiloBajo.start();
        hiloMedio.start();
        hiloAlto.start();

        // Permitir que los hilos se ejecuten durante unos segundos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrumpir los hilos después de 5 segundos
        hiloBajo.interrupt();
        hiloMedio.interrupt();
        hiloAlto.interrupt();

        System.out.println("Todos los hilos han sido interrumpidos.");
    }
}