package Programacion.T04_GeneracionServiciosEnRed.Ejemplos.FTP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FTPExampleFull {
    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();

        try {
            // Conexión al servidor FTP
            String server = "127.0.0.1";  // Dirección del servidor
            int port = 21;               // Puerto estándar FTP
            String user = "usuario1";    // Usuario configurado en FileZilla
            String password = "1234"; // Contraseña del usuario

            System.out.println("Conectando al servidor FTP...");
            ftpClient.connect(server, port);

            // Verificar conexión
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("El servidor respondió con un error: " + replyCode);
                return;
            }

            // Inicio de sesión
            if (!ftpClient.login(user, password)) {
                System.out.println("Error al iniciar sesión.");
                return;
            }
            System.out.println("Inicio de sesión exitoso.");

            // Configuración del modo pasivo y tipo de archivo
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Ruta local
            String localDirPath = "C:\\Users\\Ruper\\Desktop\\AccesoServidor\\NUEVODIR";
            File localDir = new File(localDirPath);

            // Subir archivo al servidor
            String localFilePath = localDirPath + "\\archivo_subir.txt"; // Archivo a subir
            String remoteFileName = "archivo_remoto.txt"; // Nombre en el servidor
            File localFile = new File(localFilePath);

            if (localFile.exists()) {
                FileInputStream fis = new FileInputStream(localFile);
                if (ftpClient.storeFile(remoteFileName, fis)) {
                    System.out.println("Archivo subido con éxito: " + remoteFileName);
                } else {
                    System.out.println("Error al subir el archivo.");
                }
                fis.close();
            } else {
                System.out.println("El archivo local no existe: " + localFilePath);
            }

            // Listar archivos en el servidor
            System.out.println("Archivos en el servidor:");
            String[] files = ftpClient.listNames();
            if (files != null && files.length > 0) {
                for (String file : files) {
                    System.out.println(" - " + file);
                }
            } else {
                System.out.println("El servidor no tiene archivos en el directorio actual.");
            }

            // Descargar archivo desde el servidor
            String remoteFileToDownload = "archivo_remoto.txt"; // Archivo en el servidor
            String localDownloadPath = localDirPath + "\\archivo_descargado.txt"; // Ruta local para guardar
            FileOutputStream fos = new FileOutputStream(localDownloadPath);

            if (ftpClient.retrieveFile(remoteFileToDownload, fos)) {
                System.out.println("Archivo descargado con éxito: " + localDownloadPath);
            } else {
                System.out.println("Error al descargar el archivo.");
            }
            fos.close();

            // Cerrar sesión y desconectar
            ftpClient.logout();
            System.out.println("Sesión cerrada.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
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
