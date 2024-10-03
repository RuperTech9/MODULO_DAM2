package ProgramacionServiciosYProcesos.Ejemplos;

import java.io.File;
import java.io.IOException; 

public class Ejemplo8 { 
  public static void main(String args[]) throws IOException {
    ProcessBuilder pb = new ProcessBuilder("CMD");
    
    File fBat = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/fichero.bat");
    File fOut = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/salida.txt");
    File fErr = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/error.txt");
 
    pb.redirectInput(fBat);
    pb.redirectOutput(fOut);
    pb.redirectError(fErr); 
    pb.start(); 

  }
}// Ejemplo8
