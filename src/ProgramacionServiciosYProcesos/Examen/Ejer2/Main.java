package ProgramacionServiciosYProcesos.Examen.Ejer2;

public class Main {

	public static void main(String[] args) {

		EquipoF1 equipoF1 = new EquipoF1("Equipo Ferrari", new int[] { 6, 7 });
		EquipoF1 mcLaren = new EquipoF1("Equipo McLaren", new int[] { 8, 6 });
		EquipoF1 astonMartin = new EquipoF1("Equipo Aston Martin", new int[] { 8, 6 });

		// Tiempo de referencia inicial
		long timeStamp = System.currentTimeMillis();
		
		Boxes boxFerrari = new Boxes(1,"Box Ferrari", equipoF1,timeStamp);
		Boxes boxMcLaren = new Boxes(2,"Box McLaren", mcLaren, timeStamp);
		Boxes boxAstonMartin = new Boxes(3,"Box Aston Martin", astonMartin, timeStamp);

		

		boxFerrari.start();
		boxMcLaren.start();
		boxAstonMartin.start();
	}
}