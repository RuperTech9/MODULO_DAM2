package Programacion.T01_Procesos.Defensa;

import java.io.IOException;

public class CodigoSalida {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("CMD","/C","echo Hola");
        Process p = pb.start();

        int exitCode = p.waitFor();
        System.out.println("Exit code: " + exitCode);
    }
}
