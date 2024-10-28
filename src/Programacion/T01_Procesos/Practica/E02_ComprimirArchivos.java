package Programacion.T01_Procesos.Practica;

import java.io.*;

/**
 * La clase E02_ComprimirArchivos permite comprimir multiples archivos en un solo archivo .tar
 * utilizando el comando del sistema operativo `tar`. Este tipo de funcionalidad es util para
 * empaquetar varios archivos en uno solo para su almacenamiento o distribucion eficiente.
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * E02_ComprimirArchivos.main(new String[0]);
 * }
 * </pre>
 *
 * <p>Este programa genera un archivo .tar que contiene los archivos especificados en el array
 * {@code archivosAComprimir}, y utiliza un subproceso para ejecutar el comando `tar`.</p>
 *
 * @author Ruper
 * @version 1.0
 */
public class E02_ComprimirArchivos {

    /**
     * Metodo principal que ejecuta el proceso de compresion de archivos en un archivo .tar
     * mediante el comando `tar`. Este metodo recoge los archivos de un array de rutas, inicia un
     * proceso con `tar`, envia los nombres de los archivos a traves de la entrada estandar del
     * subproceso, y gestiona la salida y el estado del proceso.
     *
     * <p>La compresion se realiza con el comando `tar -cvf [archivoDestino] -T -`, donde `archivoDestino`
     * es el nombre del archivo .tar generado.</p>
     *
     * @param args No se utilizan argumentos en este caso.
     *
     * <p>Ejemplo de uso:</p>
     * <pre>
     * {@code
     * E02_ComprimirArchivos.main(new String[0]);
     * }
     * </pre>
     */
    public static void main(String[] args) {
        // Array de archivos a comprimir
        String[] archivosAComprimir = {
                "./src/Programacion/T01_Procesos/Practica/archivo1.txt",
                "./src/Programacion/T01_Procesos/Practica/archivo.txt",
                "./src/Programacion/T01_Procesos/Practica/archivo3.txt"
        };

        // Nombre del archivo .tar que se va a crear
        String archivoDestino = "./src/Programacion/T01_Procesos/Practica/archivos_comprimidos.tar";

        // Crear el proceso para ejecutar el comando tar en modo verbose (-v)
        ProcessBuilder pb = new ProcessBuilder("tar", "-cvf", archivoDestino, "-T", "-");

        // Iniciar el proceso
        try {
            Process proceso = pb.start();

            // Enviar la lista de archivos al subproceso a través de la entrada estándar
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
                for (int i = 0; i < archivosAComprimir.length; i++) {
                    bw.write(archivosAComprimir[i]);
                    bw.newLine(); // Asegurarse de que cada archivo se escribe en una nueva línea
                }
                bw.flush(); // Asegurarse de que los datos se envían al subproceso
            }

            // Leer la respuesta del subproceso (salida estándar)
            try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Leer cada línea de la salida estándar del proceso tar
                    System.out.println(linea);
                }
            }

            // Comprobar si el subproceso ha terminado correctamente
            int exitCode = proceso.waitFor();
            if (exitCode == 0) {
                System.out.println("Compresión completada exitosamente.");
            } else {
                System.err.println("Error durante la compresión. \nCódigo de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar el proceso de compresión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

