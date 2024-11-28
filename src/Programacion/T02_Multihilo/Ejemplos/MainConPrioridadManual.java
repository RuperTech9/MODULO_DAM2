package Programacion.T02_Multihilo.Ejemplos;

public class MainConPrioridadManual {
    public static void main(String[] args) {
        BufferPrioridad buffer = new BufferPrioridad();

        // Crea hilos de agregar y eliminar
        ControlHilosConPrioridadManual hiloAgregar = new ControlHilosConPrioridadManual(buffer, true);
        ControlHilosConPrioridadManual hiloEliminar = new ControlHilosConPrioridadManual(buffer, false);

        System.out.println("Iniciando los hilos...");
        hiloAgregar.start();
        hiloEliminar.start();

        try {
            hiloAgregar.join();
            hiloEliminar.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hilos terminados.");
        buffer.imprimir();
    }
}
