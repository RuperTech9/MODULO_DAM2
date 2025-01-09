package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class E03_TCPClienteNumero {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket cliente = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Flujos de entrada y salida
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            Scanner sc = new Scanner(System.in);

            // Solicita el número al usuario
            System.out.print("Introduce un número: ");
            int numero = sc.nextInt();

            // Enviar el número al servidor
            salida.writeInt(numero);
            System.out.println("Número enviado al servidor: " + numero);

            // Cuadrado del número del servidor
            int cuadrado = entrada.readInt();
            System.out.println("Cuadrado recibido del servidor: " + cuadrado);

            // Cierro conexiones
            entrada.close();
            salida.close();
            sc.close();

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
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

Ejercicio 3: Envío de datos y cálculos
Objetivo:

El cliente envía un número entero al servidor.
El servidor calcula su cuadrado y lo devuelve al cliente.
Explicación:

Se usan flujos para manejar datos primitivos (writeInt y readInt).
El cliente envía el número mediante DataOutputStream.
El servidor recibe el número, calcula el cuadrado y usa DataOutputStream para devolverlo.
El cliente recibe el cuadrado y lo muestra.
Aprendizaje:

Uso de tipos de datos primitivos en flujos.
Cómo estructurar una comunicación donde un cliente solicita un cálculo y el servidor responde.
Práctica: Este modelo puede aplicarse a cualquier servicio que realice cálculos o procese datos enviados por un cliente.
 */