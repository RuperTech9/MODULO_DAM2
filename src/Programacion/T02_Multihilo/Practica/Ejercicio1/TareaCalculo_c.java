package Programacion.T02_Multihilo.Practica.Ejercicio1;


public class TareaCalculo_c implements Runnable{
    int sumaHilos =0;
    int n;
    String nombre;
    boolean ejecutando = true;

    public TareaCalculo_c(String nombre){
        this.nombre = nombre;
        System.out.println("CREANDO HILO:" + nombre);
    }


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

