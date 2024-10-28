package Programacion.T01_Procesos.Defensa;

import java.io.*;

public class EscribirEntradaProceso {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("CMD");
        Process p = pb.start();

        // Enviar comandos a la entrada del proceso
        OutputStream os = p.getOutputStream();
        os.write("echo Este es un mensaje enviado al proceso\n".getBytes());
        os.write("dir\n".getBytes());
        os.flush();
        os.close();

        // Leer la salida del proceso
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea;
        while ((linea = reader.readLine()) != null) {
            System.out.println(linea);
        }

    }
}
