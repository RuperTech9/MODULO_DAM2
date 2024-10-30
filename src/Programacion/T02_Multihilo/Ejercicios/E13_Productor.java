package Programacion.T02_Multihilo.Ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class E13_Productor extends Thread {
    private E13_Cola cola;
    private String archivo;

    public E13_Productor(E13_Cola cola, String archivo) {
        this.cola = cola;
        this.archivo = archivo;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int c;
            while ((c = br.read()) != -1) {
                cola.put((char) c); // Coloca cada carácter en la cola
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cola.setProductorFinalizado(); // Indica que el productor ha terminado
        System.out.println("Fin del productor.");
    }
}