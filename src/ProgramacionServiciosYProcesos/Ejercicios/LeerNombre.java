
package ProgramacionServiciosYProcesos.Ejercicios;

/**
 * Ejercicio 1.1(a).
 * Realiza un programa java llamado LeerNombre.java que reciba desde los
 * argumentos de main() un nombre y lo visualice en pantalla. Utiliza System.exit(1)
 * para una finalización correcta del programa y System.exit(-1) para el caso que no se
 * hayan introducido los argumentos correctos en main().
 * 
 * @author Ruper
 */
public class LeerNombre {
    public static void main(String[] args) {
        if (args.length > 0) {// Verificamos si se ha pasado al menos un argumento
            System.out.println("Nombre recibido: " + args[0]);// Si hay argumentos, se imprime el nombre recibido
            System.exit(0);// Finalización correcta del programa
        } else {
            System.err.println("Error: No se ha proporcionado ningún nombre.");
            System.exit(1);// Finalización con error
        }
    }
}

/*
cd Documents\NetBeansProjects\PROGRAMACION\src\main\java
java TEMA1_PROCESOS.LeerNombre Alex

*/