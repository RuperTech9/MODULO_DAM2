package Programacion.T01_Procesos.Ejercicios;

/*
Escribe un programa Java que lea una cadena desde la entrada estándar y visualice en pantalla si la cadena es o no palíndromo o si la cadena está vacía (la longitud es cero).
Realiza un segundo programa Java que ejecute el anterior. Debe leer la cadena desde el teclado y mostrar la salida por pantalla.
Transforma este ejercicio para que la cadena se obtenga de un fichero de texto, y se envíe la salida de error a un fichero.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class E09a_VerificarPalindromo {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Introduce una cadena:");
            String cadena = br.readLine();

            // Verificamos si la cadena está vacía
            if (cadena.isEmpty()) {
                System.out.println("La cadena está vacía.");
            } else {
                // Limpiamos la cadena quitando espacios y pasando todo a minusculas
                String cadenaLimpia = cadena.replaceAll("\\s+", "").toLowerCase();

                // Verificamos si es palíndromo
                String cadenaReversa = new StringBuilder(cadenaLimpia).reverse().toString();
                if (cadenaLimpia.equals(cadenaReversa)) {
                    System.out.println("La cadena es un palíndromo.");
                } else {
                    System.out.println("La cadena no es un palíndromo.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
