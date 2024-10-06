package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioCanciones;

import java.io.*;

public class EscribirFicheroAleatorioCanciones {
    public static void main(String[] args) {
        try {
            // Fichero de objetos canciones (origen) y fichero aleatorio de canciones (destino)
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("./src//AccesoADatos//T01_Ficheros/TareaFicheroObjetos/canciones.dat"));
            RandomAccessFile ficheroAleatorio = new RandomAccessFile("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioCanciones/canciones_aleatorio.dat", "rw");

            // Variables para leer las canciones
            Cancion2 cancion2;

            // Leer cada canción del fichero de objetos y escribirla en el fichero aleatorio
            while (true) {
                try {
                    cancion2 = (Cancion2) objIn.readObject();

                    // Escribir en el fichero aleatorio
                    ficheroAleatorio.writeInt(cancion2.getId()); // ID (4 bytes)
                    ficheroAleatorio.writeInt(cancion2.getAnio()); // Año (4 bytes)

                    // Escribir el título, ajustado a 20 caracteres
                    StringBuffer tituloBuffer = new StringBuffer(cancion2.getTitulo());
                    tituloBuffer.setLength(20); // Ajustar a 20 caracteres
                    ficheroAleatorio.writeChars(tituloBuffer.toString());

                    // Escribir el artista, ajustado a 20 caracteres
                    StringBuffer artistaBuffer = new StringBuffer(cancion2.getArtista());
                    artistaBuffer.setLength(20); // Ajustar a 20 caracteres
                    ficheroAleatorio.writeChars(artistaBuffer.toString());

                    // Escribir la duración, ajustado a 20 caracteres
                    StringBuffer duracionBuffer = new StringBuffer(cancion2.getDuracion());
                    duracionBuffer.setLength(20); // Ajustar a 20 caracteres
                    ficheroAleatorio.writeChars(duracionBuffer.toString());

                    // Escribir si la canción es española o no (boolean)
                    ficheroAleatorio.writeBoolean(cancion2.isEsEspanola());

                } catch (EOFException eof) {
                    break; // Fin del fichero
                }
            }

            objIn.close();
            ficheroAleatorio.close();
            System.out.println("Fichero aleatorio de canciones creado correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}