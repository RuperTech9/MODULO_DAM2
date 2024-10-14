package Programacion.T02_Multihilo.Ejemplos.CreacionHilos;

public class PrimerHiloR implements Runnable {
	public void run() {
		//// Utilizar Thread.currentThread() para obtener el hilo actual
		System.out.println("Hola desde el Hilo! " + Thread.currentThread().getId());
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Thread hilo = new Thread(new PrimerHiloR());
			hilo.start();
		}
	}
	}
