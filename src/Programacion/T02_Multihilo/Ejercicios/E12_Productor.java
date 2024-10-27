package Programacion.T02_Multihilo.Ejercicios;

// Clase E12_Productor que genera las cadenas PING y PONG de forma alternada y las coloca en la cola
class E12_Productor extends Thread {
    private E12_Cola cola;
    private int n;

    public E12_Productor(E12_Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        String[] mensajes = {"PING", "PONG"};
        for (int i = 0; i < 10; i++) { // Generar 10 pares de PING y PONG
            String mensaje = mensajes[i % 2]; // Alternar entre PING y PONG
            cola.put(mensaje);
            System.out.println("E12_Productor: " + n + ", produce: " + mensaje);
            try {
                Thread.sleep(100); // Simulación de pausa en la producción
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fin productor...");
    }
}