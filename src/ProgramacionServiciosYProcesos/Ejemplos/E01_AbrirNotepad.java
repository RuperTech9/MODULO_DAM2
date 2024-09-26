package ProgramacionServiciosYProcesos.Ejemplos;

import java.io.IOException;

public class E01_AbrirNotepad {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        Process p = pb.start();
    }
}
