package Programacion.T01_Procesos.Ejercicios;

import java.io.IOException;

/*
 * A continuación, haz un programa parecido a E01c_Ejemplo.java para ejecutar
 * LeerNombre.java. Utiliza el metodo waitFor() para comprobar el valor de salida del
 * proceso que se ejecuta. Prueba la ejecución del programa dando valor a los
 * argumentos de main() y sin darle valor. ¿Qué valor devuelve waitFor() en un caso y otro?
 */

public class E01b_Ejemplo {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar LeerNombre sin argumentos
            ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2");
            Process process1 = pb1.start();
            int exitCode1 = process1.waitFor(); // Esperar a que termine el proceso
            System.out.println("Proceso LeerNombre sin argumentos finalizó con código: " + exitCode1);

            // Crear el proceso para ejecutar LeerNombre con un argumento
            ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/MODULO_DAM2/out/production/MODULO_DAM2");
            Process process2 = pb2.start();
            int exitCode2 = process2.waitFor(); // Esperar a que termine el proceso
            System.out.println("Proceso LeerNombre con argumento finalizó con código: " + exitCode2);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
