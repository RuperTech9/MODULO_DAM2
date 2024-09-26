package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.IOException;

public class E01b_Ejemplo {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar LeerNombre sin argumentos
            ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/PROGRAMACION/out/production/PROGRAMACION", "PROCESOS.LeerNombre");
            Process process1 = pb1.start();
            int exitCode1 = process1.waitFor(); // Esperar a que termine el proceso
            System.out.println("Proceso LeerNombre sin argumentos finalizó con código: " + exitCode1);

            // Crear el proceso para ejecutar LeerNombre con un argumento
            ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/PROGRAMACION/out/production/PROGRAMACION", "PROCESOS.LeerNombre", "Juan");
            Process process2 = pb2.start();
            int exitCode2 = process2.waitFor(); // Esperar a que termine el proceso
            System.out.println("Proceso LeerNombre con argumento finalizó con código: " + exitCode2);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


/*
Explicación del ProcessBuilder
ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/PROGRAMACION/out/production/PROGRAMACION", "PROCESOS.LeerNombre");
"java": Es el comando para ejecutar la máquina virtual de Java (JVM).
"-cp": Es la opción de línea de comandos para establecer el classpath, que le dice a la JVM dónde buscar las clases y paquetes.
"C:/Users/Ruper/IdeaProjects/PROGRAMACION/out/production/PROGRAMACION": Es el directorio donde se encuentran las clases compiladas de tu proyecto. Esta ruta debe apuntar al directorio raíz donde están ubicadas las carpetas de tus paquetes (PROCESOS en este caso) y sus archivos .class.
"PROCESOS.LeerNombre": Es el nombre completo de la clase con el paquete, en este caso PROCESOS es el nombre del paquete y LeerNombre es la clase que se ejecutará.
¿Por qué se usa esa ruta?
La ruta que usé es un ejemplo basado en la estructura de proyecto común en IntelliJ IDEA, donde:

out/production/PROGRAMACION es la carpeta donde se colocan las clases compiladas de tu proyecto PROGRAMACION.
Dentro de esa carpeta, debería estar la estructura de paquetes como PROCESOS/LeerNombre.class.
 */
