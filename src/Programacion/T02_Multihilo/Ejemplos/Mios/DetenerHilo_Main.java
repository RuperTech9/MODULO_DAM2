package Programacion.T02_Multihilo.Ejemplos.Mios;

public class DetenerHilo_Main {
    public static void main(String[] args) throws InterruptedException {
        DetenerHilo tarea = new DetenerHilo();
        Thread hilo = new Thread(tarea);
        hilo.start();

        Thread.sleep(2000);
        tarea.detener();
    }
}

