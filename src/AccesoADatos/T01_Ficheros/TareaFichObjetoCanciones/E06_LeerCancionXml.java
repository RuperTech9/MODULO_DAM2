package AccesoADatos.T01_Ficheros.TareaFichObjetoCanciones;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class E06_LeerCancionXml {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("./src//AccesoADatos//T01_Ficheros/TareaFichObjetoCanciones/canciones.xml"));
            document.getDocumentElement().normalize();

            System.out.printf("Elemento raíz: %s %n", document.getDocumentElement().getNodeName());

            // Crea una lista con todos los nodos "cancion"
            NodeList canciones = document.getElementsByTagName("cancion");
            System.out.printf("Nodos canción a recorrer: %d %n", canciones.getLength());

            // Recorrer la lista de canciones
            for (int i = 0; i < canciones.getLength(); i++) {
                Node cancion = canciones.item(i); // obtener un nodo cancion

                if (cancion.getNodeType() == Node.ELEMENT_NODE) { // tipo de nodo es "ELEMENT_NODE"
                    Element elemento = (Element) cancion;
                    System.out.printf("ID = %s%n", elemento.getElementsByTagName("id").item(0).getTextContent());
                    System.out.printf("Año = %s%n", elemento.getElementsByTagName("anio").item(0).getTextContent());
                    System.out.printf("Título = %s%n", elemento.getElementsByTagName("titulo").item(0).getTextContent());
                    System.out.printf("Artista = %s%n", elemento.getElementsByTagName("artista").item(0).getTextContent());
                    System.out.printf("Duración = %s%n", elemento.getElementsByTagName("duracion").item(0).getTextContent());
                    System.out.printf("Es Española = %s%n", elemento.getElementsByTagName("esEspanola").item(0).getTextContent());
                    System.out.println("----------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}