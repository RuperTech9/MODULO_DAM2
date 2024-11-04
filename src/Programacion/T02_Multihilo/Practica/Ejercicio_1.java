package Programacion.T02_Multihilo.Practica;

public class Ejercicio_1 {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            TareaCalculo_b tarea =  new TareaCalculo_b("Hilo" + i);
            Thread hilo = new Thread(tarea);
            hilo.start();
        }
    }
}
