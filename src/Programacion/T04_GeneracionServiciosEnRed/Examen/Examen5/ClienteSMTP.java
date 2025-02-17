package Programacion.T04_GeneracionServiciosEnRed.Examen.Examen5;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ClienteSMTP {
    // Definición del servidor SMTP y configuración
    private final String servidorSMTP = "localhost"; // Servidor SMTP (Mercury Mail corriendo en localhost)
    private final int puertoSMTP = 25; // Puerto estándar SMTP sin cifrado (para Mercury)
    private final String remitente = "postmaster@localhost"; // Dirección del remitente (configurado en Mercury Mail)

    /**
     * Método para enviar un correo electrónico con la opción de adjuntar un archivo.
     *
     * @param sc Scanner para la entrada del usuario.
     * @param archivoAdjunto Ruta del archivo adjunto (opcional).
     */
    public void enviarCorreo(Scanner sc, String archivoAdjunto) {
        // Creación del cliente SMTP con soporte TLS
        AuthenticatingSMTPClient clienteSMTP = new AuthenticatingSMTPClient("TLS");

        // Solicitar datos del correo al usuario
        System.out.print("Introduce el destinatario del correo: ");
        String destinatario = sc.nextLine();
        System.out.print("Introduce el asunto del correo: ");
        String asunto = sc.nextLine();
        System.out.print("Introduce el mensaje del correo: ");
        String mensajeTexto = sc.nextLine();

        try {
            // Conectar al servidor SMTP
            clienteSMTP.connect(servidorSMTP, puertoSMTP);
            System.out.println("Conectado al servidor SMTP: " + clienteSMTP.getReplyString());

            // Enviar comando EHLO al servidor SMTP para identificar el cliente
            clienteSMTP.ehlo("localhost");
            System.out.println("Respuesta EHLO: " + clienteSMTP.getReplyString());

            // Configurar las cabeceras del mensaje con remitente, destinatario y asunto
            SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destinatario, asunto);
            clienteSMTP.setSender(remitente); // Configurar el remitente
            clienteSMTP.addRecipient(destinatario); // Agregar destinatario

            // Enviar el contenido del mensaje
            Writer writer = clienteSMTP.sendMessageData();
            if (writer != null) {
                writer.write(cabecera.toString()); // Escribir las cabeceras
                writer.write("\n" + mensajeTexto); // Escribir el cuerpo del mensaje

                // Si hay un archivo adjunto, agregarlo al mensaje
                if (archivoAdjunto != null) {
                    writer.write("\n\nArchivo adjunto: " + archivoAdjunto);
                }

                writer.close(); // Cerrar la escritura del mensaje

                // Confirmar el envío del mensaje
                System.out.println(clienteSMTP.completePendingCommand() ? "Correo enviado con éxito." : "Error al enviar el correo.");
            }

            // Cerrar sesión y desconectar el cliente SMTP
            clienteSMTP.logout();
            clienteSMTP.disconnect();
        } catch (IOException e) {
            System.err.println("Error al conectar al servidor SMTP.");
            e.printStackTrace();
        }
    }
}

