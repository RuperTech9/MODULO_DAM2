package ProgramacionServiciosYProcesos.Ejemplos;

import java.io.IOException;
import java.io.InputStream;

public class E02_ComandoCMD {
    public static void main(String[] args) throws IOException {

        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIR");
        Process p = pb.start();
        try {
            InputStream is = p.getInputStream(); // Devuelve la salida estándar del proceso, es decir, lo que el proceso escribe normalmente en la consola.

            // mostramos en pantalla caracter a caracter
            int c;
            while ((c = is.read()) != -1)
                System.out.print((char)c);// Convierte el valor entero es necesario porque is.read() devuelve valores enteros que representan caracteres en su codificación ASCII.
            is.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        // COMPROBACION DE ERROR - 0 bien - 1 mal
        int exitVal;
		try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

