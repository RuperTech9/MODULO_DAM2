package Programacion.T01_Procesos.Ejercicios;

/*
 * Realiza un programa java llamado LeerNombre.java que reciba desde los
 * argumentos de main() un nombre y lo visualice en pantalla. Utiliza System.exit(1)
 * para una finalización correcta del programa y System.exit(-1) para el caso que no se
 * hayan introducido los argumentos correctos en main().
 */

public class E01a_LeerNombre {
    public static void main(String[] args) {
        if(args.length == 0){
            System.err.println("Error: No se ha proporcionado ningún nombre.");
            System.exit(1);
        } else if(args.length > 0){
            System.out.println("Nombre recibido: " + args[0]);
            System.exit(0);
        }
    }
}
