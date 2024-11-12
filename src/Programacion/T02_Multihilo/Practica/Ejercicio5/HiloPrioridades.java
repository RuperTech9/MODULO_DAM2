package Programacion.T02_Multihilo.Practica.Ejercicio5;

/**
 * La clase HiloPrioridades extiende Thread y permite crear hilos con diferentes prioridades.
 * Dependiendo de la prioridad asignada, el hilo ejecutará una de las tres tareas definidas,
 * simulando así distintos niveles de velocidad de procesamiento.
 *
 * Ejemplo de uso:
 * <pre>
 *     HiloPrioridades hilo1 = new HiloPrioridades("Hilo 1", 1);
 *     hilo1.start();
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class HiloPrioridades extends Thread {

    /**
     * Constructor que recibe el nombre y la prioridad del hilo.
     * Asigna el nombre y la prioridad al hilo usando los métodos setName() y setPriority().
     *
     * @param nombre Nombre del hilo.
     * @param prioridad Prioridad del hilo (1, 3, o 10).
     */
    public HiloPrioridades(String nombre, int prioridad) {
        this.setName(nombre);           // Asignar nombre al hilo
        this.setPriority(prioridad);     // Asignar prioridad al hilo
    }

    /**
     * Metodo run() ejecutado por el hilo. Dependiendo de la prioridad del hilo, ejecuta
     * una tarea específica: tarea1() para prioridad 1, tarea3() para prioridad 3 y
     * tarea5() para prioridad 10. Introduce una pausa de 1 segundo entre tareas.
     */
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
                case 10:
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

    /**
     * Tarea que se ejecuta cuando la prioridad del hilo es 1.
     * Imprime un mensaje indicando que es una tarea lenta.
     */
    private void tarea1() {
        System.out.println(getName() + " con prioridad: " + getPriority()+ " - Tarea lenta.");
    }

    /**
     * Tarea que se ejecuta cuando la prioridad del hilo es 3.
     * Imprime un mensaje indicando que es una tarea de velocidad normal.
     */
    private void tarea3() {
        System.out.println(getName() + " con prioridad: " + getPriority()+ " - Tarea normal.");
    }

    /**
     * Tarea que se ejecuta cuando la prioridad del hilo es 10.
     * Imprime un mensaje indicando que es una tarea rápida.
     */
    private void tarea5() {
        System.out.println(getName() + " con prioridad: " + getPriority()+ " - Tarea rápida.");
    }
}