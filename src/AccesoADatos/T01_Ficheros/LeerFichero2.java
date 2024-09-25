package AccesoADatos.T01_Ficheros;
import java.io.*;

public class LeerFichero2 {
	public static void main(String[] args) throws IOException {
		//Declarar el fichero
		File fichero =
				new File("./src//AccesoADatos//T01_Ficheros//pruebaFichTexto1.txt");
		
		//Crear el flujo de entrada hacie el fichero
		FileReader fic = new FileReader(fichero);
		int i;
		//Para ir leyendo de 20 en 20 caracteres
				char b[] = new char[20];
				while((i=fic.read(b)) != -1 ) System.out.println(b);
		fic.close(); //Cerramos el fichero
		
	}//Fin main

}//Fin class