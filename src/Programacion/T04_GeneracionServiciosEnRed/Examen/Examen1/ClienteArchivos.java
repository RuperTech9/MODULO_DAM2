package Programacion.T04_GeneracionServiciosEnRed.Examen.Examen1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteArchivos {
    private static final String SERVIDOR = "127.0.0.1";
    private static final int PUERTO = 44441;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             DataInputStream entrada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream())) {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Opciones:");
                System.out.println("1. Listar archivos");
                System.out.println("2. Subir archivo");
                System.out.println("3. Descargar archivo");
                System.out.println("4. Salir");
                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        salida.writeUTF("LISTAR");
                        String respuesta;
                        while (!(respuesta = entrada.readUTF()).equals("FIN_LISTA")) {
                            System.out.println(respuesta);
                        }
                        break;

                    case 2:
                        System.out.print("Introduce la ruta con el nombre del archivo a subir: ");
                        String rutaArchivoSubir = scanner.nextLine();
                        File archivoSubir = new File(rutaArchivoSubir);

                        if (archivoSubir.exists() && archivoSubir.isFile()) {
                            salida.writeUTF("SUBIR");
                            salida.writeUTF(archivoSubir.getName());
                            salida.writeLong(archivoSubir.length());

                            try (FileInputStream fis = new FileInputStream(archivoSubir)) {
                                byte[] buffer = new byte[1024];
                                int leidos;
                                while ((leidos = fis.read(buffer)) > 0) {
                                    salida.write(buffer, 0, leidos);
                                }
                            }
                            System.out.println(entrada.readUTF());
                        } else {
                            System.out.println("Archivo no encontrado.");
                        }
                        break;

                    case 3:
                        System.out.print("Introduce el nombre del archivo a descargar: ");
                        String nombreArchivoDescargar = scanner.nextLine();
                        salida.writeUTF("DESCARGAR");
                        salida.writeUTF(nombreArchivoDescargar);

                        String estado = entrada.readUTF();
                        if (estado.equals("EXISTE")) {
                            long tamanio = entrada.readLong();
                            try (FileOutputStream fos = new FileOutputStream(nombreArchivoDescargar)) {
                                byte[] buffer = new byte[1024];
                                int leidos;
                                long totalLeidos = 0;

                                while ((leidos = entrada.read(buffer, 0, (int) Math.min(buffer.length, tamanio - totalLeidos))) > 0) {
                                    fos.write(buffer, 0, leidos);
                                    totalLeidos += leidos;
                                    if (totalLeidos == tamanio) break;
                                }
                            }
                            System.out.println("Archivo descargado correctamente.");
                        } else {
                            System.out.println("El archivo no existe en el servidor.");
                        }
                        break;

                    case 4:
                        salida.writeUTF("SALIR");
                        System.out.println("Saliendo...");
                        return;

                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
