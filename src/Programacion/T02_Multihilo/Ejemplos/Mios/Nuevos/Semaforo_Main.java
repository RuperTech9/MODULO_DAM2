package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;

/*
programa en el que dos hilos compartan un recurso, pero solo un hilo
puede acceder a él a la vez, utilizando la palabra clave synchronized.
 */
public class Semaforo_Main {
    public static void main(String[] args) {
        Semaforo semaforo = new Semaforo();

        Runnable tarea = () -> {
            String nombreHilo = Thread.currentThread().getName();
            semaforo.usarRecurso(nombreHilo);
        };

        Thread hilo1 = new Thread(tarea, "Hilo 1");
        Thread hilo2 = new Thread(tarea, "Hilo 2");

        hilo1.start();
        hilo2.start();
    }
}
