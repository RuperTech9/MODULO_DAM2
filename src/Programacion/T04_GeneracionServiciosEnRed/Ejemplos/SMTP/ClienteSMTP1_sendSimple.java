package Programacion.T04_GeneracionServiciosEnRed.Ejemplos.SMTP;

import java.io.IOException;
import org.apache.commons.net.smtp.*;

public class ClienteSMTP1_sendSimple {
  public static void main(String[] args) {
	SMTPClient client = new SMTPClient();
	try {
	    int respuesta;
	    //NOS CONECTAMOS AL PUERTO 25
          client.connect("localhost");	    
	    System.out.print(client.getReplyString());
	    respuesta = client.getReplyCode();
		
	    if (!SMTPReply.isPositiveCompletion(respuesta)) {
		client.disconnect();
		System.err.println("CONEXION RECHAZADA.");
		System.exit(1);
	    }
			
	    // REALIZAR ACCIONES, por ejemplo enviar un correo
	    client.login();
	    String destinatario = "usuario@correo.com";
	    String mensaje = "Hola, \n Mensaje de prueba. \n Saludos.";
	    String remitente = "yo@localhost.com";
	    
	    if (client.sendSimpleMessage (remitente, destinatario, mensaje))
	    	System.out.println("Mensaje enviado a " + destinatario);
	    else
	    	System.out.println("No se ha podido enviar el mensaje");
	    
	    client.logout();
		
	    // NOS DESCONECTAMOS
	    client.disconnect();
		
	} catch (IOException e) {
	   System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");		   
	   e.printStackTrace();
	   System.exit(2);
	}		
		
  }//main
}// ..


