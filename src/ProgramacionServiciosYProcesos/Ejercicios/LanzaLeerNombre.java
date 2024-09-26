package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LanzaLeerNombre {
    public static void main(String[] args) throws IOException {
        //creamos objeto File al directorio donde esta LeerNombre
        File directorio = new File("C:/Users/Ruper/IdeaProjects/MODULO_DAM2/src/ProgramacionServiciosYProcesos/Ejercicios");

        //El proceso a ejecutar es LeerNombre
        ProcessBuilder pb = new ProcessBuilder("java", "-cp","C:\\Users\\Ruper\\IdeaProjects\\MODULO_DAM2\\out\\production\\MODULO_DAM2","ProgramacionServiciosYProcesos.Ejercicios.LeerNombre", "Agustin");

        //se establece el directorio donde se encuentra el ejecutable
        pb.directory(directorio);

        System.out.printf("Directorio de trabajo: %s%n",pb.directory());

        //se ejecuta el proceso
        Process p = pb.start();

        //obtener la salida devuelta por el proceso
        try {
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1)
                System.out.print((char) c);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // COMPROBACION DE ERROR - 0 bien - 1 mal
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
