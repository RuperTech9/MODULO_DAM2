
public class Boxes {

	private String nombre;

	public Box() {
	}

	public Box(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void cambiaRuedas(EquipoF1 equipoF1, long timeStamp) {
		
		System.out.println("En el " + this.nombre + 
				" SE EMPIEZAN A CAMBIAR LOS NEUMATICOS DEL " + equipoF1.getNombre() + 
				" EN EL INSTANTE: " + ((System.currentTimeMillis()/1000) - (timeStamp / 1000))	+
				"seg");

		for (int i = 0; i < equipoF1.getcoches().length; i++) {
			this.esperaTiempo(equipoF1.getcoches()[i]);
			System.out.println("Cambiando los neumaticos del coche " + (i + 1) + 
					". Tiempo parcial: " + ((System.currentTimeMillis()/1000) - (timeStamp / 1000)) + 
					" segundos");
		}

		System.out.println("El " + this.nombre + " HA TERMINADO DE CAMBIAR LOS NEUMATICOS " + 
							equipoF1.getNombre() + " EN UN TIEMPO DE: " + 
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