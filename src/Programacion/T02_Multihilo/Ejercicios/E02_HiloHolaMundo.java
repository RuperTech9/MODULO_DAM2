package Programacion.T02_Multihilo.Ejercicios;

// Clase que extiende Thread
public class E02_HiloHolaMundo extends Thread {

    public void run() {
        System.out.println("Hola mundo desde el hilo: " + getId());
    }

    public static void main(String[] args) {
        // Crear e iniciar 5 hilos
        for (int i = 1; i < 6; i++) {
            new Thread(new E02_HiloHolaMundo()).start();

        }
    }
}