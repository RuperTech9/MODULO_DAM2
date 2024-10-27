package Programacion.T02_Multihilo.Ejercicios;

public class E01_TicTac {
    public static void main(String[] args) {

        for (Integer i = 0; i < 20; i++) {
            E01_Tic t1 = new E01_Tic();
            E01_Tac t2 = new E01_Tac();
            // Iniciar el hilo E01_Tic
            t1.start();
            try {
                // Esperar a que el hilo E01_Tic termine antes de iniciar el hilo E01_Tac
                // Se puede hacer con el sleep también
                t1.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Iniciar el hilo E01_Tac
            t2.start();
            try {
                // Esperar a que el hilo E01_Tac termine antes de continuar con la siguiente iteración
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
