package AccesoADatos.T01_Ficheros.TareaFicheroObjetos;
/*
    Clase Cancion2 Serializable
    identificador, año, titulo, artista, duración y canción española (verdadero/falso)
    Constructor y constructo a 0 o null, o falso.
    y metodo mostrar cancion
 */

import java.io.Serializable;

public class E01_Cancion implements Serializable {
    private int id;
    private int anio;
    private String titulo;
    private String artista;
    private String duracion;
    private boolean esEspanola;

    public E01_Cancion() {
        this.id = 0;
        this.anio = 0;
        this.titulo = "";
        this.artista = "";
        this.duracion = "";
        this.esEspanola = false;
    }

    public E01_Cancion(int id, int anio, String titulo, String artista, String duracion, boolean esEspanola) {
        this.id = id;
        this.anio = anio;
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.esEspanola = esEspanola;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public int getAnio() {
        return anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public String getDuracion() {
        return duracion;
    }

    public boolean isEsEspanola() {
        return esEspanola;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Título: %s, Artista: %s, Año: %d, Duración: %s, Española: %s",
                id, titulo, artista, anio, duracion, esEspanola ? "Sí" : "No");
    }
}