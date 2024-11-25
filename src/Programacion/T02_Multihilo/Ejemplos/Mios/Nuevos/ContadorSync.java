package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;

/*
programa donde cinco hilos incrementen un contador global, pero asegúrate
de que la operación sea sincronizada para evitar condiciones de carrera.
 */
public class ContadorSync {
    private int contador = 0;

    public synchronized void incrementar() {
        contador++;
    }

    public synchronized int getContador() {
        return contador;
    }
}


