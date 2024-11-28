package Programacion.T02_Multihilo.defensa;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConjuntoCompartido conjunto = new ConjuntoCompartido();

        ControlHilos hilo1 = new ControlHilos(conjunto);
        ControlHilos hilo2 = new ControlHilos(conjunto);


        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();


        System.out.println("Todos los hilos han terminado su ejecución.");
        conjunto.imprimirElementos();
    }
}
