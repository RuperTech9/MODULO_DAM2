package Programacion.T03_Comunicaciones.Ejercicios;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class E07_MulticastServidor {
    private static final String MULTICAST_IP = "225.0.0.1";
    private static final int PUERTO = 6000;
    private static MulticastSocket socket;
    private static InetAddress grupo;

    public static void main(String[] args) {
        // Configuración del JFrame
        JFrame frame = new JFrame("Servidor Multicast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Campo de texto para escribir el mensaje
        JTextField textField = new JTextField();
        textField.setBounds(20, 20, 240, 30);
        frame.add(textField);

        // Botón para enviar el mensaje
        JButton enviarButton = new JButton("Enviar");
        enviarButton.setBounds(270, 20, 100, 30);
        frame.add(enviarButton);

        // Área de texto para mostrar mensajes enviados
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 60, 350, 150);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Visualización de mensajes enviados"));
        frame.add(scrollPane);

        // Botón para salir
        JButton salirButton = new JButton("Salir");
        salirButton.setBounds(270, 220, 100, 30);
        frame.add(salirButton);

        frame.setVisible(true);

        try {
            socket = new MulticastSocket();
            grupo = InetAddress.getByName(MULTICAST_IP);

            // Acción del botón "Enviar"
            enviarButton.addActionListener(e -> {
                String mensaje = textField.getText();
                if (!mensaje.isEmpty()) {
                    try {
                        byte[] buffer = mensaje.getBytes();
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, PUERTO);
                        socket.send(paquete);

                        textArea.append("Mensaje enviado: " + mensaje + "\n");
                        textField.setText("");
                    } catch (IOException ex) {
                        textArea.append("Error al enviar mensaje: " + ex.getMessage() + "\n");
                    }
                }
            });

            // Acción del botón "Salir"
            salirButton.addActionListener(e -> {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (Exception ex) {
                    textArea.append("Error al cerrar el socket: " + ex.getMessage() + "\n");
                }
                frame.dispose();
                System.exit(0);
            });

        } catch (IOException ex) {
            textArea.append("Error al iniciar el servidor: " + ex.getMessage() + "\n");
        }
    }
}
