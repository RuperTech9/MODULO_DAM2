package Programacion.T02_Multihilo.Ejercicios;

// Clase principal para simular el tráfico de vehículos en el puente
public class E09_SimulacionPuente {
    public static void main(String[] args) {
        int capacidadPuente = 3; // Capacidad máxima del puente
        int totalVehiculos = 10; // Total de vehículos para la simulación

        E09_Puente puente = new E09_Puente(capacidadPuente);

        // Crear y lanzar los hilos para los vehículos
        for (int i = 1; i <= totalVehiculos; i++) {
            E09_Vehiculo vehiculo = new E09_Vehiculo(i);
            E09_HiloVehiculo hiloVehiculo = new E09_HiloVehiculo(vehiculo, puente);
            hiloVehiculo.start();
        }
    }
}