package Programacion.T01_Procesos.Ejemplos;

/*
Con el metodo getOutputStream() podemos enviar datos a la entrada estándar del programa EjemploLectura.java.
Por ejemplo, si queremos enviar la cadena “Hola Manuel” cambiaríamos varias cosas en el E05_EjecutarClaseConEntradaEstandar.java
 */

import java.io.*;

public class E05_EjecutarClaseConEntradaEstandar {

	public static void main(String[] args) throws IOException {

		File directorio = new File("./src/Programacion/T01_Procesos/Ejemplos");
		ProcessBuilder pb = new ProcessBuilder("java",
				"-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2",
				"Programacion.T01_Procesos.Ejemplos.EjemploLectura");

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
}// E05_EjecutarClaseConEntradaEstandar
