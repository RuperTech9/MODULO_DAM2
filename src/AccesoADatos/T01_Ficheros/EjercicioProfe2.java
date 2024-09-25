package AccesoADatos.T01_Ficheros;

import java.io.*;

/*
 * Vamos a añadir un array de alturas que sean números reales
 */
public class EjercicioProfe2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File fichero = new File("C:/Users/Ruper/eclipse-workspace/ACCESO_DATOS/src/FICHEROS/pruebaData.txt");
		FileOutputStream fileout = new FileOutputStream(fichero);
		DataOutputStream dataOS = new DataOutputStream(fileout);
		
		String nombres[] = {"Ana","Luis","Alicia","Pedro","Manuel","Andrés"};	
		int edades[] = {14,15,13,14,16,15};
		double alturas[] = {105.4,105.4,105.4,105.4,105.4,105.4};
		
		for (int i=0;i<edades.length;i++) {
			dataOS.writeUTF(nombres[i]);
			dataOS.writeInt(edades[i]);
			dataOS.writeDouble(alturas[i]);
			System.out.println("Nombre: " + nombres[i] + " Edad: " + edades[i] + " Altura: " + alturas[i]);
		}
		dataOS.close();
	}
}
