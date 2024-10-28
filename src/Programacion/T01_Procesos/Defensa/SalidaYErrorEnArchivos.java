package Programacion.T01_Procesos.Defensa;

import java.io.File;
import java.io.IOException;

public class SalidaYErrorEnArchivos {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("CMD","/C","DIR");
        pb.redirectOutput(new File("salida.txt"));
        pb.redirectError(new File("error.txt"));
        pb.start();
    }
}
