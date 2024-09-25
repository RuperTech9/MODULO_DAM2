package AccesoADatos.T01_Ficheros;

import java.io.*;

public class CrearDirectorioYFichero {
    public static void main(String[] args) {
        File d = new File("./src//AccesoADatos//T01_Ficheros//DIRECTORIO");
        File f1 = new File(d,"FICHERO1.TXT");
        File f2 = new File(d,"FICHERO2.TXT");

        d.mkdir();

        try{
            if(f1.createNewFile())
                System.out.println("FICHERO1 creado correctamente");
            else
                System.out.println("No se ha podido creal el FICHERO1");

            if(f2.createNewFile())
                System.out.println("FICHERO2 creado correctamente");
            else
                System.out.println("No se ha podido crear el FICHERO2");

        }catch (IOException ioe){ ioe.printStackTrace(); }

        // Renombrar Fichero1
        File f1Nuevo = new File(d, "Fichero1Nuevo");
        if (f1.renameTo(f1Nuevo)) {
            System.out.println("Fichero1 renombrado a 'Fichero1Nuevo'.");
        } else {
            System.out.println("No se pudo renombrar el Fichero1.");
        }

        try {
            File f3 = new File("./src//AccesoADatos//T01_Ficheros//DIRECTORIO/FICHERO3.TXT");
            f3.createNewFile();

        }catch (IOException ioe){ ioe.printStackTrace(); }

        try {
            if(f2.delete())
                System.out.println("FICHERO2 borrado correctamente");
            else
                System.out.println("No se ha podido borrar el fichero");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
