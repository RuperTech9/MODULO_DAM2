package Programacion.T02_Multihilo.Practica;

import java.util.Random;

public class TareaCalculo_c implements Runnable{
    int suma=0;
    Random random = new Random();
    int sumaFinal = 2000;
    String nombre;

    public TareaCalculo_c(String nombre){
        this.nombre = nombre;
    }

    public void run() {
        while(suma < sumaFinal){
            int numero = random.nextInt(901) + 100;
            suma = suma + numero;
            System.out.println(nombre + " - Suma hilo: " + suma);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " - Hilo Terminado");
    }
}

