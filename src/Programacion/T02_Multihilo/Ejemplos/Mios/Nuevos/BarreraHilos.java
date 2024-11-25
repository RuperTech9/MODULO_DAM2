package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;

/*
barrera que haga que tres hilos esperen hasta que todos estén listos antes de continuar.
 */
public class BarreraHilos {
    private int contador = 0;
    private final int limite;

    public BarreraHilos(int limite) {
        this.limite = limite;
    }

    public synchronized void esperar() throws InterruptedException {
        contador++;
        if (contador < limite) {
            wait();
        } else {
            notifyAll();
        }
    }
}
