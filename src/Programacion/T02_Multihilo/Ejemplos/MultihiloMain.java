package Programacion.T02_Multihilo.Ejemplos;

public class MultihiloMain {
    public static void main(String[] args) {
        Thread hilo1 = new Multihilo();
        Thread hilo2 = new Multihilo();
        Thread hilo3 = new Multihilo();

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
