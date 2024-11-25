package Programacion.T02_Multihilo.Ejemplos.Mios.Nuevos;

/*
programa donde un hilo incremente segundos, otro incremente minutos,
y otro incremente horas, sincronizándolos para simular un reloj.
 */

public class Reloj {
    private int horas = 0;
    private int minutos = 0;
    private int segundos = 0;

    public synchronized void incrementarSegundos() throws InterruptedException {
        segundos++;
        if (segundos == 60) {
            segundos = 0;
            incrementarMinutos();
        }
        notifyAll();
    }

    public synchronized void incrementarMinutos() {
        minutos++;
        if (minutos == 60) {
            minutos = 0;
            incrementarHoras();
        }
    }

    public synchronized void incrementarHoras() {
        horas = (horas + 1) % 24;
    }

    public synchronized void mostrarHora() {
        System.out.printf("Hora actual: %02d:%02d:%02d%n", horas, minutos, segundos);
    }
}
