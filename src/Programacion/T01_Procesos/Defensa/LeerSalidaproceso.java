package Programacion.T01_Procesos.Defensa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeerSalidaproceso {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "ping www.google.com");
        Process p = pb.start();

        BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea;
        while ((linea = bf.readLine()) != null) {
            System.out.println(linea);
        }

    }
}
