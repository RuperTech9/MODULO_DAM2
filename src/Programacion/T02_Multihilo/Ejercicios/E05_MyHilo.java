package Programacion.T02_Multihilo.Ejercicios;

import java.util.Scanner;

// Clase MyHilo que extiende Thread
class E05_MyHilo extends Thread {
    private E05_SolicitaSuspender suspender = new E05_SolicitaSuspender();
    private boolean hayTrabajo = true; // Control de trabajo en el hilo
    private Integer contador = 0; // Variable contador que se incrementará

    // Método para suspender el hilo
    public void suspende() {
        suspender.set(true);
    }

    // Método para reanudar el hilo
    public void reanuda() {
        suspender.set(false);
    }

    // Método que devuelve el valor del contador
    public int getContador() {
        return contador;
    }

    // Método run que contiene la tarea del hilo
    public void run() {
        try {
            while (hayTrabajo) {
                contador++; // Incrementamos el contador
                System.out.println("Contador: " + contador);

                // Pausa de 1 segundo para simular trabajo
                Thread.sleep(1000);

                // Comprobación de si el hilo debe suspenderse
                suspender.esperandoParaReanudar();
            }
            System.out.println("Terminado");
        } catch (InterruptedException exception) {
            System.out.println("El hilo ha sido interrumpido.");
        }
    }

    // Método para detener el trabajo del hilo
    public void detenerTrabajo() {
        hayTrabajo = false; // Cambia la condición del bucle y detiene el hilo
    }

    public static void main(String[] args) throws InterruptedException {
        E05_MyHilo hilo = new E05_MyHilo();
        Scanner sc = new Scanner(System.in);
        boolean hiloLanzado = false; // Controla si el hilo se ha lanzado

        System.out.println("Introduce S para suspender, R para reanudar, * para salir:");

        while (true) {
            String input = sc.nextLine().trim();

            if (input.equals("*")) {
                break; // Termina el programa al introducir "*"
            }

            if (!hiloLanzado) {
                hilo.start(); // Lanzar el hilo la primera vez que se introduce un comando
                hiloLanzado = true;
            }

            if (input.equalsIgnoreCase("S")) {
                System.out.println("Suspendiendo el hilo...");
                hilo.suspende();
            } else if (input.equalsIgnoreCase("R")) {
                System.out.println("Reanudando el hilo...");
                hilo.reanuda();
            }
        }

        // Al finalizar el bucle, se muestra el valor del contador y se finaliza el hilo
        System.out.println("Valor final del contador: " + hilo.getContador());
        hilo.detenerTrabajo(); // Detenemos el trabajo del hilo
        hilo.interrupt(); // Interrumpimos el hilo para terminar su ejecución
        sc.close();
    }
}