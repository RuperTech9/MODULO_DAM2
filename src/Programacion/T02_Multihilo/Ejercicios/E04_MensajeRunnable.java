package Programacion.T02_Multihilo.Ejercicios;

public class E04_MensajeRunnable implements Runnable {
    String mensaje;

    public E04_MensajeRunnable(String mensaje) {
        this.mensaje = mensaje;
    }
    @Override
    public void run() {
        try{
            Thread.sleep(Thread.currentThread().getId() * 100);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Hola mundo " + mensaje + " desde el hilo: " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            new Thread(new E04_MensajeRunnable("mensaje " + i)).start();
        }
    }
}

/*
Comparación con y sin Thread.sleep():

Con Thread.sleep(): Los mensajes se imprimen con un retardo que depende del ID del hilo.
Esto puede resultar en una salida más ordenada, con hilos de menor ID apareciendo primero.

Sin Thread.sleep(): Todos los hilos intentan imprimir el mensaje inmediatamente,
lo que genera una salida sin un orden específico debido al comportamiento aleatorio de la
planificación de hilos por el sistema operativo.

Este ejercicio permite observar cómo sleep() puede influir en la sincronización y orden de ejecución de los hilos.
 */