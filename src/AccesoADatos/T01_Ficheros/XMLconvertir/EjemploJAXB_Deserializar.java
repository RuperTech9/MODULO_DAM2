package AccesoADatos.T01_Ficheros.XMLconvertir;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EjemploJAXB_Deserializar {
    private static final String MIARCHIVO_XML = "./src//AccesoADatos//T01_Ficheros/XMLconvertir/Librerias.xml";
    public static void main(String[] args) throws JAXBException, IOException {
        //Se crea la lista de libros
        ArrayList<Libro> librolista = new ArrayList<Libro>();
        // Creamos dos libros y los añadimos
        Libro libro1 = new Libro("Entornos de Desarrollo", "Alicia Ramos", "Garceta", "978-84-1545-297-3");
        librolista.add(libro1);
        Libro libro2 = new Libro("Acceso a Datos", "Maria Jesús Ramos", "Garceta", "978-84-1545-228-7");
        librolista.add(libro2);

        // Se crea la librería y se le asigna la lista de libros
        Libreria milibreria = new Libreria();
        milibreria.setNombre("Prueba de libreria JAXB");
        milibreria.setLugar("Talavera, como no");
        milibreria.setListaLibros(librolista);

        // Visualizamos ahora los datos del documento XML creado
        System.out.println("------------ Leo el XML creado -----------");
        // Se crea Unmarshaller en el contexto de la clase Libreria
        Unmarshaller unmars = JAXBContext.newInstance(Libro.class).createUnmarshaller();

        // Utilizamos el método unmarshal, para obtener datos de un Reader
        Libreria libreria2 = (Libreria) unmars.unmarshal(new FileReader(MIARCHIVO_XML));

        //Recuperamos los datos y visualizamos
        System.out.println("Nombre de libreria: " + libreria2.getNombre());
        System.out.println("Lugar de la libreria: " + libreria2.getLugar());
        System.out.println("Libros de la libreria: ");
    }
}