package ProgramacionServiciosYProcesos.Examen.Ejer4;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client4  {
  public static void main(String[] args) throws Exception {
	String Host = "localhost";
	int Puerto = 65432;//puerto remoto	
	Scanner s = new Scanner(System.in);
	System.out.println("PROGRAMA CLIENTE INICIADO....");
	Socket Cliente = new Socket(Host, Puerto);
	String envio=s.next();
	ArrayList<String> lista =new ArrayList<String>();
	lista.add("Sami");
	lista.add("playstation");
	lista.add("General Z");
	Carta carta = new Carta(lista, true);
	// CREO FLUJO DE SALIDA AL SERVIDOR
	ObjectOutputStream flujoSalida = new
			ObjectOutputStream(Cliente.getOutputStream());
	
	// ENVIO UN SALUDO AL SERVIDOR
	flujoSalida.writeObject(carta);

	// CREO FLUJO DE ENTRADA AL SERVIDOR	
	ObjectInputStream flujoEntrada = new 
            ObjectInputStream(Cliente.getInputStream());

	// EL SERVIDOR ME ENVIA UN MENSAJE
	System.out.println("Recibiendo del SERVIDOR: \n\t" + 
               flujoEntrada.readObject());
	
	// CERRAR STREAMS Y SOCKETS	
	flujoEntrada.close();	
	flujoSalida.close();	
	Cliente.close();	
	s.close();
  }// main
}// 
