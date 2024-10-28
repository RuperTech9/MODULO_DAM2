package Programacion.T01_Procesos.Practica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ejemplo extends Thread {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> comandos = List.of("CMD /C dir", "CMD /C echo Hola", "CMD /C time /t");
        List<Process> procesos = new ArrayList<>();

        for (String comando : comandos) {
            ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
            procesos.add(pb.start());
        }

        for (Process proceso : procesos) {
            proceso.waitFor();
            System.out.println("Fin con codigo: " + proceso.exitValue());
        }

    }

}
