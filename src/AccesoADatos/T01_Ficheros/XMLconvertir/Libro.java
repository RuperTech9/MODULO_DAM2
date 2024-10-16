package AccesoADatos.T01_Ficheros.XMLconvertir;

/*import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;*/
import java.io.Serializable;

//@XmlType(propOrder = {"autor", "nombre", "editorial", "isbn"})
public class Libro implements Serializable {
    private String nombre;
    private String autor;
    private String editorial;
    private String isbn;

    // Constructor con parámetros
    public Libro(String nombre, String autor, String editorial, String isbn) {
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
    }

    // Constructor sin parámetros
    public Libro() {
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}