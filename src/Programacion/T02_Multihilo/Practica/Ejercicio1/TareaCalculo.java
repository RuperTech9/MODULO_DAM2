package Programacion.T02_Multihilo.Practica.Ejercicio1;

public class TareaCalculo extends Thread{
    int sumaHilos =0;
    int n;

    public TareaCalculo(String nombre){
        super(nombre);
        System.out.println("CREANDO HILO:" + getName());
    }

    public void run() {
        while(true){
            n = (int)(Math.random() * 901) + 100;
            sumaHilos = sumaHilos + n;
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

