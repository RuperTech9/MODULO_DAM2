package Programacion.T03_Comunicaciones.Ejercicios;

import java.io.Serializable;

public class Curso implements Serializable {
    private String id;
    private String descripcion;

    // Constructor sin parámetros
    public Curso() {
    }

    // Constructor con parámetros
    public Curso(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Curso [ID: " + id + ", Descripción: " + descripcion + "]";
    }
}
