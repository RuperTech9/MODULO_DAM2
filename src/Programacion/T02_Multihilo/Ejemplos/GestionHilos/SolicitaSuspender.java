package Programacion.T02_Multihilo.Ejemplos.GestionHilos;

public class SolicitaSuspender {
	private boolean suspender;

	// Cambiado a public para permitir su acceso desde otras clases
	public synchronized void set(boolean b) {
		suspender = b; // Cambio de estado sobre el objeto
		notifyAll(); // Notificar al hilo en espera (si solo hay uno)
	}

	public synchronized void esperandoParaReanudar() throws InterruptedException {
		// Mientras la bandera "suspender" sea true, el hilo se suspende
		while (suspender) {
			wait(); // Suspender el hilo hasta recibir notify()
		}
	}
}
