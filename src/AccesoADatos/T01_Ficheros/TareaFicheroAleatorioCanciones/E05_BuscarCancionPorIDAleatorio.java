package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioCanciones;

import java.io.*;
import java.util.Scanner;

public class E05_BuscarCancionPorIDAleatorio {
    public static void main(String[] args) {
        try {
            RandomAccessFile ficheroAleatorio = new RandomAccessFile("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioCanciones/cancionesAleatorio.dat", "r");

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el ID de la canción a buscar: ");
            int idBuscado = sc.nextInt();

            // Calcular la posición del registro (cada canción ocupa 129 bytes)
            long posicion = (idBuscado - 1) * 129;
            if (posicion >= ficheroAleatorio.length()) {
                System.out.println("La canción con ID " + idBuscado + " no existe.");
            } else {
                ficheroAleatorio.seek(posicion);

                // Leer la canción
                int id = ficheroAleatorio.readInt();
                int anio = ficheroAleatorio.readInt();

                // Leer el título (20 caracteres)
                char[] titulo = new char[20];
                for (int i = 0; i < titulo.length; i++) {
                    titulo[i] = ficheroAleatorio.readChar();
                }

                // Leer el artista (20 caracteres)
                char[] artista = new char[20];
                for (int i = 0; i < artista.length; i++) {
                    artista[i] = ficheroAleatorio.readChar();
                }

                // Leer la duración (20 caracteres)
                char[] duracion = new char[20];
                for (int i = 0; i < duracion.length; i++) {
                    duracion[i] = ficheroAleatorio.readChar();
                }

                // Leer el booleano de si es canción española
                boolean esEspanola = ficheroAleatorio.readBoolean();

                // Mostrar los datos
                System.out.println("ID: " + id);
                System.out.println("Año: " + anio);
                System.out.println("Título: " + new String(titulo).trim());
                System.out.println("Artista: " + new String(artista).trim());
                System.out.println("Duración: " + new String(duracion).trim());
                System.out.println("Canción española: " + (esEspanola ? "Sí" : "No"));
            }

            ficheroAleatorio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}