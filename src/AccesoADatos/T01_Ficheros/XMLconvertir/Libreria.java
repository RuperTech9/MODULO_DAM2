package AccesoADatos.T01_Ficheros.XMLconvertir;


import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Libreria {
    private ArrayList<Libro> listaLibros;
    private String nombre;
    private String lugar;

    // Constructor con parámetros
    public Libreria(ArrayList<Libro> listaLibros, String nombre, String lugar) {
        this.listaLibros = listaLibros;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    // Constructor sin parámetros
    public Libreria() {
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    // Wrapper, envoltura alrededor de la representación XML
    @XmlElementWrapper(name = "ListaLibros")
    @XmlElement(name = "Libro")
    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
}