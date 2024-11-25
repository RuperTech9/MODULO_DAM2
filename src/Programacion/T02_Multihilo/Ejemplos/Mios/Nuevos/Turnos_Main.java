package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;

public class Turnos_Main {
    public static void main(String[] args) {
        Turnos turnos = new Turnos();

        Thread hiloA = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) turnos.imprimirA();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread hiloB = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) turnos.imprimirB();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread hiloC = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) turnos.imprimirC();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        hiloA.start();
        hiloB.start();
        hiloC.start();
    }
}
