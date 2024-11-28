package Programacion.T02_Multihilo.defensa;

public class ControlHilos extends Thread {
    ConjuntoCompartido conjunto;
    private boolean ejecutar = true;
    private int contador = 0;
	
	//Constructor
    public ControlHilos(ConjuntoCompartido con) {
        this.conjunto = con;
    }

    @Override
    public void run() {
        while (ejecutar) {
            conjunto.agregarElemento(contador);
            contador++;
            System.out.println(Thread.currentThread().getName() + " Contador: " + contador);
            if (contador == 4) {
                ejecutar = false;
            }
        }
    }
}
