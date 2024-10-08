package Programacion.T01_Procesos.Ejemplos;

/*
REDIRECCIONANDO LA ENTRADA Y LA SALIDA
Ejemplo 9: se muestra en la consola la salida del comando DIR:
 */

import java.io.IOException;

public class E09_EjecutarComandoDIRYMostrarSalidaEnConsola {
	public static void main(String args[]) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIR");

		//la salida a consola
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);	    
		Process p = pb.start();		
	
	}
}// E09_EjecutarComandoDIRYMostrarSalidaEnConsola
