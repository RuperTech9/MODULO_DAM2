package ProgramacionServiciosYProcesos.Ejercicios;

/*
 * Realiza un programa java que admita argumentos desde main() y devuelva con
 * System.exit() los siguientes valores:
 * · Si el número de argumentos es <1 debe devolver 1.
 * · Si el argumento es una cadena debe devolver 2.
 * · Si el argumento es un número entero menor que 0 debe devolver 3.
 * · En cualquier otra situación debe devolver 0.
 */

public class E02a_ValidadorArgumentos {
    public static void main(String[] args) {
        if (args.length < 1) {
            // No se ha pasado ningún argumento
            System.err.println("Error: No se ha proporcionado ningún argumento.");
            System.exit(1);
        }

        String argumento = args[0];

        try {
            // Intentamos convertir el argumento a número
            int numero = Integer.parseInt(argumento);

            if (numero < 0) {
                // El argumento es un número entero menor que 0
                System.err.println("Error: El argumento es un número menor que 0.");
                System.exit(3);
            } else {
                // El argumento es un número válido
                System.out.println("El argumento es un número válido: " + numero);
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            // El argumento no es un número, es una cadena de texto
            System.err.println("Error: El argumento es una cadena de texto.");
            System.exit(2);
        }
    }
}
