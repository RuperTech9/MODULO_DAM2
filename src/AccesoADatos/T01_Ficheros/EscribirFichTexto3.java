package AccesoADatos.T01_Ficheros;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Realizar un programa que abra para añadir el fichero de ciudades creado anteriormente 
 * y añada al final una nueva ciudad.
 * A continuación Realizar un proceso que lea el fichero resultante, lo guarde en un arrayList
 * y lo vuelva a ordenar descendentemente, y lo escriba.
 */

public class EscribirFichTexto3 {

	public static void main(String[] args) throws IOException {
		//ABRIR FICHERO
		File fichero = new File("./src//AccesoADatos//T01_Ficheros/pruebaFichTexto1.txt");

        FileWriter fw = new FileWriter(fichero, true); // El 'true' habilita el modo "append"
        fw.append(" Valencia ");
        fw.close();

        ArrayList<String> provincias = new ArrayList<>(); //ArrayList para almacenar las ciudades del fichero
//--------------------------------------------------------------------------------------------------------------------------------------------
        FileReader fr = new FileReader(fichero);
        BufferedReader br = new BufferedReader(fr); // Leer el contenido del fichero usando BufferedReader

        String linea;
        while ((linea = br.readLine()) != null) {// Leer el fichero línea por línea
            String[] ciudades = linea.split(" ");// Dividimos la línea en usando el espacio como delimitador
            for (String ciudad : ciudades) {
                if (!ciudad.trim().isEmpty()) {//verifica si no está vacío después de eliminar espacios en blanco al inicio y al final con el método .trim().
                    provincias.add(ciudad); // Añadimos cada ciudad al ArrayList
                }
            }
        }
        br.close();
        fr.close();
//-------------------------------------------------------------------------------------------------------------------------------------------- 
        provincias.sort(null);
        //provincias.sort(Collections.reverseOrder()); // Ordenar el ArrayList en orden descendente
        
        ArrayList<String> listaInvertida = new ArrayList<>();
        for (int i = provincias.size() - 1; i >= 0; i--) { // Recorre desde el final hasta el principio
            listaInvertida.add(provincias.get(i)); // Añade cada elemento en orden inverso
        }

        System.out.println("Contenido del fichero ordenado descendentemente:");
        for (String provincia : provincias) {
            System.out.println(provincia);
        } 
    }
}
