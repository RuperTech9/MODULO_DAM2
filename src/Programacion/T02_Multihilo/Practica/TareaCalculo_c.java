package Programacion.T02_Multihilo.Practica;


public class TareaCalculo_c implements Runnable{
    int sumaHilos =0;
    int n;
    int sumaFinal = 2000;
    String nombre;


    public void run() {
        while(sumaHilos < sumaFinal){
            n = (int)(Math.random()*901) + 100;
            sumaHilos = sumaHilos + n;
            System.out.println(nombre + " - Suma hilo: " + sumaHilos);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " - Hilo Terminado");
    }
}

