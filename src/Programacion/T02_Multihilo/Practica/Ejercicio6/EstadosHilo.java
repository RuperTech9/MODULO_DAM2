package Programacion.T02_Multihilo.Practica.Ejercicio6;

public class EstadosHilo extends Thread {
    private final Object lock = new Object();

    public EstadosHilo(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        System.out.println(getName() + " - Estado: RUNNABLE");

        try {
            // Estado TIMED_WAITING con sleep()
            System.out.println(getName() + " - Entrando en TIMED_WAITING (sleep)...");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println(getName() + " - Salió de TIMED_WAITING (sleep)");

            // Estado WAITING con wait()
            synchronized (lock) {
                System.out.println(getName() + " - Entrando en WAITING (wait)...");
                lock.wait(); // El hilo queda en espera hasta recibir notify()
                System.out.println(getName() + " - Salió de WAITING (wait)");
            }

        } catch (InterruptedException e) {
            System.out.println(getName() + " - Interrumpido");
        }

        System.out.println(getName() + " - Estado: TERMINATED");
    }

    public static void main(String[] args) {
        // Crear instancia de EstadosHilo
        EstadosHilo hilo1 = new EstadosHilo("Hilo1");

        System.out.println("Estado de " + hilo1.getName() + " antes de start(): " + hilo1.getState()); // NEW

        // Iniciar el hilo (de NEW a RUNNABLE)
        hilo1.start();

        try {
            // Pausa para permitir que el hilo entre en TIMED_WAITING
            Thread.sleep(500); // Deja que "Hilo1" entre en TIMED_WAITING por sleep()

            // Verificar estado después de sleep()
            System.out.println("Estado de " + hilo1.getName() + " durante sleep(): " + hilo1.getState()); // TIMED_WAITING

            // Pausa adicional para que el hilo llegue a WAITING
            Thread.sleep(1500);

            // Estado cuando el hilo está en WAITING (por wait())
            synchronized (hilo1.lock) {
                System.out.println("Estado de " + hilo1.getName() + " antes de notify(): " + hilo1.getState()); // WAITING
                hilo1.lock.notify(); // Notificar al hilo para salir de WAITING
            }

            // Esperar que el hilo termine
            hilo1.join(); // De RUNNABLE a TERMINATED

            System.out.println("Estado de " + hilo1.getName() + " después de join(): " + hilo1.getState()); // TERMINATED

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
