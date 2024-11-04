package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GeneradorCSV_V1 implements Runnable {
    private ArrayList<String> identificadores;
    private String nombreArchivo;

    public GeneradorCSV_V1(ArrayList<String> identificadores, String nombreArchivo) {
        this.identificadores = identificadores;
        this.nombreArchivo = nombreArchivo;
    }

    public void run() {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < 50000; i++) {
                // Seleccionar un identificador aleatorio utilizando Math.random()
                String id = identificadores.get((int) (Math.random() * identificadores.size()));

                // Generar un número entre 0 y 20000
                int numero = (int) (Math.random() * 20001);

                // Escribir el par en el archivo CSV
                writer.write(id + "," + numero + "\n");
            }
            System.out.println("Archivo " + nombreArchivo + " generado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Generar identificadores
        ArrayList<String> identificadores = GeneradorIDs_V1.generarID_V1();

        // Crear un hilo para generar el archivo CSV
        String nombreArchivo = "./src/Programacion/T02_Multihilo/Practica/Ejercicio2/pares_identificadores.csv";
        Thread hiloCSV = new Thread(new GeneradorCSV_V1(identificadores, nombreArchivo));
        hiloCSV.start();

        try {
            hiloCSV.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Archivo CSV generado en " + nombreArchivo);
    }
}