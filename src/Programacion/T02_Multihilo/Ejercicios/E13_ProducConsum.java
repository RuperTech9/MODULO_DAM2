package Programacion.T02_Multihilo.Ejercicios;

public class E13_ProducConsum {
    public static void main(String[] args) {
        String nombreArchivo = "./src/Programacion/T02_Multihilo/Ejercicios/E13_texto.txt"; // Nombre del archivo que será leído por el productor
        E13_Cola cola = new E13_Cola(); // Crear la cola compartida

        // Crear productor y consumidores
        E13_Productor productor = new E13_Productor(cola, nombreArchivo);
        E13_Consumidor consumidor1 = new E13_Consumidor(cola, 1);
        E13_Consumidor consumidor2 = new E13_Consumidor(cola, 2);

        // Iniciar productor y consumidores
        productor.start();
        consumidor1.start();
        consumidor2.start();

        // Esperar a que el productor termine y verificar el estado de los consumidores
        try {
            productor.join();
            System.out.println("Estado de consumidor 1 después de que finaliza el productor: " + consumidor1.getState());
            System.out.println("Estado de consumidor 2 después de que finaliza el productor: " + consumidor2.getState());

            consumidor1.join();
            consumidor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
Explicación del Código
Clase Cola:

Atributo productorFinalizado: Indica si el productor ha terminado de colocar datos. Es un AtomicBoolean para gestionar de manera segura el estado final del productor.
Método get(): Si disponible es false y el productor ha finalizado, retorna null, lo que indica al consumidor que debe finalizar.
Método put(): Coloca un carácter en la cola y notifica a los consumidores.
Método setProductorFinalizado(): Señala que el productor ha terminado de producir datos y notifica a los consumidores en espera.
Clase Productor:

Lee caracteres de un archivo de texto línea por línea, colocando cada carácter en la cola.
Al finalizar, llama a setProductorFinalizado() para notificar que ha terminado su tarea y permite que los consumidores finalicen correctamente.
Clase Consumidor:

Consume caracteres de la cola y los imprime en pantalla.
Cuando recibe null de cola.get(), significa que el productor ha terminado y no hay más datos, por lo que termina su ejecución.
Clase principal ProducConsumArchivo:

Crea un Productor que lee el archivo texto.txt y dos Consumidor que leen de la misma Cola.
Inicia todos los hilos y utiliza join() para esperar que el productor finalice antes de verificar el estado de los consumidores con getState().
Al finalizar, espera a que todos los consumidores terminen.
Explicación del Comportamiento
Sincronización: El uso de wait() y notifyAll() en la clase Cola asegura que los consumidores y el productor no intenten acceder a la cola cuando está en un estado inconsistente.
Finalización de Consumidores: Gracias a setProductorFinalizado() y la comprobación en get(), todos los consumidores finalizan correctamente al no recibir más datos.
Uso de getState(): Se utiliza para observar el estado de los consumidores después de que el productor finaliza. Verás que los consumidores terminan en estado TERMINATED si han consumido todos los datos.
Resultados Esperados
La salida muestra cada carácter leído del archivo, seguido de mensajes de finalización del productor y los consumidores. Los consumidores deberían finalizar correctamente después de consumir todos los datos sin quedar en estado WAITING.
 */