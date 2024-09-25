package AccesoADatos.T01_Ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscribirFichBytes {
    public static void main(String[] args) throws IOException {
        // Declara el fichero
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/prueba3.dat");
        
        // Crea flujo de salida hacia el fichero
        FileOutputStream fileout = new FileOutputStream(fichero);
        
        // Escribe datos en el flujo de salida
        for (int i = 1; i < 100; i++) {
            fileout.write(i);
        }
        
        // Cerrar stream de salida
        fileout.close();
        
        // Crea flujo de entrada desde el fichero
        FileInputStream filein = new FileInputStream(fichero);
        
        // Visualizar los datos del fichero
        int i;
        while ((i = filein.read()) != -1) { // Lee datos del flujo de entrada
            System.out.println(i);
        }
        
        // Cerrar stream de entrada
        filein.close();
    }
}