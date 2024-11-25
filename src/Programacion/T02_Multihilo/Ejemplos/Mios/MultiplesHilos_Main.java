package Programacion.T02_Multihilo.Ejemplos.Mios;

public class MultiplesHilos_Main {
    public static void main(String[] args) {
        Thread hilo1 = new MultiplesHilos();
        Thread hilo2 = new MultiplesHilos();
        Thread hilo3 = new MultiplesHilos();

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}