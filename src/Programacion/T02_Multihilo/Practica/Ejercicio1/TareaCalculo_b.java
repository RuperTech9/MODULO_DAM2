package Programacion.T02_Multihilo.Practica.Ejercicio1;

public class TareaCalculo_b implements Runnable {
    int sumaHilos =0;
    int n;
    String nombre;

    public TareaCalculo_b(String nombre){
        this.nombre = nombre;
        System.out.println("CREANDO HILO:" + nombre);
    }

    public void run() {
        while(true){
            n = (int)(Math.random() * 901) + 100;
            sumaHilos = sumaHilos + n;
            System.out.println(nombre + " - Suma: " + sumaHilos);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Hilo " + nombre + " interrumpido.");
                break;
            }
        }
    }
}
