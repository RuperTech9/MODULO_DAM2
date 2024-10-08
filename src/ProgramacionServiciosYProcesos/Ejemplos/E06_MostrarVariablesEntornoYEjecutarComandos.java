package ProgramacionServiciosYProcesos.Ejemplos;

/*
Ejemplo 6: Usa 2 métodos de la clase ProcessBuilder:
- environment(): Devuelve las variables de entorno del proceso
- command() Sin parámetros devuelve los argumentos del proceso definido en el objeto ProcessBuilder,
- y command() con parámetros define un nuevo proceso y sus argumentos.
 */

import java.io.*;
import java.util.*;

public class E06_MostrarVariablesEntornoYEjecutarComandos {
	public static void main(String args[]) {
		
		//File directorio = new File(".\\bin");
		
		ProcessBuilder test = new ProcessBuilder();
		Map entorno = test.environment();
		System.out.println("Variables de entorno:");
		System.out.println(entorno);

		test = new ProcessBuilder("java", "LeerNombre", "Maria Jes�s");		
		
		// devuelve el nombre del proceso y sus argumentos
		List l = test.command();
		Iterator iter = l.iterator();
		System.out.println("\nArgumentos del comando:");
		while (iter.hasNext())
			System.out.println(iter.next());

		// ejecutamos el comando DIR
		test = test.command("CMD", "/C", "DIR");
		try {
			Process p = test.start();
			InputStream is = p.getInputStream(); //Recojo los caracteres que devuelve
		    
			System.out.println();
			// mostramos en pantalla caracter a caracter
			 int c;
			 while ((c = is.read()) != -1)
				System.out.print((char) c);
			 is.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}// E06_MostrarVariablesEntornoYEjecutarComandos
