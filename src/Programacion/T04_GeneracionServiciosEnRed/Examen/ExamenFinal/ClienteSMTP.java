package Programacion.T04_GeneracionServiciosEnRed.Examen.ExamenFinal;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;
import java.io.IOException;
import java.io.Writer;

public class ClienteSMTP {
    /**
     * Método para enviar un correo electrónico
     */
    public static void enviarCorreo(String contenidoFTP) {
        SMTPClient clienteSMTP = new SMTPClient();
        String servidorSMTP = "localhost"; // Servidor de correo local
        int puertoSMTP = 25; // Puerto sin negociación TLS
        String remitente = "postmaster@localhost"; // Cuenta Mercury Mail
        String destinatario = "examen2dam2@gmail.com";
        String asunto = "Examen RA4";

        // Para agregar nombre y apellidos al mensaje
        String nombreCompleto = "Alejandro Rupérez López";

        // Creo el mensaje con el contenido del FTP
        String mensajeTexto = "Este es el contenido del directorio 'pub' del servidor FTP:\n\n"
                + contenidoFTP + "\n\n"
                + "Un saludo,\n" + nombreCompleto;

        try {
            // Conectar al servidor SMTP
            clienteSMTP.connect(servidorSMTP, puertoSMTP);

            // Enviar saludo EHLO
            clienteSMTP.sendCommand("EHLO localhost");

            // Configuro remitente y destinatario
            SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destinatario, asunto);
            clienteSMTP.setSender(remitente);
            clienteSMTP.addRecipient(destinatario);

            // Envío el contenido del mensaje
            Writer writer = clienteSMTP.sendMessageData();
            if (writer != null) {
                writer.write(cabecera.toString());
                writer.write("\n" + mensajeTexto);
                writer.close();
                System.out.println(clienteSMTP.completePendingCommand() ? "Correo enviado con exito." : "Error al enviar correo.");
            }

            // Cierro sesión SMTP
            clienteSMTP.logout();
            clienteSMTP.disconnect();

        } catch (IOException e) {
            System.err.println("Error al conectar al servidor SMTP.");
            e.printStackTrace();
        }
    }
}