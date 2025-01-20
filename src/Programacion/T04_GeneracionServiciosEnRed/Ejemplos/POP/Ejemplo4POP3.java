package Programacion.T04_GeneracionServiciosEnRed.Ejemplos.POP;

import java.io.BufferedReader;
import java.io.IOException;
import org.apache.commons.net.*;
import org.apache.commons.net.pop3.POP3SClient;
import org.apache.commons.net.pop3.POP3MessageInfo;

public class Ejemplo4POP3 {
	public static void main(String[] args) {
		String server = "localhost", username = "usu1", password = "usu1";
		int puerto = 110;
		
		POP3SClient pop3 = new POP3SClient();
		try {
			//conectamos al servidor
			pop3.connect(server, puerto);
			System.out.println("Conexi�nn realizada al servidor POP3 " + server);
			
			//inicio de sesi�nn
			if (!pop3.login(username,password))
				System.err.println("Error al hacer login");
			else {
				//obtenemos todos los mensajes en un array
				POP3MessageInfo[] men = pop3.listMessages();
				
				if (men == null)
					System.out.println("Imposible recuperar mensajes.");
				else
					System.out.println("N� de mensajes: "+ men.length);
				
				Recuperartodo(men,pop3);
				
				//finalizar sesi�n
				pop3.logout();
			}//else
		
			//desconectamos
			pop3.disconnect();
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}//try-catch
		System.exit(0);
	}//main
	
	
	private static void Recuperartodo (POP3MessageInfo[] men, POP3SClient pop3) throws IOException {
		for (int i=0; i< men.length; i++) {
			System.out.println("Mensaje: " + (i+1));
			POP3MessageInfo msginfo = men[i]; //lista de mensajes
			
			//recupera todo el mensaje
			System.out.println("Mensaje:");
			BufferedReader reader =(BufferedReader) pop3.retrieveMessage(msginfo.number);
			String linea;
			while ((linea = reader.readLine()) !=null)
				System.out.println(linea.toString());
			reader.close();
		}//for
	}//Recuperartodo
	
}//Ejemplo2POP3