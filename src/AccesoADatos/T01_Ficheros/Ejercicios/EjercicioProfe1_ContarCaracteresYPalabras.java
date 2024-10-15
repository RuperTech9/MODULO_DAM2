package AccesoADatos.T01_Ficheros.Ejercicios;

import java.io.*;

/* Realizar un programa Java que lea un fichero de texto y comprueba que no sobrepase 1000 caracteres, ni las 150 palabras, si no los sobrepasa el articulo es valido
 * si los sobrepasa saldra un mensaje de que no es válido
 * 
 * Hay que detectar cadenas y caractéres
*/
public class EjercicioProfe1_ContarCaracteresYPalabras {

	public static void main(String[] args) {
		// Ruta del fichero de texto que se va a leer
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/Ejercicios/PruebaProfe1_ContarCaracteresYPalabras.txt");
        
        try {
            // Comprobar si el fichero existe
            if (!fichero.exists()) {
                System.out.println("El fichero no existe.");
                return;
            }

            // Leer el fichero
            FileReader fr = new FileReader(fichero); // Para leer el archivo.
            BufferedReader br = new BufferedReader(fr);// Para facilitar la lectura del archivo línea por línea.

            StringBuilder contenido = new StringBuilder(); // Para almacenar el contenido completo del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(" "); // Añadir cada línea con un espacio al final
            }
            br.close();
            fr.close();

            // Contar caracteres
            String texto = contenido.toString().trim(); // Convertir el contenido a String y eliminar espacios al inicio y final
            int numeroCaracteres = texto.length(); // Contar el número de caracteres

            // Contar palabras
            String[] palabras = texto.split("\\s+"); // Dividir el texto por espacios en blanco
            int numeroPalabras = palabras.length; // Contar el número de palabras

            // Comprobar si el artículo es válido
            if (numeroCaracteres <= 1000 && numeroPalabras <= 150) {
                System.out.println("El artículo es válido.");
                System.out.println("Número de caracteres: " + numeroCaracteres);
                System.out.println("Número de palabras: " + numeroPalabras);
            } else {
                System.out.println("El artículo no es válido.");
                System.out.println("Número de caracteres: " + numeroCaracteres + " (máximo 1000)");
                System.out.println("Número de palabras: " + numeroPalabras + " (máximo 150)");
            }

        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el fichero: " + e.getMessage());
        }
    }
}