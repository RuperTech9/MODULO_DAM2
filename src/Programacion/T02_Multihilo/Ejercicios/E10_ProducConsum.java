package Programacion.T02_Multihilo.Ejercicios;

// Clase principal para ejecutar la simulación
public class E10_ProducConsum {
    public static void main(String[] args) {
        E10_Cola cola = new E10_Cola();
        E10_Productor p = new E10_Productor(cola, 1);
        E10_Consumidor c = new E10_Consumidor(cola, 1);

        p.start();
        c.start();
    }
}
