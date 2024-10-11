package AccesoADatos.T01_Ficheros;

import java.io.*;

public class E07_EscribirFichTexto {
    public static void main(String[] args) throws IOException {
        File fichero = new File ("./src//AccesoADatos//T01_Ficheros//pruebaFichTexto.txt"); //declarar fichero
        FileWriter fw = new FileWriter(fichero);//crear flujo de salida

        try{
            String cadena = "Esto es una prueba con FileWriter";
            char[] cad = cadena.toCharArray();//convierto la cadena en array de caracteres para extraerlos 1 a 1

            for (int i = 0; i < cad.length; i++) {
                fw.write(cad[i]);//se va escribiendo un carácter
            }
            fw.append('*');//se añade al final un *
        }finally {
            if (fw != null) {
                fw.close(); // cierro el fichero fuera del bucle
            }
        }
    }
}
