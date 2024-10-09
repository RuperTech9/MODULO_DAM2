package ProgramacionServiciosYProcesos.Practica;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SincronizacionSubprocesos {

    public static void main(String[] args) {
        // Archivos a procesar
        String archivo1 = "./src/ProgramacionServiciosYProcesos/Practica/archivo1.txt";
        String archivo2 = "./src/ProgramacionServiciosYProcesos/Practica/archivo2.txt";

        SincronizacionSubprocesos sincronizacion = new SincronizacionSubprocesos();
        sincronizacion.procesarArchivos(archivo1, archivo2);
    }

    public void procesarArchivos(String archivo1, String archivo2) {
        try {
            // Iniciar los subprocesos para contar líneas y palabras
            ProcessBuilder pbLineas = new ProcessBuilder("powershell.exe", "-Command",
                    "Get-Content " + archivo1 + " | Measure-Object -Line");
            ProcessBuilder pbPalabras = new ProcessBuilder("powershell.exe", "-Command",
                    "Get-Content " + archivo2 + " | Measure-Object -Word");

            // Iniciar los procesos
            Process procesoLineas = pbLineas.start();
            Process procesoPalabras = pbPalabras.start();

            // Leer el resultado del conteo de líneas
            int numLineas = leerResultado(procesoLineas);
            // Leer el resultado del conteo de palabras
            int numPalabras = leerResultado(procesoPalabras);

            // Esperar a que ambos procesos terminen
            procesoLineas.waitFor();
            procesoPalabras.waitFor();

            // Mostrar el resultado combinado
            System.out.println("Número total de líneas: " + numLineas);
            System.out.println("Número total de palabras: " + numPalabras);
            System.out.println("Total combinado (líneas + palabras): " + (numLineas + numPalabras));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int leerResultado(Process proceso) {
        int resultado = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Buscar la línea que contiene el resultado
                if (linea.trim().matches("^\\d+$")) {
                    resultado = Integer.parseInt(linea.trim());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
