package ProgramacionServiciosYProcesos.Ejemplos;

/*
Ejemplo 4: Ejecutar el proceso DATE con el valor de la fecha actual.
El metodo write() envía los datos al stream, el metodo getBytes() codifica la cadena.
 */

import java.io.*;

public class E04_EjecutarComandoDATEYEnviarEntrada {
	public static void main(String[] args) throws IOException {
		Process p = new ProcessBuilder("CMD", "/C", "DATE").start();

		// escritura -- envia entrada a DATE
		OutputStream os = p.getOutputStream();
		os.write("01-12-17".getBytes());
		os.flush(); // vacia el buffer de salida

		// lectura -- obtiene la salida de DATE
		InputStream is = p.getInputStream();		
		 int c;
		 while ((c = is.read()) != -1)
			System.out.print((char) c);
		 is.close();
		 
		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}//E04_EjecutarComandoDATEYEnviarEntrada
