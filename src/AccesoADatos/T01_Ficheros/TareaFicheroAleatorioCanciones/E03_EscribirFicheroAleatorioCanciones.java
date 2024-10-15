package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioCanciones;

import java.io.*;


public class E03_EscribirFicheroAleatorioCanciones {
    public static void main(String[] args) {
        // Fichero de objetos serializados (entrada)
        File ficheroObjetos = new File("./src/AccesoADatos/T01_Ficheros/TareaFicheroAleatorioCanciones/canciones.dat");

        // Fichero binario aleatorio (salida)
        File ficheroAleatorio = new File("./src/AccesoADatos/T01_Ficheros/TareaFicheroAleatorioCanciones/CancionesAleatorio.dat");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroObjetos));
             RandomAccessFile raf = new RandomAccessFile(ficheroAleatorio, "rw")) {

            // Leer objetos del fichero de objetos
            while (true) {
                try {
                    // Leer una canción del fichero de objetos
                    E01_Cancion canciones = (E01_Cancion) ois.readObject();

                    // Escribir los atributos de la canción en el fichero aleatorio
                    raf.writeInt(canciones.getId()); // 4 bytes (ID)
                    raf.writeInt(canciones.getAno()); // 4 bytes (Año)

                    // Escribir el título ajustado a 20 caracteres (40 bytes)
                    StringBuffer bufferTitulo = new StringBuffer(canciones.getTitulo());
                    bufferTitulo.setLength(20); // Aseguramos que sea de 20 caracteres
                    raf.writeChars(bufferTitulo.toString());

                    // Escribir el artista ajustado a 20 caracteres (40 bytes)
                    StringBuffer bufferArtista = new StringBuffer(canciones.getArtista());
                    bufferArtista.setLength(20); // Aseguramos que sea de 20 caracteres
                    raf.writeChars(bufferArtista.toString());

                    // Escribir la duración ajustada a 20 caracteres (40 bytes)
                    StringBuffer bufferDuracion = new StringBuffer(canciones.getDuracion());
                    bufferDuracion.setLength(20); // Aseguramos que sea de 20 caracteres
                    raf.writeChars(bufferDuracion.toString());

                    // Escribir si la canción es española (1 byte)
                    raf.writeBoolean(canciones.isCancionEspanola());

                } catch (EOFException e) {
                    // Fin del fichero de objetos
                    System.out.println("Se han escrito todas las canciones.");
                    break;
                } catch (ClassNotFoundException e) {
                    System.out.println("Error: No se pudo encontrar la clase E01_Cancion.");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}