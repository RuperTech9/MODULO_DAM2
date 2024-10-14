package Programacion.T01_Procesos.Ejemplos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParallelProcessExample {
    public static void main(String[] args) {
        List<String> commands = List.of("cmd /C dir", "cmd /C dir", "cmd /C dir");

        List<Process> processes = new ArrayList<>();

        try {
            // Iniciar procesos en paralelo
            for (String command : commands) {
                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
                Process process = processBuilder.start();
                processes.add(process);
            }

            // Esperar a que todos los procesos terminen
            for (Process process : processes) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                process.waitFor();
            }

            System.out.println("Todos los procesos han terminado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}