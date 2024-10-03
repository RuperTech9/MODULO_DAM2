package ProgramacionServiciosYProcesos.Examen.Ejer4;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serv4 {
  public static void main(String[] arg) throws IOException {
	  
	  Scanner s = new Scanner(System.in);

	
	int numeroPuerto = 65432;// Puerto
	
	
	ServerSocket servidor = new ServerSocket(numeroPuerto);
	Socket clienteConectado = null;
	System.out.println("Esperando al cliente.....");
	clienteConectado = servidor.accept();

	// CREO FLUJO DE ENTRADA DEL CLIENTE
	InputStream entrada = null;
	entrada = clienteConectado.getInputStream();
	ObjectInputStream flujoEntrada = new ObjectInputStream(entrada);

	// EL CLIENTE ME ENVIA UN MENSAJE
	Carta texto= (Carta) flujoEntrada.readObject();
	System.out.println("Recibiendo del CLIENTE: \n\t" + 
                      texto);
	String Carta = 
	// CREO FLUJO DE SALIDA AL CLIENTE
	ObjectOutputStream flujoSalida = new ObjectOutputStream(clienteConectado.getOutputStream());

	// ENVIO UN SALUDO AL CLIENTE
	flujoSalida.writeObjecrt(Carta);

	// CERRAR STREAMS Y SOCKETS
	entrada.close();
	flujoEntrada.close();
	salida.close();
	flujoSalida.close();
	clienteConectado.close();
	servidor.close();
  }// main
  private static void nota(String mensaje) {
		System.out.println(mensaje);
		}
  private static void notal(long mensaje) {
		System.out.println(mensaje);
		}

}// fin
