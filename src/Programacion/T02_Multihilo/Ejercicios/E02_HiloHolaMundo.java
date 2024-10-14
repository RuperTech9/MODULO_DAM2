package Programacion.T02_Multihilo.Ejercicios;

// Clase que extiende Thread
class HiloHolaMundo extends Thread {
    public HiloHolaMundo(String nombre) {
        super(nombre); // Establecer el nombre del hilo
    }

    public void run() {
        System.out.println("Hola mundo desde el hilo: " + getName());
    }
}

public class E02_HiloHolaMundo {
    public static void main(String[] args) {
        // Crear e iniciar 5 hilos
        for (int i = 1; i < 6; i++) {
            HiloHolaMundo hilo = new HiloHolaMundo("Hilo " + i);
            hilo.start(); // Iniciar cada hilo
        }
    }
}