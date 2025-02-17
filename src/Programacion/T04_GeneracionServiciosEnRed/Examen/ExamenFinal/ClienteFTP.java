package Programacion.T04_GeneracionServiciosEnRed.Examen.ExamenFinal;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class ClienteFTP {
	/**
	 * Conecta al servidor FTP con las credenciales especificadas.
	 * @return el contenido de la carpeta "pub".
	 */
	public static String obtenerContenidoFTP() {
		// Configuración del servidor FTP
		FTPClient cliente = new FTPClient();
		String servidorFTP = "ftp.iac.es"; // Dirección del servidor FTP
		String usuario = "anonymous";
		String clave = "anonymous";
		StringBuilder contenido = new StringBuilder(); // StringBuilder para el contenido de la carpeta "pub"

		System.out.println("Conectando al servidor FTP: " + servidorFTP);
		try {
			cliente.connect(servidorFTP);
			cliente.enterLocalPassiveMode(); // Modo pasivo para evitar problemas de conexión

			boolean login = cliente.login(usuario, clave);
			if (!login) {
				System.err.println("Error: Login incorrecto.");
				cliente.disconnect();
				return null;
			}

			// Cambio al directorio pub
			if (!cliente.changeWorkingDirectory("pub")) {
				System.err.println("Error: No se pudo acceder al directorio 'pub'.");
				cliente.logout();
				cliente.disconnect();
				return null;
			}

			System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

			// Lista de archivos y carpetas en "pub"
			FTPFile[] files = cliente.listFiles();
			System.out.println("Ficheros en 'pub': " + files.length);

			for (FTPFile file : files) {
				String tipo = file.isDirectory() ? "Directorio" : "Archivo";
				contenido.append(file.getName()).append(" - ").append(tipo).append("\n");
			}

			// Cierro sesión y desconecto del servidor FTP
			cliente.logout();
			cliente.disconnect();
			System.out.println("\nDesconectado del servidor FTP.");

		} catch (IOException e) {
			System.err.println("Error conexión FTP: " + e.getMessage());
		}
		return contenido.toString();
	}

	public static void main(String[] args) {
		String contenidoFTP = obtenerContenidoFTP();
		if (contenidoFTP != null) {
			System.out.println("\nEste es el contenido del directorio 'pub':\n" + contenidoFTP);
			ClienteSMTP.enviarCorreo(contenidoFTP);
		} else {
			System.err.println("No se pudo obtener el contenido del FTP.");
		}
	}
}