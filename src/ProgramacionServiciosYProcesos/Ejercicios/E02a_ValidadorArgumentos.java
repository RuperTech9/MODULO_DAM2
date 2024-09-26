package ProgramacionServiciosYProcesos.Ejercicios;

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
