package AccesoADatos.T01_Ficheros.TareaFicheroObjetos;

import java.io.*;
import java.util.Scanner;

public class BuscarCancionPorArtista {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroObjetos/canciones.dat");
            if (!fichero.exists()) {
                System.out.println("El fichero no existe.");
                return;
            }

            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(fichero));

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el nombre del artista: ");
            String artistaBuscado = sc.nextLine();

            boolean encontrado = false;
            int contador = 0;

            // Leer el fichero secuencialmente para encontrar todas las canciones del artista
            while (true) {
                try {
                    Cancion cancion = (Cancion) objIn.readObject();
                    if (cancion.getArtista().equalsIgnoreCase(artistaBuscado)) {
                        System.out.println("Canción encontrada: " + cancion);
                        encontrado = true;
                        contador++;
                    }
                } catch (EOFException eof) {
                    break; // Fin del fichero
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró ninguna canción del artista " + artistaBuscado);
            } else {
                System.out.println("Se encontraron " + contador + " canción(es) del artista " + artistaBuscado);
            }

            objIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}