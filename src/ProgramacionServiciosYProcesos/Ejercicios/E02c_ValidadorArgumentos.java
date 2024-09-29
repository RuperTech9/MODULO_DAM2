package ProgramacionServiciosYProcesos.Ejercicios;

public class E02c_ValidadorArgumentos {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error: No se han proporcionado suficientes argumentos.");
            System.exit(1);
        }

        try {
            int numero = Integer.parseInt(args[0]);

            if (numero < 0) {
                System.err.println("Error: El número es menor que 0.");
                System.exit(3);
            }

            System.out.println("El argumento es un número entero positivo o cero.");
            System.exit(0);

        } catch (NumberFormatException e) {
            System.err.println("Error: El argumento es una cadena.");
            System.exit(2);
        }
    }
}