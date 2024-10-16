package Programacion.T01_Procesos.Practica;

import java.io.*;


public class E01_EjecutarComandos {
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
            e.printStackTrace();
            return -1; // Código de error en caso de excepción
        }
    }//FIN ejecutarComando

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
            e.printStackTrace();
            return -1; // Código de error en caso de excepción
        }
    } //FIN ejecutarComandoConEntrada


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
            e.printStackTrace();
            return -1; // Código de error en caso de excepción
        }
    } //FIN ejecutarComandoConRedireccion

    // Método auxiliar para imprimir la salida del proceso
    private void imprimirSalidaProceso(Process proceso) throws IOException {
        // Leer la salida estándar del proceso
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        }

        // Leer la salida de error del proceso
        try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))) {
            String errorLinea;
            while ((errorLinea = errorReader.readLine()) != null) {
                System.err.println("Error: " + errorLinea);
            }
        }
    }

    public static void main(String[] args) {
        E01_EjecutarComandos ejecutar = new E01_EjecutarComandos();

        // Ejemplo 1: Ejecutar un comando simple
        System.out.println("Ejecutando comando 'ipconfig':");
        ejecutar.ejecutarComando("ipconfig");

        // Ejemplo 2: Ejecutar un comando con entrada estándar
        System.out.println("\nEjecutando 'sort' para que me ordene los nombres que le paso:");
        ejecutar.ejecutarComandoConEntrada("sort", "german\ndavid\nfrancesco\nalex");


        // Ejemplo 3: Ejecutar un comando con redirección de salida
        File archivoSalida = new File("./src/Programacion/T01_Procesos/Practica/salida.txt");
        System.out.println("\nEjecutando 'ipconfig' con redirección a salida.txt:");
        ejecutar.ejecutarComandoConRedireccion("ipconfig", archivoSalida);
        System.out.println("Salida redirigida a 'salida.txt'");
    } //FIN Main
} //FIN CLASE E01_EjecutarComandos
