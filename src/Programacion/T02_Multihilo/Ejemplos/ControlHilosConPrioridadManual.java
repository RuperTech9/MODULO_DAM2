package Programacion.T02_Multihilo.Ejemplos;

public class ControlHilosConPrioridadManual extends Thread {
    private boolean ejecutar = true;
    private final BufferPrioridad buffer;
    private final boolean esAgregar;

    public ControlHilosConPrioridadManual(BufferPrioridad buffer, boolean esAgregar) {
        this.buffer = buffer;
        this.esAgregar = esAgregar;
    }

    public void run() {
        int contador = 0;
        while (ejecutar) {
            if (esAgregar) {
                buffer.agregar(contador);
                System.out.println(Thread.currentThread().getName() + " Agregó: " + contador);
                contador++;
            } else {
                try {
                    buffer.eliminar();
                    System.out.println(Thread.currentThread().getName() + " Eliminó un elemento.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(500); // Simula una pausa entre operaciones
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (contador == 5 && esAgregar) {
                ejecutar = false;
            }
        }
    }
}