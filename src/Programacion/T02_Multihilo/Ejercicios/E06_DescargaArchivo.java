package Programacion.T02_Multihilo.Ejercicios;

class E06_DescargaArchivo implements Runnable {

    private String nombreArchivo;
    private int tiempoDescarga;

    // Constructor para inicializar el nombre del archivo y el tiempo de descarga
    public E06_DescargaArchivo(String nombreArchivo, int tiempoDescarga) {
        this.nombreArchivo = nombreArchivo;
        this.tiempoDescarga = tiempoDescarga;
    }

    @Override
    public void run() {
        System.out.println("Iniciando la descarga de " + nombreArchivo + " en el hilo: " + Thread.currentThread().getName());
        try {
            // Simula el tiempo de descarga con sleep
            Thread.sleep(tiempoDescarga * 1000); // tiempoDescarga en segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Descarga de " + nombreArchivo + " completada en el hilo: " + Thread.currentThread().getName());
    }
}