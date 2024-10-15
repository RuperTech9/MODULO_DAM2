package AccesoADatos.T01_Ficheros;

import java.io.*;

public class E02_InfoFichero {
    public static void main(String[] args) {
        System.out.println("INFORMACIÓN SOBRE EL FICHERO");
        File f = new File("./src//AccesoADatos//T01_Ficheros//InfoFich.txt");
        if(f.exists()){
            System.out.println("Nombre del fichero: " +f.getName());
            System.out.println("Ruta: " +f.getPath());
            System.out.println("Ruta Absoluta : " +f.getAbsolutePath());
            System.out.println("Se puede leer : " +f.canRead());
            System.out.println("Se puede escribir : " +f.canWrite());
            System.out.println("Tamaño : " +f.length());
            System.out.println("Es un Directorio: " +f.isDirectory());
            System.out.println("Es un fichero:" +f.isFile());
            System.out.println("Nombre del directorio padre:" +f.getParent());
        }
    }
}
