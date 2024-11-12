package Programacion.T02_Multihilo.Practica.Ejercicio5;

/**
 * La clase Ejercicio_6 prueba la funcionalidad de la clase HiloPrioridades al crear tres hilos con diferentes prioridades.
 * Cada hilo ejecuta una tarea distinta dependiendo de su prioridad. Los hilos se ejecutan durante 10 segundos y luego se interrumpen.
 *
 * Ejemplo de uso:
 * <pre>
 *     Ejercicio_6.main(new String[]{});
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class Ejercicio_6 {
    /**
     * Metodo principal que crea tres instancias de HiloPrioridades con diferentes prioridades,
     * las inicia y permite su ejecución durante 10 segundos antes de interrumpirlas.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        // Crear tres hilos con diferentes prioridades
        HiloPrioridades hilo1 = new HiloPrioridades("Hilo 1", 1);
        HiloPrioridades hilo2 = new HiloPrioridades("Hilo 2", 3);
        HiloPrioridades hilo3 = new HiloPrioridades("Hilo 3", 5);

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Permito que los hilos se ejecuten durante 10 segundos
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrumpo los hilos después de 10 segundos
        hilo1.interrupt();
        hilo2.interrupt();
        hilo3.interrupt();

        System.out.println("\nTodos los hilos han sido interrumpidos.");
    }
}