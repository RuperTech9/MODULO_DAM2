package Programacion.T02_Multihilo.Ejercicios;

public class E10_Cola {
    private int numero;
    private boolean disponible = false; // inicialmente la cola está vacía

    // Método para obtener un valor de la cola
    public synchronized int get() {
        if (disponible) {
            disponible = false; // cola vacía
            return numero;
        }
        return -1; // si no hay valor disponible
    }

    // Método para colocar un valor en la cola
    public synchronized void put(int valor) {
        numero = valor;
        disponible = true; // cola llena
    }
}