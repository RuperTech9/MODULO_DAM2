package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioCanciones;

import java.io.*;
import AccesoADatos.T01_Ficheros.TareaFicheroObjetos.Cancion;


public class EscribirFicheroAleatorioCanciones {
    public static void main(String[] args) {
        try {
            // Fichero de objetos canciones (origen) y fichero aleatorio de canciones (destino)
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioCanciones/canciones.dat"));
            RandomAccessFile ficheroAleatorio = new RandomAccessFile("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioCanciones/canciones_aleatorio.dat", "rw");

            // Variables para leer las canciones
            Cancion cancion;

            // Leer cada canción del fichero de objetos y escribirla en el fichero aleatorio
            while (true) {
                try {
                    cancion = (Cancion) objIn.readObject();

                    // Escribir en el fichero aleatorio
                    ficheroAleatorio.writeInt(cancion.getId()); // ID (4 bytes)
                    ficheroAleatorio.writeInt(cancion.getAnio()); // Año (4 bytes)

                    // Escribir el título, ajustado a 20 caracteres
                    StringBuffer tituloBuffer = new StringBuffer(cancion.getTitulo());
                    tituloBuffer.setLength(20); // Ajustar a 20 caracteres
                    ficheroAleatorio.writeChars(tituloBuffer.toString());

                    // Escribir el artista, ajustado a 20 caracteres
                    StringBuffer artistaBuffer = new StringBuffer(cancion.getArtista());
                    artistaBuffer.setLength(20); // Ajustar a 20 caracteres
                    ficheroAleatorio.writeChars(artistaBuffer.toString());

                    // Escribir la duración, ajustado a 20 caracteres
                    StringBuffer duracionBuffer = new StringBuffer(cancion.getDuracion());
                    duracionBuffer.setLength(20); // Ajustar a 20 caracteres
                    ficheroAleatorio.writeChars(duracionBuffer.toString());

                    // Escribir si la canción es española o no (boolean)
                    ficheroAleatorio.writeBoolean(cancion.isEsEspanola());

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