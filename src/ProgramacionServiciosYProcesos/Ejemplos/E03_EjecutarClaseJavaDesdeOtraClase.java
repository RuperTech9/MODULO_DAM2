package ProgramacionServiciosYProcesos.Ejemplos;

/*
El siguiente ejemplo es un programa Java que ejecuta el programa Java anterior.
 */

import java.io.*;

public class E03_EjecutarClaseJavaDesdeOtraClase {
  public static void main(String[] args) throws IOException {

	//creamos objeto File al directorio donde esta E02_EjecutarComandoDIRYLeerSalida
	File directorio = new File("./src/ProgramacionServiciosYProcesos//Ejemplos");

	//El proceso a ejecutar es E02_EjecutarComandoDIRYLeerSalida
	ProcessBuilder pb = new ProcessBuilder("java", "E02_EjecutarComandoDIRYLeerSalida");

    //se establece el directorio donde se encuentra el ejecutable
    pb.directory(directorio);
        
	System.out.printf("Directorio de trabajo: %s%n",pb.directory());

    //se ejecuta el proceso
	Process p = pb.start();

   //obtener la salida devuelta por el proceso
	try {
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1)
			System.out.print((char) c);
		is.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
  }
}// E03_EjecutarClaseJavaDesdeOtraClase
