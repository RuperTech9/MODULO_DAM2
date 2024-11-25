package Programacion.T02_Multihilo.Ejemplos.Mios;

public class Contador {
    private int contador = 0;

    public synchronized void incrementar() {
        contador++;
    }

    public int getContador() {
        return contador;
    }
}

