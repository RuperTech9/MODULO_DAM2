package AccesoADatos.T01_Ficheros;

import java.io.*;

public class EscribirFichTexto1 {
    public static void main(String[] args) {
        // Definir el archivo
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/pruebaFichTexto1.txt");

        // Array de cadenas de texto a escribir en el archivo
        String prov[] = {"Albacete", "Avila", "Badajoz", "Cáceres", "Huelva", "Jaén", "Madrid", "Segovia", "Soria", "Toledo", "Valladolid", "Zamora"};

        // Escribir las cadenas en el archivo
        try (FileWriter fic = new FileWriter(fichero)) {
            for (int i = 0; i < prov.length; i++) {
                fic.write(prov[i]+" "); // Escribir cada cadena sin saltos de línea
                // Si quisieras añadir un espacio o algún separador, puedes usar fic.write(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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