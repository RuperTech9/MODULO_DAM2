package Programacion.T02_Multihilo.Practica.Ejercicio6;

public class EstadosHilo {

    public void verEstadoHilo() throws InterruptedException {
        // Hilo principal para demostrar los diferentes estados
        Thread hilo1 = new Thread(() -> {
            try {
                // Sleep para simular el estado WAITING
                Thread.sleep(1000);

                synchronized (this) { // Solo se puede llamar a wait() dentro de un bloque sincronizado
                    System.out.println("Estado hilo 1 con wait(): WAITING");
                    wait(); // Entra en WAITING hasta que reciba notify()
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Hilo interrumpido");
            }
        });

        // Imprimir estados en diferentes puntos clave
        System.out.println("Estado hilo 1 inicial: " + hilo1.getState()); // NEW
        hilo1.start();
        System.out.println("Estado hilo 1 después de iniciar: " + hilo1.getState()); // RUNNABLE
        Thread.sleep(200); // Pequeña espera para asegurar que hilo1 está en sleep
        System.out.println("Estado hilo 1 durante sleep: " + hilo1.getState() +"\n"); // TIMED_WAITING


        // Inicio otro hilo para notificar y probar el estado WAITING
        Thread hilo2 = new Thread(() -> {
            try {
                Thread.sleep(1500); // Espera para asegurar que hilo1 esté en wait()
                synchronized (this) { // Solo se puede llamar a notify() dentro de un bloque sincronizado
                    notify(); // Despierta a hilo1 del estado WAITING
                    System.out.println("Estado hilo 1 después de notify(): "  + Thread.currentThread().getState());
                    System.out.println("Estado hilo 2 después de notify(): "  + Thread.currentThread().getState());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        System.out.println("Estado hilo 2 inicial: " + hilo2.getState()); // NEW
        hilo2.start();
        System.out.println("Estado hilo 2 después de iniciar: " + hilo2.getState());
        Thread.sleep(200); // Pequeña espera para asegurar que hilo1 está en sleep
        System.out.println("Estado hilo 2 durante sleep: " + hilo2.getState()+ "\n"); // TIMED_WAITING

        hilo2.join(); // Espera que hilo2 termine
        System.out.println("\nEstado hilo2 despues de join(): " + hilo2.getState());

        hilo1.join(); // Espera que hilo1 termine
        System.out.println("Estado hilo1 despues de join(): " + hilo1.getState());
    }


    public static void main(String[] args) {
        EstadosHilo estadosHilo = new EstadosHilo();
        try {
            estadosHilo.verEstadoHilo();
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido");
        }
    }
}

/* RESUMEN
Este recorrido ilustra cómo los hilos pasan por diferentes estados (NEW, RUNNABLE, TIMED_WAITING, WAITING, y TERMINATED)
mientras ejecutan sleep, wait, notify, y join.

- wait() pone a hilo1 en WAITING hasta recibir notify().
- notify() despierta a hilo1 de WAITING a RUNNABLE.
- join() en hilo2 y hilo1 asegura que el hilo principal espere hasta que ambos hilos terminen.


wait(): Pone al hilo en (WAITING).
join(): Pone al hilo en (WAITING). -SYNC-
notify(): Despierta a un hilo que está en (WAITING). -SYNC-
sleep(): Pone al hilo en (TIMED_WAITING) durante un tiempo específico

wait(): Se usa en situaciones donde varios hilos comparten un recurso y necesitan comunicarse entre sí.
join(): Es útil cuando tienes varios hilos que realizan tareas diferentes y necesitas esperar a que uno o más de ellos terminen antes de continuar.

 */