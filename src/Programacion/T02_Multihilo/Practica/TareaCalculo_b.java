package Programacion.T02_Multihilo.Practica;

import java.util.Random;

public class TareaCalculo_b implements Runnable{
    int sumaHilos =0;
    Random random = new Random();
    String nombre;

    public TareaCalculo_b(String nombre){
        this.nombre = nombre;
    }

    public void run() {
        while(true){
            int numero = random.nextInt(901) + 100;
            sumaHilos = sumaHilos + numero;
            System.out.println(nombre + " - Suma hilo: " + sumaHilos);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

