package ProgramacionServiciosYProcesos.Ejemplos;

/*
Ejemplo 7: ejecuta el comando DIR y envía la salida al fichero salida.txt.
Si hay algún error lo envía al fichero error.txt
 */


import java.io.File;
import java.io.IOException;
 
public class Ejemplo7 { 
  public static void main(String args[]) throws IOException {
    ProcessBuilder pb = new ProcessBuilder("CMD","/C" ,"DIRR");
    
    File fOut = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/salida.txt");
    File fErr = new File("./src/ProgramacionServiciosYProcesos/Ejemplos/error.txt");
 
    pb.redirectOutput(fOut);
    pb.redirectError(fErr); 
    pb.start(); 
  }
}// Ejemplo7
