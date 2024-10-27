package Programacion.T02_Multihilo.Ejercicios;

import java.util.Random;

// Clase E09_Vehiculo con un id y tiempo de cruce estimado
public class E09_Vehiculo {
    private final int id;
    private final int tiempoCruce;

    public E09_Vehiculo(int id) {
        this.id = id;
        this.tiempoCruce = new Random().nextInt(4000) + 1000; // Tiempo entre 1 y 5 segundos
    }

    public int getId() {
        return id;
    }

    public int getTiempoCruce() {
        return tiempoCruce;
    }
}
