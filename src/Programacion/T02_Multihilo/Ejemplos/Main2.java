package Programacion.T02_Multihilo.Ejemplos;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Buffer2 buffer = new Buffer2();
        ControlHilos2 hilo1 = new ControlHilos2(buffer);
        ControlHilos2 hilo2 = new ControlHilos2(buffer);

        System.out.println("Iniciando los hilos...");
        hilo1.start();
        hilo2.start();

        try {
            // Espera a que los hilos terminen
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hilos terminados.");

        // Imprime el contenido actual del buffer
        buffer.imprimir();

        // Permitir al usuario elegir un número para eliminar
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la posición que desea eliminar del buffer: ");
        int numero = sc.nextInt();

        try {
            buffer.eliminar(numero);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Imprime el contenido del buffer después de eliminar el número
        buffer.imprimir();

        sc.close();
    }
}
