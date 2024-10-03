package ProgramacionServiciosYProcesos.Examen.Ejer1;

import java.io.IOException;
import java.io.InputStream;

public class InfoExtensiones {
	public static void main(String[] args) throws IOException {
		
		ProcessBuilder pb= new ProcessBuilder("CMD","/C", "FTYPE");
	
		Process p = pb.start();
		
		try {
			InputStream is = p.getInputStream();
			int c;
			while ((c = is.read()) != -1) {
				System.out.print((char) c);
			}
			
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Obtener la salida que yo le ponga
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
