package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;

public class Reloj_Main {
    public static void main(String[] args) {
        Reloj reloj = new Reloj();

        Thread hiloSegundos = new Thread(() -> {
            try {
                while (true) {
                    reloj.incrementarSegundos();
                    reloj.mostrarHora();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        hiloSegundos.start();
    }
}
