package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;

/*
programa en el que dos hilos compartan un recurso, pero solo un hilo
puede acceder a él a la vez, utilizando la palabra clave synchronized.
 */
public class Semaforo {
    public synchronized void usarRecurso(String nombreHilo) {
        System.out.println(nombreHilo + " está usando el recurso.");
        try {
            Thread.sleep(1000); // Simula el uso del recurso
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nombreHilo + " ha terminado de usar el recurso.");
    }
}
