package Programacion.T01_Procesos.Practica;

import java.io.*;

/**
 * La clase E03_SincronizacionSubprocesos permite contar el numero de lineas y palabras
 * en archivos de texto utilizando subprocesos para ejecutar comandos de PowerShell.
 *
 * El proposito de esta clase es realizar un analisis basico de contenido en dos archivos
 * de texto diferentes, contando el numero de lineas en un archivo y el numero de palabras
 * en otro archivo.
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * E03_SincronizacionSubprocesos.main(new String[0]);
 * }
 * </pre>
 *
 * Esta clase es util para automatizar tareas de conteo de lineas y palabras en archivos
 * de texto mediante la ejecucion de subprocesos en PowerShell desde un programa Java.
 *
 * @author Ruper
 * @version 1.0
 */
public class E03_SincronizacionSubprocesos {

    /**
     * El metodo principal ejecuta los subprocesos para contar el numero de lineas en un
     * archivo de texto y el numero de palabras en otro archivo de texto, utilizando comandos
     * de PowerShell. Luego, muestra los resultados en la consola.
     *
     * <p>Este metodo primero verifica que los archivos de entrada existen. Despues, se
     * ejecutan comandos de PowerShell para contar las lineas y palabras de los archivos,
     * y finalmente se muestra el total combinado.</p>
     *
     * @param args No se utilizan argumentos en este ejemplo.
     *
     * <p>Ejemplo de uso:</p>
     * <pre>
     * {@code
     * E03_SincronizacionSubprocesos.main(new String[0]);
     * }
     * </pre>
     */
    public static void main(String[] args) {

        // Archivos de texto para el conteo de líneas y palabras
        File archivoLineas = new File("./src/Programacion/T01_Procesos/Practica/archivo1.txt");  // Ruta del archivo para contar líneas
        File archivoPalabras = new File("./src/Programacion/T01_Procesos/Practica/archivo2.txt"); // Ruta del archivo para contar palabras

        // Verificar si los archivos existen
        if (!archivoLineas.exists() || !archivoPalabras.exists()) {
            System.err.println("Uno o ambos archivos no existen.");
            return;
        }

        // Ejecutar el subproceso que cuenta las líneas
        int lineas = ejecutarComandoPowerShell("(Get-Content " + archivoLineas.getAbsolutePath() + " | Measure-Object -Line).Lines");
        if (lineas != -1) {
            System.out.println("Número de líneas en " + archivoLineas.getName() + ": " + lineas);
        } else {
            System.err.println("Error al contar líneas en " + archivoLineas.getName());
        }

        // Ejecutar el subproceso que cuenta las palabras
        int palabras = ejecutarComandoPowerShell("(Get-Content " + archivoPalabras.getAbsolutePath() + " | Measure-Object -Word).Words");
        if (palabras != -1) {
            System.out.println("Número de palabras en " + archivoPalabras.getName() + ": " + palabras);
        } else {
            System.err.println("Error al contar palabras en " + archivoPalabras.getName());
        }

        // Mostrar el total combinado de líneas y palabras
        if (lineas != -1 && palabras != -1) {
            System.out.println("Total combinado de líneas y palabras: " + (lineas + palabras));
        }
    }

    /**
     * Ejecuta un comando de PowerShell en un subproceso y devuelve el resultado como un numero entero.
     * Este metodo se utiliza para ejecutar comandos que cuentan lineas o palabras en un archivo de texto.
     *
     * <p>El comando PowerShell que se pasa como argumento se ejecuta en un subproceso,
     * y el resultado se obtiene leyendo la salida estandar del proceso.</p>
     *
     * @param comando El comando de PowerShell que se desea ejecutar (por ejemplo,
     *                {@code "(Get-Content archivo1.txt | Measure-Object -Line).Lines"}).
     * @return El resultado del comando, que es un numero entero que representa el numero de
     *         lineas o palabras. Si ocurre un error, el metodo devuelve -1.
     *
     * <p>Ejemplo de uso:</p>
     * <pre>
     * {@code
     * int lineas = ejecutarComandoPowerShell("(Get-Content archivo1.txt | Measure-Object -Line).Lines");
     * }
     * </pre>
     */
    private static int ejecutarComandoPowerShell(String comando) {
        ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", comando);
        try {
            Process proceso = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.matches("\\d+")) { //"\\d"indica que la línea debe contener uno o más dígitos.
                    return Integer.parseInt(linea.trim()); // Devolver el número encontrado
                }
            }
            proceso.waitFor(); // Esperar a que termine el proceso
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return -1; // En caso de error, devolver -1
    }
}

