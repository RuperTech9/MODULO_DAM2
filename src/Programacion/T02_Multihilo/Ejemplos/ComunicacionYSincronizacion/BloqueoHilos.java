package Programacion.T02_Multihilo.Ejemplos.ComunicacionYSincronizacion;

class ObjetoCompartido {
	public void pintaCadena (String s) {
		System.out.print(s);
	}
  }// ObjetoCompartido

class HiloCadena extends Thread {
	private ObjetoCompartido objeto;
    String cad;
	public HiloCadena (ObjetoCompartido c, String s) {		
		this.objeto = c;
		this.cad=s;
	}
	public void run() {
	 for (int j = 0; j < 10; j++)			
		 objeto.pintaCadena(cad);
	}//run
}//HiloCadena

public class BloqueoHilos {
	public static void main(String[] args) {
		ObjetoCompartido com = new ObjetoCompartido();
		HiloCadena  a = new HiloCadena (com, " A ");
		HiloCadena  b = new HiloCadena (com, " B ");
		a.start();
		b.start();
	}
}//BloqueoHilos

