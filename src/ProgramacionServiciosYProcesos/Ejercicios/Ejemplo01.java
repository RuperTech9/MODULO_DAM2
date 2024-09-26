package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.IOException;

public class Ejemplo01 {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIR");
        Process p = pb.start();
    }
}
