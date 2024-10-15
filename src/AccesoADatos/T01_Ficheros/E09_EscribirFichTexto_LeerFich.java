package AccesoADatos.T01_Ficheros;

import java.io.*;

public class E09_EscribirFichTexto_LeerFich {
    public static void main(String[] args) {
        // Definir el archivo
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/FichProvincias.txt");


        // Leer el contenido del archivo y mostrarlo en la consola
        try (FileReader fr = new FileReader(fichero)) {
            int i;
            while ((i = fr.read()) != -1) { // Se va leyendo un carácter
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}