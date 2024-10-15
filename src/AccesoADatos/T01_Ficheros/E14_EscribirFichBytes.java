package AccesoADatos.T01_Ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class E14_EscribirFichBytes {
    public static void main(String[] args) throws IOException {
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/FichBytes.dat");// Declara el fichero

        FileOutputStream fileout = new FileOutputStream(fichero);// Crea flujo de salida hacia el fichero
        for (int i = 1; i < 100; i++) {
            fileout.write(i);// Escribe datos en el flujo de salida
        }
        fileout.close();// Cerrar stream de salida

        FileInputStream filein = new FileInputStream(fichero);// Crea flujo de entrada desde el fichero
        // Visualizar los datos del fichero
        int i;
        while ((i = filein.read()) != -1) { // Lee datos del flujo de entrada
            System.out.println(i);
        }
        filein.close();// Cerrar stream de entrada
    }
}
/*
Crea flujo de salida hacia el fichero en modo append (true) para añadir bytes al final.
los nuevos datos se añadirán al final del archivo en lugar de sobrescribir su contenido.

    FileOutputStream fileout = new FileOutputStream(fichero, true);
 */