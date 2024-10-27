package Programacion.T02_Multihilo.Ejercicios;

public class E03_HiloHolaMundoRunnable  implements Runnable {
    @Override
    public void run() {
        System.out.println("Hola Mundo en hilo: " + Thread.currentThread().getId());
    }
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new E03_HiloHolaMundoRunnable()).start();
        }
    }
}
