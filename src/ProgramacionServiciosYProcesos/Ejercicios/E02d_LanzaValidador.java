package ProgramacionServiciosYProcesos.Ejercicios;

import java.io.IOException;

public class E02d_LanzaValidador {
    public static void main(String[] args) {
        try {
            // Proceso sin argumentos
            ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/PROGRAMACION/out/production/PROGRAMACION", "ProgramacionServiciosYProcesos.Ejercicios.ArgumentosChecker");
            Process process1 = pb1.start();
            int exitCode1 = process1.waitFor();
            mostrarResultado(exitCode1);

            // Proceso con argumento de cadena
            ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/PROGRAMACION/out/production/PROGRAMACION", "ProgramacionServiciosYProcesos.Ejercicios.ArgumentosChecker", "hola");
            Process process2 = pb2.start();
            int exitCode2 = process2.waitFor();
            mostrarResultado(exitCode2);

            // Proceso con número negativo
            ProcessBuilder pb3 = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/PROGRAMACION/out/production/PROGRAMACION", "ProgramacionServiciosYProcesos.Ejercicios.ArgumentosChecker", "-5");
            Process process3 = pb3.start();
            int exitCode3 = process3.waitFor();
            mostrarResultado(exitCode3);

            // Proceso con número positivo
            ProcessBuilder pb4 = new ProcessBuilder("java", "-cp", "C:/Users/Ruper/IdeaProjects/PROGRAMACION/out/production/PROGRAMACION", "ProgramacionServiciosYProcesos.Ejercicios.ArgumentosChecker", "10");
            Process process4 = pb4.start();
            int exitCode4 = process4.waitFor();
            mostrarResultado(exitCode4);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar el resultado basado en el código de salida
    public static void mostrarResultado(int exitCode) {
        switch (exitCode) {
            case 1:
                System.out.println("Resultado: No se han proporcionado suficientes argumentos.");
                break;
            case 2:
                System.out.println("Resultado: El argumento es una cadena.");
                break;
            case 3:
                System.out.println("Resultado: El número es menor que 0.");
                break;
            case 0:
                System.out.println("Resultado: El argumento es un número entero válido.");
                break;
            default:
                System.out.println("Resultado: Código de salida no esperado.");
                break;
        }
    }
}