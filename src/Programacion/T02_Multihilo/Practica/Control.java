package Programacion.T02_Multihilo.Practica;

public class Control extends Thread {
    boolean ejecutar = true;
    int contador = 0;
    public void run() {
        while (ejecutar){
            contador ++;
            System.out.println(Thread.currentThread().getName()+" Contador: "+contador);
            if (contador == 4){
                ejecutar = false;
            }
        }

    }
}
