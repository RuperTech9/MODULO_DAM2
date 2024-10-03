package ProgramacionServiciosYProcesos.Examen.Ejer1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class LanzaInfoExt {
public static void main(String[] args) throws IOException {
	File archivo = new File("info.txt");
	File directorio = new File(".\\bin");
	ProcessBuilder pb= new ProcessBuilder("java","InfoExtensiones");
	pb.directory(directorio);
	pb.redirectOutput(archivo);
	Process p =pb.start();
	 
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
}
}
