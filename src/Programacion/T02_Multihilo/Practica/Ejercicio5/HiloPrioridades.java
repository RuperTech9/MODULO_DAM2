package Programacion.T02_Multihilo.Practica.Ejercicio5;

public class HiloPrioridades extends Thread {

    public HiloPrioridades(String nombre, int prioridad) {
        this.setName(nombre);           // Asignar nombre al hilo
        this.setPriority(prioridad);     // Asignar prioridad al hilo
    }

    @Override
    public void run() {
        System.out.println("Iniciando hilo [" + getName() + "] con prioridad " + getPriority());

        while (true) {
            switch (getPriority()) {
                case 1:
                    tarea1();
                    break;
                case 3:
                    tarea3();
                    break;
                case 5:
                case 10:  // Permitir que las prioridades 5 y 10 ejecuten tarea5()
                    tarea5();
                    break;
                default:
                    System.out.println("Prioridad " + getPriority() + " no tiene una tarea asignada en " + getName());
            }

            // Demora de 1 segundo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Hilo [" + getName() + "] interrumpido.");
                break;
            }
        }
    }

    // Metodo que se ejecuta si la prioridad es 1
    private void tarea1() {
        System.out.println("Ejecutando tarea1 en [" + getName() + "] con prioridad " + getPriority()+ " - Tarea lenta.");
    }

    // Metodo que se ejecuta si la prioridad es 3
    private void tarea3() {
        System.out.println("Ejecutando tarea3 en [" + getName() + "] con prioridad " + getPriority()+ " - Tarea normal.");
    }

    // Metodo que se ejecuta si la prioridad es 5
    private void tarea5() {
        System.out.println("Ejecutando tarea5 en [" + getName() + "] con prioridad " + getPriority()+ " - Tarea rápida.");
    }

    public static void main(String[] args) {
        HiloPrioridades h1 = new HiloPrioridades("Hilo1", 1);
        HiloPrioridades h2 = new HiloPrioridades("Hilo2", 3);
        HiloPrioridades h3 = new HiloPrioridades("Hilo3", 5);

        h1.start();
        h2.start();
        h3.start();

        // Ejecuto los hilos durante 5 segundos y luego los interrumpo
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        h1.interrupt();
        h2.interrupt();
        h3.interrupt();

        System.out.println("Todos los hilos han sido interrumpidos.");
    }
}