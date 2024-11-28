package Programacion.T02_Multihilo.Ejemplos;

public class DetenerHiloMain {
    public static void main(String[] args) throws InterruptedException {
        DetenerHilo tarea = new DetenerHilo();
        Thread hilo = new Thread(tarea);
        hilo.start();

        Thread.sleep(2000);
        tarea.detener();
    }
}
