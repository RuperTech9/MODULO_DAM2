package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E02_TCPServidorMayusculas {
    public static void main(String[] args) {
        int puerto = 6000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Espera a que se conecte el cliente
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");

            Scanner sc = new Scanner(System.in);

            // Flujos de entrada y salida
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());

            // Leer mensaje del usuario y enviarlo en mayúsculas
            System.out.print("Escribe el mensaje en mayúsculas para enviar al cliente: ");
            String mensaje = sc.nextLine().toUpperCase(); // Convertir a mayúsculas por si el usuario escribe en minúsculas
            System.out.println("Envío mensaje en mayúsculas: " + mensaje);
            salida.writeUTF(mensaje);

            // Recibir el mensaje en minúsculas del cliente
            String respuestaCliente = entrada.readUTF();
            System.out.println("Mensaje recibido en minúsculas del cliente: " + respuestaCliente);

            // Cerrar conexiones
            entrada.close();
            salida.close();
            cliente.close();
            System.out.println("Conexión cerrada");

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

/*

A continuación, te explico detalladamente cada uno de los ejercicios mencionados:

Ejercicio 1: Conexión TCP básica
Objetivo:

Crear un servidor que acepte conexiones de dos clientes de manera secuencial.
Mostrar los puertos locales y remotos de cada cliente en el servidor.
El cliente debe mostrar información de la conexión (puertos y dirección IP del servidor).
Explicación:

Se usa ServerSocket para que el servidor escuche conexiones en un puerto específico.
Con el método accept(), el servidor espera y acepta conexiones de los clientes.
El objeto Socket obtenido de accept() se utiliza para identificar los detalles del cliente.
Por su parte, el cliente usa la clase Socket para conectarse al servidor.
Aprendizaje:

Entender cómo funciona la creación de sockets TCP.
Diferenciar entre puertos locales (puerto del cliente/servidor) y remotos (puerto del otro extremo).
Captar el flujo básico: el servidor escucha, el cliente se conecta.
Práctica: Esto es útil para iniciar cualquier comunicación en red, ya que forma la base de cualquier sistema cliente-servidor.

Ejercicio 2: Comunicación bidireccional TCP
Objetivo:

El servidor envía un mensaje en mayúsculas al cliente.
El cliente transforma el mensaje a minúsculas y lo envía de vuelta al servidor.
Explicación:

Se introducen los flujos de entrada y salida:
InputStream para leer datos.
OutputStream para escribir datos.
El servidor envía un mensaje usando writeUTF() (cadena en UTF).
El cliente recibe ese mensaje, lo convierte a minúsculas y lo devuelve usando los mismos flujos.
Aprendizaje:

Uso de flujos para transmitir datos bidireccionalmente entre cliente y servidor.
Manipulación de cadenas recibidas y enviadas.
Estructura básica de interacción entre cliente y servidor.
Práctica: Este enfoque es clave para sistemas interactivos que requieren respuestas personalizadas, como aplicaciones de preguntas y respuestas.
 */