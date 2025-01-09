package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.IOException;
import java.net.*;

public class E01_TCPServidor {
    public static void main(String[] args) throws IOException {
        int puerto = 6000;
        ServerSocket servidor = new ServerSocket(puerto); // El servidor se establece en el puerto 6000
        // Este objeto ServerSocket escucha las solicitudes de conexión de los clientes en el puerto especificado.
        System.out.println("Servidor escuchando en el puerto " + servidor.getLocalPort());

        // Espera al primer cliente que acepte
        Socket cliente1 = servidor.accept();
        System.out.println("Cliente 1 conectado");
        System.out.println("Puerto local: " + cliente1.getLocalPort());
        System.out.println("Puerto remoto: " + cliente1.getPort());

        // Espera al segundo cliente que acepte
        Socket cliente2 = servidor.accept();
        System.out.println("Cliente 2 conectado");
        System.out.println("Puerto local: " + cliente2.getLocalPort());
        System.out.println("Puerto remoto: " + cliente2.getPort());

        // Cerrar conexiones
        cliente1.close();
        cliente2.close();
        servidor.close();
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