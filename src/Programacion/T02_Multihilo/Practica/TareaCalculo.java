package Programacion.T02_Multihilo.Practica;

import java.util.Random;

public class TareaCalculo extends Thread{
    int sumaHilos =0;
    Random random = new Random();

    public TareaCalculo(String nombre){
        super(nombre);
    }

    public void run() {
        while(true){
            int numero = random.nextInt(901) + 100;
            sumaHilos = sumaHilos + numero;
            System.out.println(getName() + " - Suma hilo: " + sumaHilos);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

