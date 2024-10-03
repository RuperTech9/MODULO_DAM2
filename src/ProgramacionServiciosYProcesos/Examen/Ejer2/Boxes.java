package ProgramacionServiciosYProcesos.Examen.Ejer2;

public class Boxes extends Thread {

	private int id;
	private String nombre;
	private EquipoF1 equipoF1;
	private long timeStamp;
	private boolean pasa = true;

	public Boxes() {
	}

	public Boxes(String nombre) {
		this.nombre = nombre;
	}
	
	public Boxes(String nombre, EquipoF1 equipoF1) {
		super();
		this.nombre = nombre;
		this.equipoF1 = equipoF1;
	}
	

	public Boxes(String nombre, EquipoF1 equipoF1, long timeStamp) {
		super();
		this.nombre = nombre;
		this.equipoF1 = equipoF1;
		this.timeStamp = timeStamp;
	}
	

	public Boxes(int id, String nombre, EquipoF1 equipoF1, long timeStamp) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.equipoF1 = equipoF1;
		this.timeStamp = timeStamp;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EquipoF1 getEquipoF1() {
		return equipoF1;
	}

	public void setEquipoF1(EquipoF1 equipoF1) {
		this.equipoF1 = equipoF1;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void  run() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeStamp = System.currentTimeMillis();
		inicia();
		cambia();
		termina();
		notifyAll();
	}
	
	private synchronized void inicia() {
	
		if (pasa == true) {
			pasa = false;
			System.out.println("(HILO "+id+") En el " + this.nombre + 
					" SE EMPIEZAN A CAMBIAR LOS NEUMATICOS DEL " + equipoF1.getNombre() + 
					" EN EL INSTANTE: " + ((System.currentTimeMillis()/1000) - (timeStamp / 1000))	+
					"seg");	
			
		}
		
	}
	private synchronized void cambia() {

		for (int i = 0; i < equipoF1.getcoches().length; i++) {
			this.esperaTiempo(equipoF1.getcoches()[i]);
			System.out.println("(HILO "+id+") Cambiando los neumaticos del coche " + (i + 1) + 
					". Tiempo parcial: " + ((System.currentTimeMillis()/1000) - (timeStamp / 1000)) + 
					" segundos");
		}
	}
	private synchronized void termina() {
		System.out.println("(HILO "+id+") El " + this.nombre + " HA TERMINADO DE CAMBIAR LOS NEUMATICOS " + 
							equipoF1.getNombre() + " EN UN TIEMPO DE: " + 
							((System.currentTimeMillis()/1000) - (timeStamp / 1000)) + " segundos");
		notify();
	}

	private void esperaTiempo(int segs) {
		try {
			Thread.sleep(segs * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}