package Programacion.T02_Multihilo.Ejercicios;

public class E13_Consumidor extends Thread {
    private E13_Cola cola;
    private int id;

    public E13_Consumidor(E13_Cola cola, int id) {
        this.cola = cola;
        this.id = id;
    }

    public void run() {
        Character caracter;
        while ((caracter = cola.get()) != null) {
            System.out.println("Consumidor " + id + " consume: " + caracter);
        }
        System.out.println("Fin del consumidor " + id);
    }
}