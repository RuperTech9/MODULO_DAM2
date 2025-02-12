package Programacion.T04_GeneracionServiciosEnRed.Examen.Examen1;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorArchivos {
    private static final int PUERTO = 44441;
    private static final String DIRECTORIO_BASE = "C:/ServidorArchivos";

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            // Crear directorio base si no existe
            File directorioBase = new File(DIRECTORIO_BASE);
            if (!directorioBase.exists()) {
                directorioBase.mkdirs();
            }

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                pool.execute(new HiloCliente(cliente, DIRECTORIO_BASE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}

class HiloCliente implements Runnable {
    private Socket cliente;
    private String directorioBase;

    public HiloCliente(Socket cliente, String directorioBase) {
        this.cliente = cliente;
        this.directorioBase = directorioBase;
    }

    @Override
    public void run() {
        try (
                DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())
        ) {
            while (true) {
                // Leer la acción solicitada por el cliente
                String accion = entrada.readUTF();

                switch (accion) {
                    case "LISTAR":
                        listarArchivos(salida);
                        break;

                    case "SUBIR":
                        recibirArchivo(entrada, salida);
                        break;

                    case "DESCARGAR":
                        enviarArchivo(entrada, salida);
                        break;

                    case "SALIR":
                        System.out.println("Cliente desconectado.");
                        cliente.close();
                        return;

                    default:
                        salida.writeUTF("Acción no reconocida.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listarArchivos(DataOutputStream salida) throws IOException {
        File directorio = new File(directorioBase);
        File[] archivos = directorio.listFiles();

        if (archivos != null && archivos.length > 0) {
            for (File archivo : archivos) {
                salida.writeUTF(archivo.getName());
            }
        } else {
            salida.writeUTF("No hay archivos en el servidor.");
        }
        salida.writeUTF("FIN_LISTA");
    }

    private void recibirArchivo(DataInputStream entrada, DataOutputStream salida) throws IOException {
        String nombreArchivo = entrada.readUTF();
        long tamanioArchivo = entrada.readLong();

        try (FileOutputStream fos = new FileOutputStream(directorioBase + "/" + nombreArchivo)) {
            byte[] buffer = new byte[1024];
            int leidos;
            long totalLeidos = 0;

            while ((leidos = entrada.read(buffer, 0, (int) Math.min(buffer.length, tamanioArchivo - totalLeidos))) > 0) {
                fos.write(buffer, 0, leidos);
                totalLeidos += leidos;
                if (totalLeidos == tamanioArchivo) break;
            }
        }
        salida.writeUTF("Archivo subido correctamente.");
    }

    private void enviarArchivo(DataInputStream entrada, DataOutputStream salida) throws IOException {
        String nombreArchivo = entrada.readUTF();
        File archivo = new File(directorioBase + "/" + nombreArchivo);

        if (archivo.exists() && archivo.isFile()) {
            salida.writeUTF("EXISTE");
            salida.writeLong(archivo.length());

            try (FileInputStream fis = new FileInputStream(archivo)) {
                byte[] buffer = new byte[1024];
                int leidos;
                while ((leidos = fis.read(buffer)) > 0) {
                    salida.write(buffer, 0, leidos);
                }
            }
        } else {
            salida.writeUTF("NO_EXISTE");
        }
    }
}
