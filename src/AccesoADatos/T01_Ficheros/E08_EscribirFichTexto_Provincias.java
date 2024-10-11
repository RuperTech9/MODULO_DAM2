package AccesoADatos.T01_Ficheros;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class E08_EscribirFichTexto_Provincias {
    public static void main(String[] args) throws IOException {
        File fichero = new File ("./src//AccesoADatos//T01_Ficheros//pruebaFichTexto.txt"); //declarar fichero
        FileWriter fw = new FileWriter(fichero);//crear flujo de salida

        try{
            String prov[] = {"Albacete", "Avila", "Badajoz", "Cáceres", "Huelva", "Jaén", "Madrid", "Segovia", "Soria", "Toledo", "Valladolid", "Zamora"};
            for (int i = 0; i < prov.length; i++) {
                fw.write(prov[i]+" ");//se va escribiendo un carácter
            }
        }finally {
            if (fw != null) {
                fw.close(); // cierro el fichero fuera del bucle
            }
        }
    }
}
