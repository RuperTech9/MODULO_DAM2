package Programacion.T01_Procesos.Defensa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListarProcesos {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("tasklist");
        Process p = pb.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
    }
}
