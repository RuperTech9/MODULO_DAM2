package Programacion.T02_Multihilo.Practica.Ejercicio1;

/**
 * La clase TareaCalculo_b implementa Runnable en lugar de extender Thread. Realiza la misma
 * funcionalidad de suma acumulada que TareaCalculo, pero utiliza un enfoque basado en la interfaz Runnable.
 *
 * @author Ruper
 * @version 1.0
 */
public class TareaCalculo_b implements Runnable {
    int sumaHilos =0;
    int n;
    String nombre;

    /**
     * Constructor que recibe un nombre para el hilo.
     *
     * @param nombre Nombre del hilo.
     */
    public TareaCalculo_b(String nombre){
        this.nombre = nombre;
        System.out.println("CREANDO HILO:" + nombre);
    }

    /**
     * Método run() que realiza el cálculo acumulativo en un bucle infinito.
     * Genera un número aleatorio, lo suma al acumulador y lo imprime en la consola.
     * Introduce una pausa de 10 segundos entre iteraciones y maneja interrupciones.
     */
    @Override
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
