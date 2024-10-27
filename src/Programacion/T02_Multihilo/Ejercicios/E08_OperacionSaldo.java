package Programacion.T02_Multihilo.Ejercicios;

public class E08_OperacionSaldo extends Thread {
    private final E08_Saldo saldo;
    private final int cantidad;

    public E08_OperacionSaldo(E08_Saldo saldo, int cantidad, String nombre) {
        super(nombre); // Asignar nombre al hilo
        this.saldo = saldo;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        saldo.agregarSaldo(cantidad, getName()); // Añadir saldo usando el nombre del hilo
    }
}