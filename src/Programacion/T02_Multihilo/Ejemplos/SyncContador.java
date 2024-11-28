package Programacion.T02_Multihilo.Ejemplos;

public class SyncContador {
    private int contador = 0;

    public synchronized void incrementar() {
        contador++;
    }

    public int getContador() {
        return contador;
    }
}
