package Programacion.T01_Procesos.Practica;

import java.io.*;

/**
 * La clase E01_EjecutarComandos permite ejecutar comandos del sistema operativo
 * desde un programa Java, proporcionando metodos para ejecutar comandos simples,
 * comandos con entrada estandar y comandos con redireccion de salida.
 *
 * Esta clase es util para automatizar tareas del sistema operativo e interactuar
 * con herramientas externas.
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * E01_EjecutarComandos ejecutar = new E01_EjecutarComandos();
 * ejecutar.ejecutarComando("ping www.google.com");
 * }
 * </pre>
 *
 * @author Ruper
 * @version 1.0
 */
public class E01_EjecutarComandos {

    /**
     * Ejecuta un comando del sistema operativo sin entrada estandar ni redireccion de salida.
     * El comando es ejecutado en un subproceso, y se imprime su salida estandar en la consola.
     *
     * @param comando El comando a ejecutar (por ejemplo, "ping www.google.com").
     * @return El codigo de salida del proceso. Si el codigo es 0, termino correctamente;
     *         si es -1, ocurrio un error durante la ejecucion.
     *
     * <p>Ejemplo de uso:</p>
     * <pre>
     * {@code
     * int resultado = ejecutar.ejecutarComando("ping www.google.com");
     * }
     * </pre>
     */
    public int ejecutarComando(String comando) {
        try {
            // Crear el proceso con el comando
            ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
            Process proceso = pb.start();

            // Esperar a que el proceso termine y devolver el código de salida
            int exitCode = proceso.waitFor();
            imprimirSalidaProceso(proceso); // Imprimir la salida del proceso
            return exitCode;

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar el METODO 1: " + e.getMessage());
            return -1; // Código de error en caso de excepción
        }
    }//FIN ejecutarComando

    /**
     * Ejecuta un comando del sistema operativo que recibe entrada estandar.
     * El comando es ejecutado en un subproceso, al cual se le pasa la entrada estandar
     * especificada, y luego imprime su salida estandar en la consola.
     *
     * @param comando El comando a ejecutar (por ejemplo, "sort").
     * @param entrada La entrada estandar que se pasara al proceso (por ejemplo, una cadena
     *                con varios nombres que el comando 'sort' ordenara).
     * @return El codigo de salida del proceso. Si el codigo es 0, indica que el proceso
     *         termino correctamente; si es -1, ocurrio un error durante la ejecucion.
     *
     * <p>Ejemplo de uso:</p>
     * <pre>
     * {@code
     * int resultado = ejecutar.ejecutarComandoConEntrada("sort", "german\ndavid\nfrancesco\nalex");
     * }
     * </pre>
     */
    public int ejecutarComandoConEntrada(String comando, String entrada) {
        try {
            // Crear el proceso con el comando
            ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
            Process proceso = pb.start();

            // Enviar la entrada estándar al proceso
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
                writer.write(entrada);
                writer.flush();
            }

            // Esperar a que el proceso termine y devolver el código de salida
            int exitCode = proceso.waitFor();
            imprimirSalidaProceso(proceso); // Imprimir la salida del proceso
            return exitCode;

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar el METODO 2: " + e.getMessage());
            return -1; // Código de error en caso de excepción
        }
    } //FIN ejecutarComandoConEntrada

    /**
     * Ejecuta un comando del sistema operativo y redirige su salida estandar a un archivo.
     * El comando es ejecutado en un subproceso, y su salida estándar es escrita en el archivo
     * especificado.
     *
     * @param comando El comando a ejecutar (por ejemplo, "ipconfig").
     * @param archivoSalida El archivo donde se redirigira la salida del proceso.
     * @return El codigo de salida del proceso. Si el codigo es 0, indica que el proceso
     *         termino correctamente; si es -1, ocurrio un error durante la ejecucion.
     *
     * <p>Ejemplo de uso:</p>
     * <pre>
     * {@code
     * File archivoSalida = new File("salida.txt");
     * int resultado = ejecutar.ejecutarComandoConRedireccion("ipconfig", archivoSalida);
     * }
     * </pre>
     */
    public int ejecutarComandoConRedireccion(String comando, File archivoSalida) {
        try {
            // Crear el proceso con el comando
            ProcessBuilder pb = new ProcessBuilder(comando.split(" "));

            // Redirigir la salida estándar al archivo
            pb.redirectOutput(archivoSalida);

            // Iniciar el proceso
            Process proceso = pb.start();

            // Esperar a que el proceso termine y devolver el código de salida
            int exitCode = proceso.waitFor();
            return exitCode;

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar el METODO 3: " + e.getMessage());
            return -1; // Código de error en caso de excepción
        }
    } //FIN ejecutarComandoConRedireccion

    /**
     * Metodo auxiliar que imprime la salida estándar y la salida de error del proceso.
     * Lee y muestra en consola la salida generada por el proceso.
     *
     * @param proceso El objeto de tipo Process cuya salida sera leida.
     * @throws IOException Si ocurre un error al leer la salida del proceso.
     */
    private void imprimirSalidaProceso(Process proceso) throws IOException {
        // Leer la salida estándar del proceso
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        }
    }

    /**
     * Metodo principal que demuestra el uso de los metodos de la clase E01_EjecutarComandos.
     *
     * @param args Argumentos de linea de comandos (no utilizados en este ejemplo).
     */
    public static void main(String[] args) {
        E01_EjecutarComandos ejecutar = new E01_EjecutarComandos();

        // Ejemplo 1: Ejecutar un comando simple
        System.out.println("METODO 1: ejecuto comando 'ping':");
        ejecutar.ejecutarComando("ping www.google.com");

        // Ejemplo 2: Ejecutar un comando con entrada estándar
        System.out.println("\nMETODO 2: ejecuto comando para que me ordene los nombres que le paso:");
        ejecutar.ejecutarComandoConEntrada("sort", "german\ndavid\nfrancesco\nalex");

        // Ejemplo 3: Ejecutar un comando con redirección de salida
        File archivoSalida = new File("./src/Programacion/T01_Procesos/Practica/salida.txt");
        System.out.println("\nMETODO 3: ejecuto 'ipconfig' con redirección a salida.txt:");
        ejecutar.ejecutarComandoConRedireccion("ipconfig", archivoSalida);
        System.out.println("Salida redirigida a 'salida.txt'");
    } //FIN CrearBBDD_1
} //FIN CLASE E01_EjecutarComandos
