package Programacion.T02_Multihilo.Ejemplos.CrearHilos;

public class PrimerHiloR implements Runnable {
	public void run() {
		System.out.println("Hola desde el Hilo! " +
	       Thread.currentThread().getId());
	}
}
