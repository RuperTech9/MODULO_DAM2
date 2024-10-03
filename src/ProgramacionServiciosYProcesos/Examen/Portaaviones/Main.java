package ProgramacionServiciosYProcesos.Examen.Portaaviones;

public class Main {

	public static void main(String[] args) {

		Avion avion1 = new Avion("F-18", new int[] { 3, 1, 2, 4, 1, 1 });
		Avion avion2 = new Avion("F-14", new int[] { 3, 2, 4, 1, 1 });

		Hangar Hangar1 = new Hangar("Hangar 1");
		Hangar Hangar2 = new Hangar("Hangar 2");

		// Tiempo de referencia inicial
		long initialTime = System.currentTimeMillis();

		Hangar1.armaAvion(avion1, initialTime);
		Hangar2.armaAvion(avion2, initialTime);
	}
}