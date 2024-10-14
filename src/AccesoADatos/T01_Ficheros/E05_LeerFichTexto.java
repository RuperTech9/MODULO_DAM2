package AccesoADatos.T01_Ficheros;

import java.io.*;


public class E05_LeerFichTexto {
    public static void main(String[] args) throws IOException {
        File fichero = new File("./src//AccesoADatos//T01_Ficheros//pruebaFichTexto1.txt");
        FileReader fr = new FileReader(fichero);
        int i;
        while ((i = fr.read()) != -1) //se va leyendo un carácter
            System.out.print((char) i);
        fr.close();
    }
}
