package Programacion.T02_Multihilo.Ejercicios;

import java.util.Random;

public class E14_Jugador extends Thread {
    private E14_Arbitro arbitro;
    private int id;

    public E14_Jugador(E14_Arbitro arbitro, int id) {
        this.arbitro = arbitro;
        this.id = id;
    }

    public void run() {
        Random rand = new Random();

        while (!arbitro.isAdivinado()) { // Intentar mientras no se haya adivinado
            int intento = rand.nextInt(10) + 1; // Genera un intento entre 1 y 10

            if (arbitro.comprobarNumero(intento, id)) {
                break; // Sale del bucle si adivina correctamente
            }

            try {
                Thread.sleep(500); // Pausa para evitar intentos demasiado rápidos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}