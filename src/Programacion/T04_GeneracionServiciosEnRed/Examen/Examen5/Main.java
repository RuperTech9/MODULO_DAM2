package Programacion.T04_GeneracionServiciosEnRed.Examen.Examen5;

import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, InvalidKeyException, InvalidKeySpecException {
        Scanner sc = new Scanner(System.in);
        ClienteFTP clienteFTP = new ClienteFTP(); // Instancia del cliente FTP
        ClienteSMTP clienteSMTP = new ClienteSMTP(); // Instancia del cliente SMTP

        System.out.println("Iniciando aplicación...");

        // Intentar conectar al servidor FTP
        if (!clienteFTP.conectar()) {
            System.out.println("No se pudo conectar al servidor FTP. Saliendo...");
            return;
        }

        String archivoAdjunto = null; // Variable para almacenar el archivo adjunto en caso de ser necesario

        // Bucle principal para manejar las opciones del usuario
        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Listar archivos y directorios en el servidor FTP");
            System.out.println("2. Subir archivo al servidor FTP");
            System.out.println("3. Descargar archivo del servidor FTP");
            System.out.println("4. Enviar correo (con o sin archivo adjunto)");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea después del número ingresado

            switch (opcion) {
                case 1:
                    // Listar archivos y directorios del servidor FTP
                    clienteFTP.listarArchivos();
                    break;
                case 2:
                    // Subir archivo al servidor FTP
                    System.out.print("Introduce la ruta del archivo a subir: ");
                    String rutaSubir = sc.nextLine();
                    clienteFTP.subirArchivo(rutaSubir);
                    archivoAdjunto = rutaSubir; // Guardar la ruta del archivo para enviarlo como adjunto en un correo
                    break;
                case 3:
                    // Descargar archivo del servidor FTP
                    System.out.print("Introduce el nombre del archivo a descargar: ");
                    String archivoDescargar = sc.nextLine();
                    System.out.print("Introduce la ruta local para guardar el archivo: ");
                    String rutaDescargar = sc.nextLine();
                    clienteFTP.descargarArchivo(archivoDescargar, rutaDescargar);
                    archivoAdjunto = rutaDescargar; // Guardar la ruta del archivo descargado para enviarlo como adjunto en un correo
                    break;
                case 4:
                    // Enviar correo electrónico con o sin archivo adjunto
                    clienteSMTP.enviarCorreo(sc, archivoAdjunto);
                    break;
                case 5:
                    // Cerrar la aplicación
                    System.out.println("Cerrando aplicación...");
                    clienteFTP.desconectar();
                    return; // Finaliza el programa
                default:
                    // Manejo de opción no válida
                    System.out.println("Opción no válida.");
            }
        }
    }
}
