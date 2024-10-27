package Programacion.T02_Multihilo.Ejercicios;

// Clase principal para ejecutar la simulación del modelo productor-consumidor con un productor y un consumidor
public class E12_ProducConsum {
    public static void main(String[] args) {
        E12_Cola cola = new E12_Cola(); // Crear la cola compartida entre productor y consumidores

        // Crear el productor y el consumidor
        E12_Productor p = new E12_Productor(cola, 1);
        E12_Consumidor c1 = new E12_Consumidor(cola, 1);

        // Iniciar el productor y el consumidor
        p.start();
        c1.start();
    }
}