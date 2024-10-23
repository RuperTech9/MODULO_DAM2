package AccesoADatos.T01_Ficheros.XMLconvertir;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class EjemploJAXB_Serializar {
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

        // Creamos el contexto indicando la clase raíz
        JAXBContext context = JAXBContext.newInstance(Libreria.class);
        //Creamos el Marshaller, convertidor de JavaBean en una cadena XML
        Marshaller m = context.createMarshaller();
        //Formateamos el xml para que quede bien
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // Lo visualizamos en consola
        m.marshal(milibreria, System.out);
        // Escribimos en el archivo
        m.marshal(milibreria, new File(MIARCHIVO_XML));
    }
}