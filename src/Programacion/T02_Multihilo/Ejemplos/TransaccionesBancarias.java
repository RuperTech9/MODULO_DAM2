package Programacion.T02_Multihilo.Ejemplos;

public class TransaccionesBancarias {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();

        Thread depositos = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                cuenta.depositar(50);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Hilo Depositos");

        Thread retiros = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                cuenta.retirar(30);
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Hilo Retiros");

        depositos.start();
        retiros.start();
    }
}