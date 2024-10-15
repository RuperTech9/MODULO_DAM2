package AccesoADatos.T01_Ficheros;

import java.io.*;

/*
 * Vamos a añadir un array de alturas que sean números reales
 */
public class E16_LeerFichData_NombresEdadesAlturas {

	public static void main(String[] args) throws IOException {
		File fichero = new File("./src//AccesoADatos//T01_Ficheros/InfoUser.dat");
		FileInputStream filein = new FileInputStream(fichero);
		DataInputStream datain = new DataInputStream(filein);

		String n;
		int e;
		double a;

		try{
			while(true){
				n = datain.readUTF();
				e = datain.readInt();
				a = datain.readDouble();
				System.out.println("Nombre: " + n + ", edad: " + e + ", altura: " + a);
			}
		} catch (EOFException eo) {	}
		datain.close();

	}
}
