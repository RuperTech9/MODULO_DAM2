package Programacion.T02_Multihilo.Ejercicios;

// Clase E12_Consumidor que consume mensajes de la cola y los visualiza
class E12_Consumidor extends Thread {
    private E12_Cola cola;
    private int n;

    public E12_Consumidor(E12_Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 10; i++) { // Consumir 10 mensajes
            String mensaje = cola.get();
            System.out.println("E12_Consumidor: " + n + ", consume: " + mensaje);
            try {
                Thread.sleep(100); // Simulación de pausa en la consumición
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}