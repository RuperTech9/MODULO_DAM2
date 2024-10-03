package ProgramacionServiciosYProcesos.Ejemplos;

/*
El siguiente ejemplo ejecuta el comando DIR usando el método getInputStream() de Process para leer el stream de salida del proceso.
InputStream is = p.getInputStream();
Para leer la salida se usa el metodo read() de InputStream que devolverá la salida carácter a carácter.
Process
 */

import java.io.*;

public class Ejemplo2 {
	public static void main(String[] args) throws IOException {

		Process p = new ProcessBuilder("CMD", "/C", "DIR").start();
		try {

			InputStream is = p.getInputStream();

			// mostramos en pantalla caracter a caracter
			 int c;
			 while ((c = is.read()) != -1)
				System.out.print((char) c);
			 is.close();

		
		} catch (Exception e) {
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
}// Ejemplo2
