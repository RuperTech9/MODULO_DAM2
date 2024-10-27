package Programacion.T02_Multihilo.Ejercicios;

import java.util.Random;

public class E08_Saldo {
    private int saldo;

    public E08_Saldo(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Método para obtener el saldo actual
    public int getSaldo() {
        try {
            // Añadimos un tiempo de espera aleatorio para simular latencia
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return saldo;
    }

    // Método para establecer un saldo nuevo
    public void setSaldo(int saldo) {
        try {
            // Añadimos un tiempo de espera aleatorio para simular latencia
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.saldo = saldo;
    }

    // Método synchronized para añadir una cantidad al saldo
    public synchronized void agregarSaldo(int cantidad, String nombreHilo) {
        int saldoInicial = getSaldo();
        System.out.println(nombreHilo + " - E08_Saldo inicial: " + saldoInicial + ", Cantidad a añadir: " + cantidad);
        setSaldo(saldoInicial + cantidad);
        System.out.println(nombreHilo + " - E08_Saldo final: " + getSaldo());
    }
}
