package ProgramacionServiciosYProcesos.Ejercicios;

/*
 * Escribe un programa java que visualice 5 veces la cadena que se le envía desde los
 * argumentos de main(). Si no se le envía ninguna cadena, que muestre un mensaje
 * indicándolo y que finalice el programa con System.exit(1).
 */

public class E03a_MostrarCadena {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error: No se ha proporcionado ninguna cadena.");
            System.exit(1);
        }

        String cadena = args[0];

        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ": " + cadena);
        }

        System.exit(0);
    }
}
