package Programacion.T02_Multihilo.Ejercicios;

public class E01_TicTac {
    public static void main(String[] args) {

        for (Integer i = 0; i < 20; i++) {
            Tic t1 = new Tic();
            Tac t2 = new Tac();
            // Iniciar el hilo Tic
            t1.start();
            try {
                // Esperar a que el hilo Tic termine antes de iniciar el hilo Tac
                t1.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Iniciar el hilo Tac
            t2.start();
            try {
                // Esperar a que el hilo Tac termine antes de continuar con la siguiente iteración
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
