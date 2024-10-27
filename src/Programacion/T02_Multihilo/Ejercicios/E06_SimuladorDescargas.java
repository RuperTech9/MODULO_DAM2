package Programacion.T02_Multihilo.Ejercicios;

public class E06_SimuladorDescargas {
    public static void main(String[] args) {
        // Crear instancias de DescargaArchivo con nombres y tiempos de descarga
        E06_DescargaArchivo archivo1 = new E06_DescargaArchivo("Archivo1", 5);
        E06_DescargaArchivo archivo2 = new E06_DescargaArchivo("Archivo2", 5);

        // Crear hilos con diferentes prioridades
        Thread hilo1 = new Thread(archivo1);
        Thread hilo2 = new Thread(archivo2);

        // Asignar nombres a los hilos para fácil identificación
        hilo1.setName("Hilo-1");
        hilo2.setName("Hilo-2");

        // Configurar prioridades
        hilo1.setPriority(Thread.MIN_PRIORITY); // Prioridad mínima
        hilo2.setPriority(Thread.MAX_PRIORITY); // Prioridad máxima

        // Ejecución en diferentes órdenes
        System.out.println("Iniciando los hilos en orden Hilo-1 luego Hilo-2:");
        hilo1.start();
        hilo2.start();

        // Esperar a que terminen ambos hilos antes de la siguiente ejecución
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ejecución en el orden inverso
        System.out.println("\nIniciando los hilos en orden Hilo-2 luego Hilo-1:");
        hilo1 = new Thread(archivo1); // Necesitamos reiniciar los hilos
        hilo2 = new Thread(archivo2);

        hilo1.setName("Hilo-1");
        hilo2.setName("Hilo-2");

        hilo1.setPriority(Thread.MIN_PRIORITY); // Prioridad mínima
        hilo2.setPriority(Thread.MAX_PRIORITY); // Prioridad máxima

        hilo2.start();
        hilo1.start();
    }
}