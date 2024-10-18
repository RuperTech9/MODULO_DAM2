package AccesoADatos.T01_Ficheros.XMLconvertir;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Definir la raíz del XML como <MISLIBRERIAS>
@XmlRootElement(name = "MISLIBRERIAS")
public class Librerias {
    private ArrayList<Libreria> listaLibrerias;

    // Constructor con parámetros
    public Librerias(ArrayList<Libreria> listaLibrerias) {
        this.listaLibrerias = listaLibrerias;
    }

    // Constructor sin parámetros
    public Librerias() {
        this.listaLibrerias = new ArrayList<>();
    }

    // Getter y Setter para la lista de librerías
    @XmlElement(name = "Libreria")
    public ArrayList<Libreria> getListaLibrerias() {
        return listaLibrerias;
    }

    public void setListaLibrerias(ArrayList<Libreria> listaLibrerias) {
        this.listaLibrerias = listaLibrerias;
    }

    public static void main(String[] args) {
        // Crear libros
        Libro libro1 = new Libro("Libro1", "Autor1", "Editorial1", "1111");
        Libro libro2 = new Libro("Libro2", "Autor2", "Editorial2", "2222");
        Libro libro3 = new Libro("Libro3", "Autor3", "Editorial3", "3333");
        Libro libro4 = new Libro("Libro4", "Autor4", "Editorial4", "4444");

        // Crear listas de libros para cada librería
        ArrayList<Libro> listaLibros1 = new ArrayList<>();
        listaLibros1.add(libro1);
        listaLibros1.add(libro2);

        ArrayList<Libro> listaLibros2 = new ArrayList<>();
        listaLibros2.add(libro3);
        listaLibros2.add(libro4);

        // Crear librerías
        Libreria libreria1 = new Libreria(listaLibros1, "Libreria1", "Ciudad1");
        Libreria libreria2 = new Libreria(listaLibros2, "Libreria2", "Ciudad2");

        // Crear lista de librerías
        ArrayList<Libreria> listaLibrerias = new ArrayList<>();
        listaLibrerias.add(libreria1);
        listaLibrerias.add(libreria2);

        // Crear objeto Librerias
        Librerias librerias = new Librerias(listaLibrerias);

        //Crear archivo XML
        try {
            // Crear contexto JAXB
            JAXBContext context = JAXBContext.newInstance(Librerias.class);

            // Crear un Marshaller
            Marshaller marshaller = context.createMarshaller();

            // Formatear la salida para que esté legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Escribir el archivo XML
            marshaller.marshal(librerias, new File("./src//AccesoADatos//T01_Ficheros/XMLconvertir/Librerias.xml"));

            System.out.println("Archivo Librerias.xml generado correctamente.");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}