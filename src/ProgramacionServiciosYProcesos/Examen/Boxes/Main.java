
public class Main {

	public static void main(String[] args) {

		EquipoF1 ferrari = new EquipoF1("Equipo Ferrari", new int[] { 6, 7 });
		EquipoF1 mcLaren = new EquipoF1("Equipo McLaren", new int[] { 8, 6 });
		EquipoF1 astonMartin = new EquipoF1("Equipo Aston Martin", new int[] { 8, 6 });

		Box boxFerrari = new Box("Box Ferrari");
		Box boxMcLaren = new Box("Box McLaren");
		Box boxAstonMartin = new Box("Box Aston Martin");

		// Tiempo de referencia inicial
		long initialTime = System.currentTimeMillis();

		boxFerrari.cambiaRuedas(ferrari, initialTime);
		boxMcLaren.cambiaRuedas(mcLaren, initialTime);
		boxAstonMartin.cambiaRuedas(astonMartin, initialTime);
	}
}