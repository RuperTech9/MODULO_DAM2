package AccesoADatos.T01_Ficheros;

import java.io.*;


public class E19_EscribirFichAleatorio {
    public static void main(String[] args) throws IOException {

        // Fichero de objetos canciones (origen) y fichero aleatorio de canciones (destino)
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/PruebaAleatorioEmpl.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw"); //Se abre en modo "rw" para lectura y escritura

        String apellido[] = {"FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "CASILLA", "REY"};
        int dept[] = {10, 20, 10, 10, 30, 30, 20};
        Double salario[] = {1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87, 2000.0};

        StringBuffer buffer = null;//Buffer para almacenar el apellido
        int n = apellido.length;//Numero de elementos del array

        for (int i = 0; i < n; i++) {
            file.writeInt(i + 1);//Para identificar empleado
            buffer = new StringBuffer(apellido[i]);
            buffer.setLength(10);//10 caracteres por apellido
            file.writeChars(buffer.toString());//Insertar apellido
            file.writeInt(dept[i]);
            file.writeDouble(salario[i]);
        }
        file.close();
    }
}