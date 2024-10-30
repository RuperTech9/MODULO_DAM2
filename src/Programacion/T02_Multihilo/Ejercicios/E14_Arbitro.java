package Programacion.T02_Multihilo.Ejercicios;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class E14_Arbitro {
    private int numeroAdivinar;
    private AtomicBoolean adivinado = new AtomicBoolean(false);

    public E14_Arbitro() {
        generarNumero(); // Genera el número al crear el árbitro
    }

    // Método para generar el número aleatorio a adivinar
    public void generarNumero() {
        Random rand = new Random();
        numeroAdivinar = rand.nextInt(10) + 1; // Número entre 1 y 10
        System.out.println("El árbitro ha generado el número a adivinar.");
    }

    // Método sincronizado para comprobar el intento de un jugador
    public synchronized boolean comprobarNumero(int numero, int jugadorId) {
        if (adivinado.get()) {
            return true; // Si ya se adivinó el número, no hacer nada
        }

        System.out.println("Jugador " + jugadorId + " ha intentado con el número: " + numero);

        if (numero == numeroAdivinar) {
            adivinado.set(true); // Marca el número como adivinado
            System.out.println("¡Jugador " + jugadorId + " ha adivinado el número! Era " + numeroAdivinar);
            return true;
        } else {
            System.out.println("Jugador " + jugadorId + " ha fallado.");
            return false;
        }
    }

    // Método para verificar si el número ha sido adivinado
    public boolean isAdivinado() {
        return adivinado.get();
    }
}
