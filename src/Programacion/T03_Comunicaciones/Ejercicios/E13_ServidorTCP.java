package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.*;
import java.net.*;

public class E13_ServidorTCP {
    private static final int PUERTO = 11223; // Puerto donde el servidor escucha

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) { // Crea un socket servidor en el puerto especificado
            System.out.println("Servidor TCP escuchando en el puerto " + PUERTO); // Informa que el servidor está activo

            while (true) { // Bucle infinito para aceptar múltiples clientes
                System.out.println("Esperando clientes...");
                Socket cliente = servidor.accept(); // Acepta una conexión entrante de un cliente
                System.out.println("Cliente conectado: " + cliente.getInetAddress()); // Muestra la dirección del cliente conectado

                // Crea un manejador para atender al cliente en un hilo separado
                E13_ManejadorCliente manejador = new E13_ManejadorCliente(cliente); // Inicializa un manejador para el cliente
                Thread hilo = new Thread(manejador); // Crear un hilo para el manejo
                hilo.start(); // Inicia el hilo para atender al cliente
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejar excepciones relacionadas con E/S
        }
    }
}

/*
El servidor se prepara (el restaurante abre)
El servidor crea un ServerSocket en un puerto específico (por ejemplo, el puerto 11223).
El servidor se queda esperando clientes con el metodo .accept(), como si esperara a que alguien toque la puerta.

El cliente se conecta (un cliente entra al restaurante)
El cliente usa un Socket para llamar al servidor en su IP y puerto (como ir a la dirección del restaurante y tocar la puerta).
Cuando el servidor acepta la conexión, se establece un "cable" (el socket) entre ambos.

Se intercambian mensajes
Una vez conectados:
El cliente puede enviar mensajes al servidor (como hacer un pedido).
El servidor recibe el mensaje y lo procesa (como preparar el plato).
El servidor responde al cliente con el resultado (entrega el plato).

Multihilo: El servidor maneja a cada cliente en paralelo
Cuando un cliente se conecta, el servidor crea un nuevo hilo para atenderlo.
Este hilo es independiente y puede manejar los mensajes del cliente sin bloquear a otros.
Mientras este hilo trabaja con el cliente, el servidor principal sigue escuchando y aceptando nuevas conexiones.
 */

