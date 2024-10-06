package AccesoADatos.T01_Ficheros.TareaFicheroObjetos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CrearFicheroCanciones {
    public static void main(String[] args) {
        try {
            // Crea el fichero binario de canciones
            FileOutputStream fileOut = new FileOutputStream("./src//AccesoADatos//T01_Ficheros/TareaFicheroObjetos/canciones.dat");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

            // Inicializamos el fichero con canciones vacías
            for (int i = 0; i < 5; i++) {
                Cancion cancion = new Cancion();
                objOut.writeObject(cancion); // Escribimos las canciones en el fichero
            }

            objOut.close();
            fileOut.close();
            System.out.println("Fichero de canciones creado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}