package ProgramacionServiciosYProcesos.Examen.Ejer4;

import java.io.Serializable;
import java.util.ArrayList;

public class Carta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> lista;
	private boolean bueno;
	public Carta(ArrayList<String> lista, boolean bueno) {
		super();
		this.lista = lista;
		this.bueno = bueno;
	}
	public ArrayList<String> getLista() {
		return lista;
	}
	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}
	public boolean isBueno() {
		return bueno;
	}
	public void setBueno(boolean bueno) {
		this.bueno = bueno;
	}
	
	
}
