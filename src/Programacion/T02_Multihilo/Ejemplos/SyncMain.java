package Programacion.T02_Multihilo.Ejemplos;

public class SyncMain {
    public static void main(String[] args) throws InterruptedException {
        SyncContador contador = new SyncContador();
        Thread hilo1 = new SyncHiloContador(contador);
        Thread hilo2 = new SyncHiloContador(contador);

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println("Contador final: " + contador.getContador());
    }
}
