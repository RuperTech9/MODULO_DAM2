package ProgramacionServiciosYProcesos.Ejercicios;

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
