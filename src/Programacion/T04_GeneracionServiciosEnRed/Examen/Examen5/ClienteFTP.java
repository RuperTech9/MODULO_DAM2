package Programacion.T04_GeneracionServiciosEnRed.Examen.Examen5;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class ClienteFTP {
    // Configuración del servidor FTP
    private final String servidorFTP = "localhost"; // Dirección del servidor FTP
    private final int puertoFTP = 21; // Puerto estándar del protocolo FTP
    private final String usuarioFTP = "usuario1"; // Nombre de usuario del servidor FTP
    private final String contrasenaFTP = "1234"; // Contraseña del usuario
    private FTPClient clienteFTP; // Objeto FTPClient para manejar la conexión

    // Constructor que inicializa el cliente FTP
    public ClienteFTP() {
        clienteFTP = new FTPClient();
    }

    /**
     * Conectar al servidor FTP con las credenciales especificadas.
     * @return true si la conexión es exitosa, false en caso contrario.
     */
    public boolean conectar() {
        try {
            clienteFTP.connect(servidorFTP, puertoFTP);
            if (clienteFTP.login(usuarioFTP, contrasenaFTP)) {
                System.out.println("Conexión al servidor FTP exitosa.");
                clienteFTP.enterLocalPassiveMode(); // Activar modo pasivo para evitar problemas con firewalls
                clienteFTP.setFileType(FTP.BINARY_FILE_TYPE); // Configurar transferencia en modo binario
                return true;
            } else {
                System.out.println("Error al iniciar sesión en el servidor FTP.");
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Error al conectar con el servidor FTP: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Lista los archivos y directorios disponibles en el servidor FTP.
     */
    public void listarArchivos() {
        try {
            System.out.println("Archivos y directorios disponibles:");
            for (String nombre : clienteFTP.listNames()) {
                System.out.println(" - " + nombre);
            }
        } catch (IOException e) {
            System.out.println("Error al listar archivos.");
        }
    }

    /**
     * Sube un archivo al servidor FTP.
     * @param rutaArchivo Ruta local del archivo a subir.
     */
    public void subirArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            try (FileInputStream fis = new FileInputStream(archivo)) {
                boolean exito = clienteFTP.storeFile(archivo.getName(), fis);
                System.out.println(exito ? "Archivo subido correctamente." : "Error al subir el archivo.");
            } catch (IOException e) {
                System.out.println("Error al subir el archivo.");
            }
        } else {
            System.out.println("El archivo no existe en la ruta especificada.");
        }
    }

    /**
     * Descarga un archivo del servidor FTP y lo guarda en la ruta especificada.
     * @param archivoRemoto Nombre del archivo en el servidor FTP.
     * @param rutaLocal Ruta local donde se guardará el archivo descargado.
     */
    public void descargarArchivo(String archivoRemoto, String rutaLocal) {
        File destino = new File(rutaLocal);

        // Si la ruta local es un directorio, añadir el nombre del archivo
        if (destino.isDirectory()) {
            destino = new File(destino, archivoRemoto);
        }

        try (FileOutputStream fos = new FileOutputStream(destino)) {
            boolean exito = clienteFTP.retrieveFile(archivoRemoto, fos);
            System.out.println(exito ? "Archivo descargado correctamente en: " + destino.getAbsolutePath() : "Error al descargar el archivo.");
        } catch (IOException e) {
            System.out.println("Error al descargar el archivo.");
        }
    }

    /**
     * Cierra la sesión y desconecta del servidor FTP.
     */
    public void desconectar() {
        try {
            if (clienteFTP.isConnected()) {
                clienteFTP.logout(); // Cierra sesión en el servidor FTP
                clienteFTP.disconnect(); // Cierra la conexión FTP
                System.out.println("Desconectado del servidor FTP.");
            }
        } catch (IOException e) {
            System.out.println("Error al desconectar del servidor FTP.");
        }
    }
}
