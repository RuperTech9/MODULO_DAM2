package Programacion.T02_Multihilo.defensa;

import java.util.HashSet;

public class ConjuntoCompartido {
    private final HashSet<Integer> lista = new HashSet<Integer>();


    public synchronized void agregarElemento(int valor) {
        lista.add(valor);
        System.out.println("Elemento agregado: " + valor);
        notify();
    }

    public synchronized int retirarElemento() throws InterruptedException {
        while (lista.isEmpty()) {
            wait();  // Espera hasta que el conjunto no esté vacío
        }
        Integer valor = lista.iterator().next();
        lista.remove(valor);
        return valor;
    }

    public synchronized void imprimirElementos() {
        System.out.println("Elementos: " + lista);
    }

	//Getters y Setters
	
}