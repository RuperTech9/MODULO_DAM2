package Programacion.T02_Multihilo.Practica.Ejercicio1;

/**
 * La clase TareaCalculo extiende Thread y se encarga de realizar una suma acumulativa de números aleatorios
 * en un hilo independiente. En cada iteración, genera un número aleatorio entre 100 y 1000, lo suma a una
 * variable acumuladora y muestra el resultado en la consola cada 10 segundos.
 *
 * @author Ruper
 * @version 1.0
 */
public class TareaCalculo extends Thread {
    private int sumaHilos = 0; // Acumulador de la suma de números generados
    private int n; // Número aleatorio generado en cada iteración

    /**
     * Constructor que recibe un nombre para el hilo y lo asigna.
     *
     * @param nombre Nombre del hilo.
     */
    public TareaCalculo(String nombre) {
        super(nombre);
        System.out.println("CREANDO HILO: " + getName());
    }

    /**
     * Metodo run() que inicia la ejecución del hilo. En cada iteración, genera un número aleatorio
     * entre 100 y 1000, lo suma al acumulador y lo imprime en la consola. Introduce una pausa de 10
     * segundos entre iteraciones. Si el hilo es interrumpido, sale del bucle y finaliza.
     */
    @Override
    public void run() {
        while (true) {
            n = (int) (Math.random() * 901) + 100; // Número aleatorio entre 100 y 1000
            sumaHilos += n;
            System.out.println(getName() + " - Suma: " + sumaHilos);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Hilo " + getName() + " interrumpido.");
                break;
            }
        }
    }
}
