package Programacion.T02_Multihilo.Practica;

public class Main {
    public static void main(String[] args) {
        Control hilo1 = new Control();
        Control hilo2 = new Control();

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Hilos terminados");
    }
}
