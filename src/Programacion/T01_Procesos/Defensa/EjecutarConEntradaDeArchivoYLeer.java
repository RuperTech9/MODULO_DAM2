package Programacion.T01_Procesos.Defensa;

import java.io.*;

public class EjecutarConEntradaDeArchivoYLeer {
    public static void main(String[] args) throws IOException {
        File archivo = new File("comandos.txt");
        ProcessBuilder pb = new ProcessBuilder("CMD");
        pb.redirectInput(archivo);
        Process p = pb.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
    }
}
