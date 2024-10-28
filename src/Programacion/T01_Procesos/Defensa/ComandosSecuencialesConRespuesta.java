package Programacion.T01_Procesos.Defensa;

import java.io.*;

public class ComandosSecuencialesConRespuesta {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("CMD");
        Process p = pb.start();

        OutputStream os = p.getOutputStream();
        os.write("echo Primer comando\n".getBytes());
        os.write("echo Segundo comando\n".getBytes());
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }


    }

}
