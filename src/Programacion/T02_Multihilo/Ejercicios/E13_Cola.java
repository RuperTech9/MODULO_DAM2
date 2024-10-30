package Programacion.T02_Multihilo.Ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class E13_Cola {
    private Character caracter;
    private boolean disponible = false;
    private AtomicBoolean productorFinalizado = new AtomicBoolean(false); // Indica si el productor ha terminado

    // Método para obtener un carácter de la cola
    public synchronized Character get() {
        while (!disponible && !productorFinalizado.get()) {
            try {
                wait(); // Espera hasta que haya un carácter disponible o el productor finalice
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!disponible && productorFinalizado.get()) {
            return null; // Si el productor finalizó y no hay más datos, retornar null
        }
        disponible = false;
        notifyAll(); // Notifica al productor que la cola está vacía
        return caracter;
    }

    // Método para colocar un carácter en la cola
    public synchronized void put(Character caracter) {
        while (disponible) {
            try {
                wait(); // Espera hasta que el carácter sea consumido
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.caracter = caracter;
        disponible = true;
        notifyAll(); // Notifica a los consumidores que hay un nuevo carácter disponible
    }

    // Método para indicar que el productor ha finalizado
    public void setProductorFinalizado() {
        productorFinalizado.set(true);
        synchronized (this) {
            notifyAll(); // Despierta a todos los consumidores en espera
        }
    }

    public boolean isProductorFinalizado() {
        return productorFinalizado.get();
    }
}