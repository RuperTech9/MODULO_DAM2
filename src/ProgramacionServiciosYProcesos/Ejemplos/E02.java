package ProgramacionServiciosYProcesos.Ejemplos;

import java.io.IOException;
import java.io.InputStream;

public class E02 {
    public static void main(String[] args) throws IOException {

        Process pb = new ProcessBuilder("CMD", "/C", "DIR").start();
        try {

            InputStream is = pb.getInputStream();

            // mostramos en pantalla caracter a caracter
            int c;
            while ((c = is.read()) != -1)
                System.out.print((char)c);
            is.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    // COMPROBACION DE ERROR - 0 bien - 1 mal
    int exitVal;
		try {
        exitVal = pb.waitFor();
        System.out.println("Valor de Salida: " + exitVal);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
}

