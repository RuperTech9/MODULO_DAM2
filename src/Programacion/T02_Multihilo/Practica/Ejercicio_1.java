package Programacion.T02_Multihilo.Practica;

public class Ejercicio_1 {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {

            TareaCalculo tarea = new TareaCalculo("Hilo " + i);
            tarea.start();
        }
        System.out.println("30 HILOS INICIADOS...");
    }
}