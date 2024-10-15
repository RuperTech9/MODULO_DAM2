package AccesoADatos.T01_Ficheros.TareaFicheroBinarioAleatorioCanciones;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class CrearCanciones1Xml {
    public static void main(String[] args) throws IOException {
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroBinarioAleatorioCanciones/Canciones1.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, anio, posicion = 0; // para situarnos al principio del fichero
        boolean esEspanola;
        char[] titulo = new char[20], artista = new char[20], duracion = new char[20];
        char aux;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Canciones", null);
            document.setXmlVersion("1.0");

            while (posicion < file.length()) {
                file.seek(posicion); // nos posicionamos al inicio del registro

                id = file.readInt(); // Leer ID de la canción
                anio = file.readInt(); // Leer el año de la canción

                // Leer título
                for (int i = 0; i < titulo.length; i++) {
                    aux = file.readChar();
                    titulo[i] = aux;
                }
                String tituloStr = limpiarTexto(new String(titulo));

                // Leer artista
                for (int i = 0; i < artista.length; i++) {
                    aux = file.readChar();
                    artista[i] = aux;
                }
                String artistaStr = limpiarTexto(new String(artista));

                // Leer duración
                for (int i = 0; i < duracion.length; i++) {
                    aux = file.readChar();
                    duracion[i] = aux;
                }
                String duracionStr = limpiarTexto(new String(duracion));

                esEspanola = file.readBoolean(); // Leer si es española

                // Crear elemento "cancion" en el XML solo si el ID es válido (> 0)
                if (id > 0) {
                    Element raiz = document.createElement("cancion");
                    document.getDocumentElement().appendChild(raiz);

                    // Añadir los campos al nodo "cancion"
                    CrearElemento("id", Integer.toString(id), raiz, document);
                    CrearElemento("anio", Integer.toString(anio), raiz, document);
                    CrearElemento("titulo", tituloStr, raiz, document);
                    CrearElemento("artista", artistaStr, raiz, document);
                    CrearElemento("duracion", duracionStr, raiz, document);
                    CrearElemento("esEspanola", Boolean.toString(esEspanola), raiz, document);
                }

                posicion += 129; // Avanzar al siguiente registro (129 bytes por canción)
            }

            // Guardar el árbol de nodos en un archivo XML
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("Canciones1.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            System.out.println("Archivo XML 'Canciones1.xml' creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file.close(); // Cerrar el fichero
        }
    }

    // Método para crear elementos en el XML
    static void CrearElemento(String nombreElemento, String valor, Element raiz, Document document) {
        Element elem = document.createElement(nombreElemento);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

    // Método para limpiar el texto, eliminando caracteres no válidos o nulos
    static String limpiarTexto(String texto) {
        return texto.replaceAll("\u0000", "").trim();
    }
}