package AccesoADatos.T01_Ficheros;

import java.io.*;


public class E15_LeerFichBytes_DatosPrimitivos {

	public static void main(String[] args) throws IOException {
		File f2 = new File("./src//AccesoADatos//T01_Ficheros/primitivos.dat");//crea un objeto File que representa el archivo
		f2.createNewFile();// crea un nuevo archivo físico en el disco. Si el archivo ya existe, el metodo no hace nada y devuelve false; si no existe, lo crea y devuelve true.
		
		FileOutputStream fileOut = new FileOutputStream(f2);// Clase que permite escribir bytes en un archivo (f2), Si ya existe, se sobrescribirá el contenido del archivo (si quieres añadir datos, puedes usar new FileOutputStream(f2, true)).
		DataOutputStream dataOut = new DataOutputStream(fileOut);// se utiliza para escribir datos primitivos (int, char, boolean, etc.) en un flujo de salida de forma binaria.
																 // asociado a fileout, por lo que cualquier dato que se escriba usando dataOut se guarda en el archivo primitivos.dat.
		int v = 1;
		char c = 5;
		Boolean b = true;
		String n = "hola";
		byte by = 0;

		// Escritura de datos primitivos en el archivo en el mismo orden
		dataOut.writeByte(by);
		dataOut.writeChar(c);
		dataOut.writeBoolean(b);
		dataOut.writeInt(v);
		dataOut.writeUTF(n);

		dataOut.close();

		FileInputStream fileIn = new FileInputStream(f2);
		DataInputStream dataIn = new DataInputStream(fileIn);
		
		//para funcionar se tiene que leer en el mismo orden en 
		//el que se escribieron los datos
		try {
			while(dataIn.available() > 0) { // Si available() devuelve 0, significa que se ha alcanzado el final del archivo. Se usa en la condición del while para asegurar que solo se lee mientras hay datos disponibles.
				by=dataIn.readByte();
				System.out.println("Esto es un Byte: "+by);
				c=dataIn.readChar();
				System.out.println("Esto es un Caracter: "+c);
				b=dataIn.readBoolean();
				System.out.println("Esto es un Boolean: "+b);
				v=dataIn.readInt();
				System.out.println("Esto es un entero: "+v);
				n=dataIn.readUTF();
				System.out.println("Esto es un String: "+n);
			}

		}catch(EOFException eo) {
			System.out.println("Error");
		}

		dataIn.close();
	}
}
