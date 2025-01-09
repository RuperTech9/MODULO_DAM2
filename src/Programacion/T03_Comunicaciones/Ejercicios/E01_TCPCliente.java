package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.IOException;
import java.net.*;

public class E01_TCPCliente {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 6000;

        // Abrir socket
        Socket cliente = new Socket(host, puerto); // El cliente se conecta al servidor

        // Mostrar información de la conexión
        InetAddress direccion = cliente.getInetAddress();
        System.out.println("Puerto local: " + cliente.getLocalPort());
        System.out.println("Puerto remoto: " + cliente.getPort());
        System.out.println("Host remoto: " + direccion.getHostName());
        System.out.println("IP host remoto: " + direccion.getHostAddress());

        // Cerrar conexión
        cliente.close();
    }
}

/*
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
 */