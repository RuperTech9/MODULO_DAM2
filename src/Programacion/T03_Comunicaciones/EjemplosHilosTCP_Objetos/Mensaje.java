package Programacion.T03_Comunicaciones.EjemplosHilosTCP_Objetos;

import java.io.Serializable;

public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;

    private String comando; // Comando para procesar (e.g., "invertir", "mayusculas")
    private String contenido; // Contenido del mensaje
    private String respuesta; // Respuesta procesada (opcional)

    // Constructor
    public Mensaje(String comando, String contenido) {
        this.comando = comando;
        this.contenido = contenido;
    }

    // Getters y Setters
    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "comando='" + comando + '\'' +
                ", contenido='" + contenido + '\'' +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }
}
