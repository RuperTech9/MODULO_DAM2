package Programacion.T04_GeneracionServiciosEnRed.Ejemplos.SMTP;

import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.net.smtp.*;

public class ClienteSMTP3_NOTLS {
	public static void main(String[] args) throws
			NoSuchAlgorithmException, UnrecoverableKeyException,
			KeyStoreException, InvalidKeyException, InvalidKeySpecException {

		AuthenticatingSMTPClient authenticatingSMTPClient = new AuthenticatingSMTPClient("TLS");

		// Datos del usuario y del servidor
		String server = "localhost"; // Mercury está corriendo en localhost
		String username = "ruper.dam1@gmail.com"; // Tu correo de Gmail
		String password = "wblwjmdzqqvkdcjw"; // Contraseña de aplicación de Gmail
		int puerto = 25; // Puerto configurado para Mercury
		String remitente = "postmaster@localhost"; // Remitente configurado en Mercury

		try {
			int respuesta;

			// Creación de la clave para establecer un canal seguro
			KeyManagerFactory keyManagerFactory = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(null, null);
			KeyManager keyManager = keyManagerFactory.getKeyManagers()[0];

			// Conexión al servidor Mercury
			authenticatingSMTPClient.connect(server, puerto);
			System.out.println("1 - " + authenticatingSMTPClient.getReplyString());

			authenticatingSMTPClient.login(server);

			// Se establece la clave
			authenticatingSMTPClient.setKeyManager(keyManager);

			respuesta = authenticatingSMTPClient.getReplyCode();
			if (!SMTPReply.isPositiveCompletion(respuesta)) {
				authenticatingSMTPClient.disconnect();
				System.err.println("SMTP server refused connection.");
				System.exit(1);
			}

			authenticatingSMTPClient.ehlo(server); // Necesario para el saludo EHLO
			System.out.println("2 - " + authenticatingSMTPClient.getReplyString());

			// Negociación TLS
			if (authenticatingSMTPClient.execTLS()) {
				System.out.println("3 - " + authenticatingSMTPClient.getReplyString());

				// Autenticación con Mercury y retransmisión a Gmail
				if (authenticatingSMTPClient.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, username, password)) {
					System.out.println("4 - " + authenticatingSMTPClient.getReplyString());

					String destino1 = "ruper1903@gmail.com";
					String asunto = "Prueba de SMTPClient con Mercury y Gmail";
					String mensaje = "Hola. \nEnviando saludos.\nUsando Mercury como relay.\nChao.";

					// Crear cabecera
					SimpleSMTPHeader simpleSMTPHeader = new SimpleSMTPHeader(remitente, destino1, asunto);
					authenticatingSMTPClient.setSender(remitente);
					authenticatingSMTPClient.addRecipient(destino1);
					System.out.println("5 - " + authenticatingSMTPClient.getReplyString());

					// Enviar DATA
					Writer writer = authenticatingSMTPClient.sendMessageData();

					if (writer == null) { // Falla al enviar
						System.out.println("FALLO AL ENVIAR DATA.");
						System.exit(1);
					}
					writer.write(simpleSMTPHeader.toString()); // Escribe cabecera
					writer.write(mensaje); // Escribe mensaje
					writer.close();

					System.out.println("6 - " + authenticatingSMTPClient.getReplyString());

					boolean exito = authenticatingSMTPClient.completePendingCommand();

					System.out.println("7 - " + authenticatingSMTPClient.getReplyString());

					if (!exito) { // Falla al finalizar
						System.out.println("FALLO AL FINALIZAR LA TRANSACCIÓN.");
						System.exit(1);
					} else
						System.out.println("MENSAJE ENVIADO CON ÉXITO......");
				} else {
					System.out.println("USUARIO NO AUTENTICADO: ");
					System.out.println(authenticatingSMTPClient.getReplyString());
				}
			} else {
				System.out.println("FALLO AL EJECUTAR STARTTLS.");
			}
		} catch (IOException e) {
			System.err.println("No se pudo conectar al servidor.");
			e.printStackTrace();
			System.exit(1);
		}
		try {
			authenticatingSMTPClient.disconnect();
		} catch (IOException f) {
			f.printStackTrace();
		}
		System.out.println("Fin de envío.");
		System.exit(0);
	}
}
