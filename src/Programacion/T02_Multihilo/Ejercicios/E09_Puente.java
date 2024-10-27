package Programacion.T02_Multihilo.Ejercicios;

import java.util.concurrent.Semaphore;

// Clase E09_Puente con una capacidad máxima controlada por Semaphore
class E09_Puente {
    private final Semaphore semaforo;

    public E09_Puente(int capacidad) {
        this.semaforo = new Semaphore(capacidad, true); // Semaphore con capacidad limitada y fairness habilitado
    }

    // Método para que un vehículo intente cruzar el puente
    public void cruzarPuente(E09_Vehiculo vehiculo) throws InterruptedException {
        semaforo.acquire(); // Adquirir permiso para cruzar
        System.out.println("Vehículo " + vehiculo.getId() + " está cruzando el puente. Tiempo de cruce: " + vehiculo.getTiempoCruce() + " ms.");
    }

    // Método para que un vehículo deje el puente
    public void salirDelPuente(E09_Vehiculo vehiculo) {
        System.out.println("Vehículo " + vehiculo.getId() + " ha salido del puente.");
        semaforo.release(); // Liberar permiso al salir
    }
}
