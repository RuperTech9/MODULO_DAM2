package Programacion.T02_Multihilo.Ejercicios;

public class E08_GestionSaldo {
    public static void main(String[] args) {
        // Crear objeto E08_Saldo con un saldo inicial
        E08_Saldo saldo = new E08_Saldo(1000);
        System.out.println("E08_Saldo inicial: " + saldo.getSaldo());

        // Crear y asignar varias operaciones de saldo con hilos
        Thread hilo1 = new E08_OperacionSaldo(saldo, 200, "Hilo-1");
        Thread hilo2 = new E08_OperacionSaldo(saldo, 300, "Hilo-2");
        Thread hilo3 = new E08_OperacionSaldo(saldo, -150, "Hilo-3");

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperar a que los hilos finalicen
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostrar el saldo final
        System.out.println("E08_Saldo final: " + saldo.getSaldo());
    }
}