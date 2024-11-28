package Programacion.T02_Multihilo.Ejemplos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class BufferPrioridad {
    private final HashSet<Integer> lista = new HashSet<Integer>();

    public synchronized void agregar(int numero) {
        lista.add(numero);
        System.out.println("Elemento agregado: " + numero);
        notify();
    }

    public synchronized void eliminar() throws InterruptedException {
        while (lista.isEmpty()) {
            wait();
        }
        // Busca el elemento con menor valor (prioridad más alta)
        int menor = Collections.min(lista);
        lista.remove((Integer) menor);
        System.out.println("Elemento eliminado con mayor prioridad (menor valor): " + menor);
        notify();
    }

    public synchronized void imprimir() {
        System.out.println("Contenido: " + lista);
    }
}