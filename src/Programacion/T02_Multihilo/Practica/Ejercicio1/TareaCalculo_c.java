package Programacion.T02_Multihilo.Practica.Ejercicio1;

/**
 * La clase TareaCalculo_c implementa Runnable y añade una variable de control booleana que permite
 * detener el hilo cuando la suma acumulada alcanza 1000000.
 *
 * @author Ruper
 * @version 1.0
 */
public class TareaCalculo_c implements Runnable{
    int sumaHilos =0;
    int n;
    String nombre;
    boolean ejecutando = true;

    /**
     * Constructor que recibe un nombre para el hilo.
     *
     * @param nombre Nombre del hilo.
     */
    public TareaCalculo_c(String nombre){
        this.nombre = nombre;
        System.out.println("CREANDO HILO:" + nombre);
    }

    /**
     * Metodo run() que realiza el cálculo acumulativo hasta que la suma alcanza 1,000,000.
     * Genera un número aleatorio, lo suma al acumulador y lo imprime en la consola. Detiene
     * el bucle si la suma acumulada es igual o superior a 1,000,000.
     */
    @Override
    public void run() {
        while(ejecutando){
            n = (int)(Math.random()*901) + 100;
            sumaHilos = sumaHilos + n;
            System.out.println(nombre + " - Suma hilo: " + sumaHilos);

            if (sumaHilos >= 1000000) {
                System.out.println(nombre + " - Limite alcanzado. Deteniendo");
                ejecutando = false;
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Hilo " + nombre + " interrumpido.");
                break;
            }
        }
    }
}

