package Programacion.T02_Multihilo.Practica.Ejercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneradorIDs_V1 implements Runnable {
    final String CARACTERES = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789";
    final int LONGITUD = 6;
    final static int CANTIDAD_TOTAL = 200;
    private static List<String> idList = Collections.synchronizedList(new ArrayList<>());
    private int cantidadHilo;

    public GeneradorIDs_V1(int cantidadHilo) {
        this.cantidadHilo = cantidadHilo;
    }

    @Override
    public void run() {
        while (idList.size() < CANTIDAD_TOTAL) {
            StringBuilder identificador = new StringBuilder();
            for (int i = 0; i < LONGITUD; i++) {
                identificador.append(CARACTERES.charAt((int) (Math.random() * CARACTERES.length())));
            }
            String nuevoID = identificador.toString();

            synchronized (idList) {
                if (!idList.contains(nuevoID) && idList.size() < CANTIDAD_TOTAL) {
                    idList.add(nuevoID);
                }
            }
        }
    }

    // Metodo para generar los IDs usando múltiples hilos
    public static ArrayList<String> generarID_V1() {
        List<Thread> threads = new ArrayList<>();
        int numHilos = 4;

        for (int i = 0; i < numHilos; i++) {
            Thread thread = new Thread(new GeneradorIDs_V1(CANTIDAD_TOTAL / numHilos));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>(idList);
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        int numHilos = 4;

        for (int i = 0; i < numHilos; i++) {
            Thread thread = new Thread(new GeneradorIDs_V1(CANTIDAD_TOTAL / numHilos));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Se generaron " + idList.size() + " identificadores únicos:");
        idList.forEach(System.out::println);
    }
}
