package AccesoADatos.T01_Ficheros.TareaFicheroObjetos;

import java.io.*;
import java.util.Scanner;

public class AñadirCancion {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroObjetos/canciones.dat");

            // Abrir el fichero en modo append (añadir)
            ObjectOutputStream objOut;
            if (fichero.exists()) {
                objOut = new MiObjectOutputStream(new FileOutputStream(fichero, true)); // No escribimos cabecera
            } else {
                objOut = new ObjectOutputStream(new FileOutputStream(fichero)); // Si no existe, escribimos cabecera
            }

            // Leer los datos de la nueva canción
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            System.out.print("Introduce el título: ");
            String titulo = sc.nextLine();
            System.out.print("Introduce el artista: ");
            String artista = sc.nextLine();
            System.out.print("Introduce el año: ");
            int anio = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            System.out.print("Introduce la duración: ");
            String duracion = sc.nextLine();
            System.out.print("¿Es una canción española? (true/false): ");
            boolean esEspanola = sc.nextBoolean();

            // Crear el objeto Cancion2
            Cancion nuevaCancion = new Cancion(id, anio, titulo, artista, duracion, esEspanola);

            // Escribir la nueva canción en el fichero
            objOut.writeObject(nuevaCancion);
            objOut.close();
            System.out.println("Canción añadida correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}