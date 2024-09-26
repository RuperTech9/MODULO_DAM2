package ProgramacionServiciosYProcesos.Ejercicios;

public class E01a_LeerNombre {
    public static void main(String[] args) {
        if(args.length == 0){
            System.err.println("Error: No se ha proporcionado ningún nombre.");
            System.exit(-1);
        } else if(args.length == 1){
            System.out.println("Nombre recibido: " + args[0]);
            System.exit(0);
        }
    }
}
