package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioCanciones;

import java.io.Serializable;

public class E01_Cancion implements Serializable {

    private int id; // 4B
    private int ano; // 4B
    private String titulo; //20 char -> 2B * 20 = 40 B
    private String artista; //20 char -> 2B * 20 = 40 B
    private String duracion; //20 char -> 2B * 20 = 40 B
    private boolean cancion_espanola; // 1B

    // TAMAÑO TOTAL CANCION = 129 Bytes;

    E01_Cancion(int vid, int vano, String vtitulo, String vartista, String vduracion, boolean vcancion_espanola)
    {
        id = vid;
        ano = vano;
        titulo = vtitulo;
        artista = vartista;
        duracion = vduracion;
        cancion_espanola = vcancion_espanola;
    }

    E01_Cancion()
    {
        id = 0;
        ano = 0;
        titulo = "";
        artista = "";
        duracion = "";
        cancion_espanola = false;
    }

    //GETTERS Y SETTERS -

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public boolean isCancionEspanola() {
        return cancion_espanola;
    }

    public void setCancionEspanola(boolean cancion_espanola) {
        this.cancion_espanola = cancion_espanola;
    }

    //

    public String mostrarDatosCancion() {
        return getId() + " - " + getAno() + " - " + getTitulo() + " - " + getArtista() +
                " - " + getDuracion() + " - " + (isCancionEspanola() ? "Española" : "No española");
    }
}