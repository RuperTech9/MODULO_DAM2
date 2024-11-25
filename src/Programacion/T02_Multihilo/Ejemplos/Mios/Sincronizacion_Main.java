package Programacion.T02_Multihilo.Ejemplos.Mios;

public class Sincronizacion_Main {
    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador();
        Thread hilo1 = new ContadorHilo(contador);
        Thread hilo2 = new ContadorHilo(contador);

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println("Contador final: " + contador.getContador());
    }
}
