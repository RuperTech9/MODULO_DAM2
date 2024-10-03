package ProgramacionServiciosYProcesos.Ejemplos;

/*
Con el metodo getOutputStream() podemos enviar datos a la entrada estándar del programa EjemploLectura.java.
Por ejemplo, si queremos enviar la cadena “Hola Manuel” cambiaríamos varias cosas en el Ejemplo5.java
 */

import java.io.*;

public class Ejemplo5 {

	public static void main(String[] args) throws IOException {

		File directorio = new File("./src/ProgramacionServiciosYProcesos/Ejemplos");
		ProcessBuilder pb = new ProcessBuilder("java",
				"-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
				"ProgramacionServiciosYProcesos.Ejemplos.EjemploLectura");

		pb.directory(directorio);

		// se ejecuta el proceso
		Process p = pb.start();

		// escritura -- envia entrada 
		OutputStream os = p.getOutputStream();
		os.write("Hola Manuel\n".getBytes());
		os.flush(); // vac�a el buffer de salida

		// lectura -- obtiene la salida
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

		try {
			InputStream er = p.getErrorStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			while ((liner = br.readLine()) != null)
				System.out.println("ERROR >" + liner);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}// Ejemplo5
