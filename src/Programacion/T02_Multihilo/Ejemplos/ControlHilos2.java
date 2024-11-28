package Programacion.T02_Multihilo.Ejemplos;

public class ControlHilos2 extends Thread {
    boolean ejecutar = true;
    private final Buffer2 buffer;

    public ControlHilos2(Buffer2 buffer) {
        this.buffer = buffer;
    }

    public void run() {
        int contador = 0;
        while (ejecutar) {
            buffer.agregar(contador);
            contador ++;
            System.out.println(Thread.currentThread().getName()+" Contador: "+contador);
            if (contador == 5) {
                ejecutar = false;
            }
        }
    }
}