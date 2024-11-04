package Programacion.T02_Multihilo.Practica.Ejercicio1;

public class Ejercicio_1 {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            TareaCalculo_c hilos = new TareaCalculo_c("Hilo " + i);
            //hilos.start();

            Thread hilo = new Thread(hilos);
            hilo.start();
        }
        System.out.println("30 HILOS INICIADOS...");
    }
}