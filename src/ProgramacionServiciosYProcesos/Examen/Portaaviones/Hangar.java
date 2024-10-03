package ProgramacionServiciosYProcesos.Examen.Portaaviones;

public class Hangar {

	private String nombre;

	public Hangar() {
	}

	public Hangar(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void armaAvion(Avion avion, long timeStamp) {
		
		System.out.println("El Hangar " + this.nombre + 
				" EMPIEZA A CARGAR LAS ARMAS DEL AVION " + avion.getNombre() + 
				" EN EL INSTANTE: " + ((System.currentTimeMillis()/1000) - (timeStamp / 1000))	+
				"seg");

		for (int i = 0; i < avion.getcargaPilon().length; i++) {
			this.esperaTiempo(avion.getcargaPilon()[i]);
			System.out.println("Procesado el pil�n " + (i + 1) + 
					" est� tardando en total: " + ((System.currentTimeMillis()/1000) - (timeStamp / 1000)) + 
					" segundos");
		}

		System.out.println("El Hangar " + this.nombre + " HA TERMINADO DE CARGAR LAS ARMAS DEL " + 
							avion.getNombre() + " EN EL TIEMPO: " + 
							((System.currentTimeMillis()/1000) - (timeStamp / 1000)) + " segundos");

	}

	private void esperaTiempo(int segs) {
		try {
			Thread.sleep(segs * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}