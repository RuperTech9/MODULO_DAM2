package Programacion.T02_Multihilo.Practica;

public class TareaCalculo_b implements Runnable {
    int sumaHilos = 0;
    int n;
    private String nombre;

    public TareaCalculo_b(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            n = (int) (Math.random() * 901) + 100;
            sumaHilos = sumaHilos + n;
            System.out.println(nombre + " - Suma hilo: " + sumaHilos);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
