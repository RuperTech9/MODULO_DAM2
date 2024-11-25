package Programacion.T02_Multihilo.Ejemplos.Mios;

public class ContadorHilo extends Thread {
    private Contador contador;

    public ContadorHilo(Contador contador) {
        this.contador = contador;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador.incrementar();
        }
    }
}
