package Programacion.T02_Multihilo.Ejercicios;

public class E11_Cola {
    private int numero;
    private boolean disponible = false; // inicialmente la cola está vacía

    // Método para obtener un valor de la cola
    public synchronized int get() {
        while (!disponible) {
            try {
                wait(); // Espera hasta que haya un número disponible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false;
        notifyAll(); // Notifica al productor que la cola está libre para poner otro valor
        return numero;
    }

    // Método para colocar un valor en la cola
    public synchronized void put(int valor) {
        while (disponible) {
            try {
                wait(); // Espera hasta que el valor sea consumido
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numero = valor;
        disponible = true;
        notifyAll(); // Notifica a los consumidores que hay un nuevo valor disponible
    }
}