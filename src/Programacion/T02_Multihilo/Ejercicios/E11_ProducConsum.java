package Programacion.T02_Multihilo.Ejercicios;

// Clase principal para ejecutar la simulación
public class E11_ProducConsum {
    public static void main(String[] args) {
        // Crear la cola compartida entre productor y consumidores
        E11_Cola cola = new E11_Cola();

        // Crear el productor y dos consumidores
        E11_Productor p = new E11_Productor(cola, 1);
        E11_Consumidor c1 = new E11_Consumidor(cola, 1);
        E11_Consumidor c2 = new E11_Consumidor(cola, 2);

        // Iniciar el productor y los consumidores
        p.start();
        c1.start();
        c2.start();
    }
}
