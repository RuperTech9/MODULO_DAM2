package Programacion.T02_Multihilo.Ejemplos;

public class ContadorSincronizado {
    private int contador = 0;

    public synchronized void incrementar() {
        contador++;
        System.out.println(Thread.currentThread().getName() + " incrementó. Contador: " + contador);
    }

    public synchronized void decrementar() {
        contador--;
        System.out.println(Thread.currentThread().getName() + " decrementó. Contador: " + contador);
    }
}
