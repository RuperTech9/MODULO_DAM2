package Programacion.T04_GeneracionServiciosEnRed.Ejemplos.FTP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FTPExample {

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();

        // Datos de conexión
        String server = "127.0.0.1";  // Dirección del servidor
        int port = 21;               // Puerto FTP estándar
        String user = "usuario1";    // Nombre de usuario (configurado en FileZilla)
        String password = "1234"; // Contraseña del usuario

        try {
            // Conectar al servidor FTP
            System.out.println("Conectando al servidor FTP...");
            ftpClient.connect(server, port);
            int replyCode = ftpClient.getReplyCode();
            if (!ftpClient.login(user, password)) {
                System.out.println("Error: No se pudo iniciar sesión en el servidor FTP.");
                return;
            }

            System.out.println("Conectado al servidor FTP con éxito.");
            System.out.println("Código de respuesta: " + replyCode);

            // Cambiar al modo pasivo y establecer tipo de archivo binario
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Listar archivos y carpetas del directorio actual
            System.out.println("Archivos en el directorio actual:");
            String[] files = ftpClient.listNames();
            if (files != null && files.length > 0) {
                for (String file : files) {
                    System.out.println(" - " + file);
                }
            } else {
                System.out.println("No hay archivos en el directorio actual.");
            }

            // Subir un archivo al servidor FTP
            String localFilePath = "C:/ejemplo.txt"; // Ruta del archivo local a subir
            String remoteFileName = "subido_ejemplo.txt"; // Nombre del archivo en el servidor
            File localFile = new File(localFilePath);
            if (localFile.exists()) {
                FileInputStream fis = new FileInputStream(localFile);
                boolean uploaded = ftpClient.storeFile(remoteFileName, fis);
                fis.close();
                if (uploaded) {
                    System.out.println("El archivo '" + remoteFileName + "' se ha subido correctamente.");
                } else {
                    System.out.println("No se pudo subir el archivo.");
                }
            } else {
                System.out.println("El archivo local no existe: " + localFilePath);
            }

            // Cerrar sesión y desconectar
            ftpClient.logout();
            System.out.println("Sesión cerrada.");
        } catch (IOException ex) {
            System.out.println("Ocurrió un error durante la conexión o transferencia: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                    System.out.println("Desconectado del servidor FTP.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
