package ProgramacionServiciosYProcesos.Examen.Portaaviones;

public class Avion {

	private String nombre;
	private int[] cargaPilon;

	public Avion() {
	}

	public Avion(String nombre, int[] cargaPilon) {
		this.nombre = nombre;
		this.cargaPilon = cargaPilon;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int[] getcargaPilon() {
		return cargaPilon;
	}

	public void setcargaPilon(int[] cargaPilon) {
		this.cargaPilon = cargaPilon;
	}

}
