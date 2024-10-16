package AccesoADatos.T01_Ficheros.TareaFichObjetoCanciones;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class E05_CrearCancionXml {
    public static void main(String[] args) throws IOException {
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroObjetos/canciones.dat");
        ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(fichero));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Canciones", null);
            document.setXmlVersion("1.0");

            // Leer cada canción del fichero
            while (true) {
                try {
                    E01_Cancion cancion = (E01_Cancion) objIn.readObject();

                    if (cancion.getId() > 0) { // Si el ID es válido (> 0)
                        Element raiz = document.createElement("cancion");
                        document.getDocumentElement().appendChild(raiz);

                        // Añadir ID
                        CrearElemento("id", Integer.toString(cancion.getId()), raiz, document);
                        // Añadir año
                        CrearElemento("anio", Integer.toString(cancion.getAnio()), raiz, document);
                        // Añadir título
                        CrearElemento("titulo", cancion.getTitulo().trim(), raiz, document);
                        // Añadir artista
                        CrearElemento("artista", cancion.getArtista().trim(), raiz, document);
                        // Añadir duración
                        CrearElemento("duracion", cancion.getDuracion().trim(), raiz, document);
                        // Añadir esEspanola
                        CrearElemento("esEspanola", Boolean.toString(cancion.isEsEspanola()), raiz, document);
                    }
                } catch (EOFException eof) {
                    break; // Fin del fichero
                }
            }

            // Guardar el documento XML en un archivo
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroObjetos/canciones.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            System.out.println("Archivo XML de canciones creado correctamente.");

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            objIn.close(); // Cerrar el fichero
        }
    }

    // Método para crear elementos en el XML
    static void CrearElemento(String nombreElemento, String valor, Element raiz, Document document) {
        Element elem = document.createElement(nombreElemento);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }
}