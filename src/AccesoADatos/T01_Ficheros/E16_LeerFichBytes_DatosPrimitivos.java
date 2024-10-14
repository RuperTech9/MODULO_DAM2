package AccesoADatos.T01_Ficheros;
// realizar un programa que añada al fichero de los datos primitivos un long, y al final visualizar el fichero
import java.io.*;
public class E16_LeerFichBytes_DatosPrimitivos {
    public static void main(String[] args) throws IOException {
        // Creación del archivo donde se almacenarán los datos primitivos
        File f2 = new File("./src//AccesoADatos//T01_Ficheros/primitivos.dat");
        f2.createNewFile();


        // Escritura adicional en el archivo en modo append (agregar al final)
        FileOutputStream fileOutAppend = new FileOutputStream(f2, true);
        DataOutputStream dataOutAppend = new DataOutputStream(fileOutAppend);

        long l = 123456789L; // Nuevo valor de tipo long
        dataOutAppend.writeLong(l);

        dataOutAppend.close();

        System.out.println("Valor long añadido al archivo.");

        // Lectura de datos del archivo
        FileInputStream fileIn = new FileInputStream(f2);
        DataInputStream dataIn = new DataInputStream(fileIn);

        // Lectura de datos en el mismo orden en el que se escribieron
        try {
            byte by = dataIn.readByte();
            System.out.println("Esto es un Byte: " + by);

            char c = dataIn.readChar();
            System.out.println("Esto es un Caracter: " + c);

            boolean b = dataIn.readBoolean();
            System.out.println("Esto es un Boolean: " + b);

            int v = dataIn.readInt();
            System.out.println("Esto es un entero: " + v);

            String n = dataIn.readUTF();
            System.out.println("Esto es un String: " + n);

            // Leer el valor long adicional
            long lRead = dataIn.readLong();
            System.out.println("Esto es un Long: " + lRead);

        } catch (IOException e) {
            // Fin del archivo alcanzado, se termina la lectura.
        } finally {
            dataIn.close(); // Cierroflujo de entrada
        }
    }
}
