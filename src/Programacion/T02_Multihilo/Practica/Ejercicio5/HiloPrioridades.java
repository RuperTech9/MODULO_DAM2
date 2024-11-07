package Programacion.T02_Multihilo.Practica.Ejercicio5;

public class HiloPrioridades extends Thread {

    public HiloPrioridades(String nombre, int prioridad) {
        this.setName(nombre);           // Asignar nombre al hilo
        this.setPriority(prioridad);     // Asignar prioridad al hilo
    }

    @Override
    public void run() {
        System.out.println("Iniciando " + getName() + " con prioridad " + getPriority());

        while (true) {
            switch (getPriority()) {
                case 1:
                    tarea1();
                    break;
                case 3:
                    tarea3();
                    break;
                case 5:
                    tarea5();
                    break;
                default:
                    System.out.println("Prioridad " + getPriority() + " no tiene una tarea asignada en " + getName());
            }

            // Demora de 1 segundo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrumpido.");
                break;
            }
        }
    }

    // Metodo que se ejecuta si la prioridad es 1
    private void tarea1() {
        System.out.println(getName() + " con prioridad: " + getPriority()+ " - Tarea lenta.");
    }

    // Metodo que se ejecuta si la prioridad es 3
    private void tarea3() {
        System.out.println(getName() + " con prioridad: " + getPriority()+ " - Tarea normal.");
    }

    // Metodo que se ejecuta si la prioridad es 5
    private void tarea5() {
        System.out.println(getName() + " con prioridad: " + getPriority()+ " - Tarea rápida.");
    }
}