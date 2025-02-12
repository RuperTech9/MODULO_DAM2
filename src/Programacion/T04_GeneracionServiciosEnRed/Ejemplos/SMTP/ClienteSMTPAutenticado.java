package Programacion.T04_GeneracionServiciosEnRed.Ejemplos.SMTP;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

public class ClienteSMTPAutenticado {

    public static void main(String[] args) {
        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient("TLS"); // Usar TLS

        try {
            int respuesta;

            // Conexión al servidor SMTP
            client.connect("localhost", 25);
            System.out.print(client.getReplyString());
            respuesta = client.getReplyCode();

            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("Conexión rechazada.");
                return;
            }

            // Realizar el saludo EHLO
            client.ehlo("localhost");

            // Autenticación SMTP
            if (!client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, "ruper1903@gmail.com", "axoe pawf wrux bqse")) {
                System.err.println("Autenticación fallida.");
                client.disconnect();
                return;
            }

            // Configuración del mensaje
            String remitente = "postmaster@localhost";
            String destinatario = "ruper.dam1@gmail.com";
            String asunto = "Prueba de SMTP Autenticado";
            String mensaje = "Hola,\nEste es un mensaje de prueba enviado desde Java.\nSaludos.";

            SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destinatario, asunto);

            // Enviar el correo
            client.setSender(remitente);
            client.addRecipient(destinatario);

            var writer = client.sendMessageData();
            if (writer != null) {
                writer.write(cabecera.toString());
                writer.write("\n");
                writer.write(mensaje);
                writer.close();

                if (client.completePendingCommand()) {
                    System.out.println("Mensaje enviado correctamente.");
                } else {
                    System.err.println("Error al finalizar la transacción.");
                }
            } else {
                System.err.println("Error al enviar DATA.");
            }

            // Cerrar sesión y desconectar
            client.logout();
            client.disconnect();

        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException e) {
            System.err.println("Error de conexión o envío.");
            e.printStackTrace();
        }
    }
}
