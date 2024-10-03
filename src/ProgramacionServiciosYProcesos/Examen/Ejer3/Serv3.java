package ProgramacionServiciosYProcesos.Examen.Ejer3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Serv3 {
  public static void main(String[] arg) throws IOException {
	  int numeroPuerto=0;
	  Scanner s = new Scanner(System.in);

	nota("Dame un puerto valido");
	numeroPuerto = s.nextInt();// Puerto
	notal(numeroPuerto);
	
	ServerSocket servidor = new ServerSocket(numeroPuerto);
	Socket clienteConectado = null;
	System.out.println("Esperando al cliente.....");
	clienteConectado = servidor.accept();

	// CREO FLUJO DE ENTRADA DEL CLIENTE
	InputStream entrada = null;
	entrada = clienteConectado.getInputStream();
	DataInputStream flujoEntrada = new DataInputStream(entrada);

	// EL CLIENTE ME ENVIA UN MENSAJE
	String texto= flujoEntrada.readUTF();
	System.out.println("Recibiendo del CLIENTE: \n\t" + 
                      texto);
	String reverse = "";
	for(int i = texto.length() -1;i>=0;i--) {
		reverse = reverse + texto.charAt(i);
	}
	nota("Mensaje del cliente transformado: \n\t" + 
            reverse);
	// CREO FLUJO DE SALIDA AL CLIENTE
	OutputStream salida = null;
	salida = clienteConectado.getOutputStream();
	DataOutputStream flujoSalida = new DataOutputStream(salida);

	// ENVIO UN SALUDO AL CLIENTE
	flujoSalida.writeUTF(reverse);

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
