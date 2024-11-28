package Programacion.T02_Multihilo.Ejemplos;

public class ControlHilos extends Thread {
    boolean ejecutar = true;
    Buffer buffer = new Buffer();



    @Override
    public void run() {
        int contador = 0;
        while (ejecutar) {
            System.out.println(Thread.currentThread().getName() + " - Iteración: " + contador);
            buffer.agregarElemento(contador);
            contador++;
            if (contador == 5) {
                ejecutar = false;
            }
        }
    }
}