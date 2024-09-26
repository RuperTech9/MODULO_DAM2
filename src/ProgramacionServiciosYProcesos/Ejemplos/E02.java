package ProgramacionServiciosYProcesos.Ejemplos;

import java.io.IOException;
import java.io.InputStream;

public class E02 {
    public static void main(String[] args) throws IOException {

        try {
            Process pb = new ProcessBuilder("CMD", "/C", "DIR").start();
            InputStream is = pb.getInputStream();

            int c;
            while ((c = is.read()) != -1){
                System.out.print((char)c);
            }
            is.close();
        } catch (Exception e){
            e.printStackTrace();

        }
    }

}

