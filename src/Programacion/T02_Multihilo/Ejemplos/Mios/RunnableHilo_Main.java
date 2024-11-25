package Programacion.T02_Multihilo.Ejemplos.Mios;

public class RunnableHilo_Main {
    public static void main(String[] args) {
        Thread hilo = new Thread(new RunnableHilo());
        hilo.start();
    }
}
