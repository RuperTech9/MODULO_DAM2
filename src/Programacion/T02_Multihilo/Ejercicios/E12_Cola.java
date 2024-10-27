package Programacion.T02_Multihilo.Ejercicios;

// Clase E12_Cola con métodos sincronizados para controlar el acceso de productor y consumidores
public class E12_Cola {
    private String mensaje;
    private boolean disponible = false;

    // Método para obtener un mensaje de la cola
    public synchronized String get() {
        while (!disponible) {
            try {
                wait(); // Espera hasta que haya un mensaje disponible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false;
        notifyAll(); // Notifica al productor que la cola está libre para poner otro mensaje
        return mensaje;
    }

    // Método para colocar un mensaje en la cola
    public synchronized void put(String mensaje) {
        while (disponible) {
            try {
                wait(); // Espera hasta que el mensaje sea consumido
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.mensaje = mensaje;
        disponible = true;
        notifyAll(); // Notifica a los consumidores que hay un nuevo mensaje disponible
    }
}