package Programacion.T02_Multihilo.Ejemplos;

public class SyncHiloContador extends Thread {
    private SyncContador contador;

    public SyncHiloContador(SyncContador contador) {
        this.contador = contador;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador.incrementar();
        }
    }
}
