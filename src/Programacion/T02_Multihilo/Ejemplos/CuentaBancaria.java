package Programacion.T02_Multihilo.Ejemplos;

public class CuentaBancaria {
    private int saldo = 100;

    public synchronized void depositar(int monto) {
        saldo += monto;
        System.out.println(Thread.currentThread().getName() + " depositó " + monto + ". Saldo: " + saldo);
    }

    public synchronized void retirar(int monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println(Thread.currentThread().getName() + " retiró " + monto + ". Saldo: " + saldo);
        } else {
            System.out.println(Thread.currentThread().getName() + " intentó retirar " + monto + ". Saldo insuficiente.");
        }
    }
}