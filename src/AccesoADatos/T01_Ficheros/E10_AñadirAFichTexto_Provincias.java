package AccesoADatos.T01_Ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Realizar un programa que abra para añadir el fichero de ciudades creado anteriormente 
 * y añada al final una nueva ciudad.
 * A continuación Realizar un proceso que lea el fichero resultante, lo guarde en un array
 * y lo vuelva a ordenar descendentemente, y lo escriba.
 */

public class E10_AñadirAFichTexto_Provincias {
	public static void main(String[] args) throws IOException {
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/FichProvincias.txt");
        //File fichero = new File("C:\\Users\\aludam2\\eclipse-workspace\\Acceso_Datos\\src\\Tema1\\EscribirFicheroTexto.txt");

        // Paso 1: Añadir "Valencia" al final del fichero sin borrar el contenido previo
        FileWriter fic = new FileWriter(fichero, true); // El 'true' habilita el modo "append"
        fic.append("Valencia ");
        fic.close(); // Cerrar el fichero

        System.out.println("Se ha añadido 'Valencia' correctamente al fichero.");

        BufferedReader reader = new BufferedReader(new FileReader(fichero));//Leer el contenido del fichero y almacenarlo en una lista
        ArrayList<String> provincias = new ArrayList<>();
        
        String line;
        while ((line = reader.readLine()) != null) {
            // Split de las provincias por espacio
            String[] provArray = line.split(" ");
            for (String provincia : provArray) {
                if (!provincia.trim().isEmpty()) {
                    provincias.add(provincia.trim());
                }
            }
        }
        reader.close(); // Cerrar el lector del fichero
        
        // Paso 3: Ordenar las provincias en orden inverso alfabético (de Z a A)
        Collections.sort(provincias, Collections.reverseOrder());
        
        // Paso 4: Escribir el contenido ordenado de vuelta al fichero
        FileWriter writer = new FileWriter(fichero);
        for (String provincia : provincias) {
            writer.write(provincia + " ");
        }
        writer.close(); // Cerrar el escritor
        
        System.out.println("Provincias ordenadas en orden inverso alfabético correctamente.");

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