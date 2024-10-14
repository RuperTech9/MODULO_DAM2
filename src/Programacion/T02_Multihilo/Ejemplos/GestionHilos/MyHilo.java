package Programacion.T02_Multihilo.Ejemplos.GestionHilos;

import java.util.Scanner;

// Clase MyHilo que extiende Thread
class MyHilo extends Thread {
	private SolicitaSuspender suspender = new SolicitaSuspender();
	private boolean hayTrabajo = true; // Simula que hay trabajo por hacer
	private Integer contador = 0; // Variable contador que se incrementará

	// Metodo que suspende el hilo
	public void suspende() {
		suspender.set(true);
	}
	// Metodo que reanuda el hilo
	public void reanuda() {
		suspender.set(false);
	}

	// Metodo que devuelve el valor del contador
	public int getContador() {
		return contador;
	}

	// Simulación del método run con una tarea
	public void run() {
		try {
			while (hayTrabajo) { // Simulamos que el hilo tiene trabajo por hacer
				contador++; // Incrementamos el contador
				System.out.println("Contador: " + contador);

				// Pausa de 1 segundo para simular tiempo de trabajo
				Thread.sleep(1000);

				// Comprobación de si el hilo debe suspenderse
				suspender.esperandoParaReanudar();
			}
			System.out.println("Terminado");
		} catch (InterruptedException exception) {
			System.out.println("El hilo ha sido interrumpido.");
		}
	}

	// Método para detener el trabajo del hilo
	public void detenerTrabajo() {
		hayTrabajo = false; // Cambia la condición del bucle y detiene el hilo
	}

	public static void main(String[] args) throws InterruptedException {
		MyHilo hilo = new MyHilo();
		Scanner scanner = new Scanner(System.in);
		boolean hiloLanzado = false; // Controla si el hilo se ha lanzado

		System.out.println("Introduce S para suspender, R para reanudar, * para salir:");

		while (true) {
			String input = scanner.nextLine().trim();

			if (input.equals("*")) {
				break; // Si el input es *, termina el programa
			}

			if (!hiloLanzado) {
				hilo.start(); // Lanzar el hilo la primera vez que se introduce un comando
				hiloLanzado = true;
			}

			if (input.equalsIgnoreCase("S")) {
				System.out.println("Suspendiendo el hilo...");
				hilo.suspende();
			} else if (input.equalsIgnoreCase("R")) {
				System.out.println("Reanudando el hilo...");
				hilo.reanuda();
			}
		}

		// Al finalizar el bucle, se muestra el valor del contador y se finaliza el hilo
		System.out.println("Valor final del contador: " + hilo.getContador());
		hilo.detenerTrabajo(); // Detenemos el trabajo del hilo
		hilo.interrupt(); // Interrumpimos el hilo para terminar su ejecución
		scanner.close();
	}
}

