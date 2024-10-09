package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioCanciones;

import java.io.*;
        import java.util.Scanner;

public class AñadirCancionFicheroAleatorio {
    public static void main(String[] args) {
        try {
            RandomAccessFile ficheroAleatorio = new RandomAccessFile("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioCanciones/canciones_aleatorio.dat", "rw");

            // Calcular el último ID en el fichero aleatorio
            long numRegistros = ficheroAleatorio.length() / 129; // 129 bytes por canción
            int nuevoId = (int) numRegistros + 1; // El nuevo ID será el siguiente consecutivo

            // Leer los datos de la nueva canción
            Scanner sc = new Scanner(System.in);
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

            // Posicionar al final del fichero para añadir la nueva canción
            ficheroAleatorio.seek(ficheroAleatorio.length());

            // Escribir la nueva canción en el fichero aleatorio
            ficheroAleatorio.writeInt(nuevoId); // ID (4 bytes)
            ficheroAleatorio.writeInt(anio); // Año (4 bytes)

            // Título (ajustado a 20 caracteres)
            StringBuffer tituloBuffer = new StringBuffer(titulo);
            tituloBuffer.setLength(20);
            ficheroAleatorio.writeChars(tituloBuffer.toString());

            // Artista (ajustado a 20 caracteres)
            StringBuffer artistaBuffer = new StringBuffer(artista);
            artistaBuffer.setLength(20);
            ficheroAleatorio.writeChars(artistaBuffer.toString());

            // Duración (ajustado a 20 caracteres)
            StringBuffer duracionBuffer = new StringBuffer(duracion);
            duracionBuffer.setLength(20);
            ficheroAleatorio.writeChars(duracionBuffer.toString());

            // Canción española (booleano)
            ficheroAleatorio.writeBoolean(esEspanola);

            ficheroAleatorio.close();
            System.out.println("Nueva canción añadida correctamente con ID: " + nuevoId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}