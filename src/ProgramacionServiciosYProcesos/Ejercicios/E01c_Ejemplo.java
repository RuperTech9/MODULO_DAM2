
package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.*;

/**
 * Ejercicio 1.1(b).
 * A continuación, haz un programa parecido a E01c_Ejemplo.java para ejecutar
 * LeerNombre.java. Utiliza el método waitFor() para comprobar el valor de salida del
 * proceso que se ejecuta. Prueba la ejecución del programa dando valor a los
 * argumentos de main() y sin darle valor. ¿Qué valor devuelve waitFor() en un caso y otro?
 * 
 * 
 * @author Ruper
 */
public class E01c_Ejemplo {
    public static void main(String[] args) {
        try {
            // Comando para ejecutar LeerNombre con un argumento
            // Si LeerNombre pertenece a un paquete, añade 'TEMA1_PROCESOS.' antes del nombre de la clase
            String command = "java ProgramacionServiciosYProcesos.LeerNombre Juan"; // Cambia a "java LeerNombre Juan" si no hay paquete

            // Crear el proceso
            Process process = Runtime.getRuntime().exec(command);

            // Esperar a que el proceso termine y obtener el valor de salida
            int exitValue = process.waitFor();

            // Comprobar el valor de salida
            if (exitValue == 1) {
                System.out.println("El proceso terminó correctamente con valor de salida: " + exitValue);
            } else {
                System.out.println("El proceso terminó con error, valor de salida: " + exitValue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
C:\Users\Ruper\IdeaProjects\MODULO_DAM2\src\ProgramacionServiciosYProcesos\Ejercicios
cd C:\Users\Ruper\Documents\NetBeansProjects\PROGRAMACION\src\main\java
javac TEMA1_PROCESOS\LeerNombre.java TEMA1_PROCESOS\E01c_Ejemplo.java

Esto creará los archivos .class dentro de la carpeta TEMA1_PROCESOS

java TEMA1_PROCESOS.E01c_Ejemplo

*/