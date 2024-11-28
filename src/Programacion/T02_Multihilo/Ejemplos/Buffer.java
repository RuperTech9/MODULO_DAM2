package Programacion.T02_Multihilo.Ejemplos;

import java.util.ArrayList;

public class Buffer {
    private final ArrayList<Integer> buffer = new ArrayList<>();

    // Método para agregar elementos al buffer
    public synchronized void agregarElemento(int numero) {
        buffer.add(numero);
        System.out.println("Elemento agregado al buffer: " + numero);
        notify();
    }

    // Método para eliminar elementos del buffer
    public synchronized void eliminarElemento(int numero) throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        int elemento = buffer.remove(0);
        System.out.println("Elemento eliminado del buffer: " + elemento);
    }

    // Método para imprimir el contenido del buffer
    public synchronized void imprimirBuffer() {
        System.out.println("Contenido del buffer: " + buffer);
    }
}