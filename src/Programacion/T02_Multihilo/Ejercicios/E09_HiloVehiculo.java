package Programacion.T02_Multihilo.Ejercicios;

// Clase E09_HiloVehiculo que simula el proceso de cruzar el puente
public class E09_HiloVehiculo extends Thread {
    private final E09_Vehiculo vehiculo;
    private final E09_Puente puente;

    public E09_HiloVehiculo(E09_Vehiculo vehiculo, E09_Puente puente) {
        this.vehiculo = vehiculo;
        this.puente = puente;
    }

    @Override
    public void run() {
        try {
            puente.cruzarPuente(vehiculo); // Intento de cruce
            Thread.sleep(vehiculo.getTiempoCruce()); // Simular el tiempo de cruce
            puente.salirDelPuente(vehiculo); // Salida del puente
        } catch (InterruptedException e) {
            System.out.println("Vehículo " + vehiculo.getId() + " fue interrumpido mientras cruzaba el puente.");
        }
    }
}

/*
Explicación del Código
Clase Vehiculo:
Representa un vehículo que desea cruzar el puente.
Cada vehículo tiene un id y un tiempoCruce aleatorio entre 1 y 5 segundos.

Clase Puente:
Controla el acceso de vehículos con un Semaphore, que limita el número de vehículos que pueden cruzar simultáneamente.
Métodos cruzarPuente y salirDelPuente utilizan acquire() y release() del Semaphore, respectivamente, para gestionar el acceso.

Clase HiloVehiculo:
Extiende Thread y simula el proceso completo de cruce.
En el metodo run(), el vehículo intenta cruzar el puente, espera el tiempoCruce en el puente, y luego sale del puente.

Clase principal SimulacionPuente:
Configura la simulación con un puente de capacidad limitada y un número de vehículos determinado.
Inicia múltiples hilos HiloVehiculo, cada uno representando un vehículo que intenta cruzar el puente.

Consideraciones
Sincronización: El Semaphore garantiza que el número de vehículos en el puente nunca exceda su capacidad.
Evitar Deadlocks: Al utilizar release() después de cada acquire(), aseguramos que el sistema no entre en deadlock.
Estadísticas: Podrías extender este código para calcular estadísticas como el tiempo promedio de espera de los vehículos, aunque esto no está implementado en el ejemplo.
 */