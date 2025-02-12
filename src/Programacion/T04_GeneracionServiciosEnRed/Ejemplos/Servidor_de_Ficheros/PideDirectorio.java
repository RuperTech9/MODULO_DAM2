package Programacion.T04_GeneracionServiciosEnRed.Ejemplos.Servidor_de_Ficheros;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PideDirectorio implements Serializable {
    private String path;

    public PideDirectorio(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}