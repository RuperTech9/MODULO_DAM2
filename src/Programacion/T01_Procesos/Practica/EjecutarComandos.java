package Programacion.T01_Procesos.Practica;

import java.io.*;

/**
 * La clase {@code EjecutorComandos} proporciona métodos para ejecutar comandos del
 * sistema operativo, manejar la entrada estándar y redirigir la salida de los subprocesos.
 */
public class EjecutarComandos {
    /**
     * Ejecuta un comando en el sistema operativo.
     *
     * @param comando El comando a ejecutar.
     * @return El código de salida del proceso: 0 si se ejecutó correctamente, -1 en caso de error.
     */
    public int ejecutarComando(String comando) {
        try {
            ProcessBuilder pb = new ProcessBuilder("CMD", "/C", comando);
            Process proceso = pb.start();

            int exitCode = proceso.waitFor();
            return exitCode;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }//FIN ejecutarComando

    /**
     * Ejecuta un comando en el sistema operativo y proporciona la entrada estándar.
     *
     * @param comando El comando a ejecutar.
     * @param entrada La entrada estándar que se enviará al proceso.
     * @return El código de salida del proceso: 0 si se ejecutó correctamente, -1 en caso de error.
     */
    public int ejecutarComandoConEntrada(String comando, String entrada) {
        try {
            // Crear el proceso para ejecutar el comando
            ProcessBuilder pb = new ProcessBuilder("CMD", "/C", comando);
            Process proceso = pb.start();

            try (OutputStream os = proceso.getOutputStream()) {
                os.write(entrada.getBytes());
                os.flush();
            }

            int exitCode = proceso.waitFor();
            return exitCode;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    } //FIN ejecutarComandoConEntrada

    /**
     * Ejecuta un comando en el sistema operativo y redirige la salida estándar a un archivo.
     *
     * @param comando El comando a ejecutar.
     * @param archivoSalida El archivo en el que se almacenará la salida estándar del proceso.
     * @return El código de salida del proceso: 0 si se ejecutó correctamente, -1 en caso de error.
     */
    public int ejecutarComandoConRedireccion(String comando, File archivoSalida) {
        try {
            ProcessBuilder pb = new ProcessBuilder("CMD", "/C", comando);
            pb.redirectOutput(archivoSalida);
            Process proceso = pb.start();

            int exitCode = proceso.waitFor();
            return exitCode;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    } //FIN ejecutarComandoConRedireccion

    public static void main(String[] args) {
        EjecutarComandos ejecutar = new EjecutarComandos();

        // Ejemplo 1: Ejecutar un comando simple
        int resultado = ejecutar.ejecutarComando("DIR");
        System.out.println("Código de salida de ejecutarComando: " + resultado);

        // Ejemplo 2: Ejecutar un comando con entrada estándar
        resultado = ejecutar.ejecutarComandoConEntrada("DATE", "01-01-2024\n");
        System.out.println("Código de salida de ejecutarComandoConEntrada: " + resultado);

        // Ejemplo 3: Ejecutar un comando con redirección de salida
        File archivoSalida = new File("./src/Programacion/T01_Procesos/Practica/salida.txt");
        resultado = ejecutar.ejecutarComandoConRedireccion("DIR", archivoSalida);
        System.out.println("Código de salida de ejecutarComandoConRedireccion: " + resultado);
    } //FIN Main
} //FIN CLASE EjecutarComandos
