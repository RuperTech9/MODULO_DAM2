package ProgramacionServiciosYProcesos.Ejemplos;

import java.io.IOException;

public class E01_AbrirNotepadConProcessBuilder {
	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("NOTEPAD");
		Process p = pb.start();
	}
}//E01_AbrirNotepadConProcessBuilder