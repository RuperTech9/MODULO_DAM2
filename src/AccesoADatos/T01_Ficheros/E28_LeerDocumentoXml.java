package AccesoADatos.T01_Ficheros;

import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class E28_LeerDocumentoXml {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            // Cambia la URL por la del fichero XML que desees leer
            URL url = new URL("https://www.w3schools.com/xml/plant_catalog.xml"); // Ejemplo con un XML desde W3Schools
            InputStream inputStream = url.openStream(); // Abrir el archivo XML desde Internet

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream); // Parsear el contenido del InputStream
            document.getDocumentElement().normalize();

            System.out.printf("Elemento raíz: %s %n", document.getDocumentElement().getNodeName());

            // Crear una lista con todos los nodos de interés (ejemplo: "PLANT")
            NodeList nodos = document.getElementsByTagName("PLANT"); // Cambia "PLANT" por la etiqueta que desees leer
            System.out.printf("Nodos a recorrer: %d %n", nodos.getLength());

            // Recorrer la lista de nodos
            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i); // obtener un nodo

                if (nodo.getNodeType() == Node.ELEMENT_NODE) { // Verificar si es un nodo de tipo ELEMENT_NODE
                    Element elemento = (Element) nodo;

                    // Lee diferentes elementos dentro de "PLANT" (ejemplo: COMMON, BOTANICAL, ZONE, PRICE, etc.)
                    System.out.printf("Common = %s%n", elemento.getElementsByTagName("COMMON").item(0).getTextContent());
                    System.out.printf("Botanical = %s%n", elemento.getElementsByTagName("BOTANICAL").item(0).getTextContent());
                    System.out.printf("Zone = %s%n", elemento.getElementsByTagName("ZONE").item(0).getTextContent());
                    System.out.printf("Price = %s%n", elemento.getElementsByTagName("PRICE").item(0).getTextContent());
                }
            }

            inputStream.close(); // Cerrar el InputStream
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
Lectura desde URL:

Se usa URL url = new URL("https://example.com/archivo.xml"); para especificar la ubicación del archivo XML en la web.
Luego se abre un InputStream con url.openStream(), que permite leer el contenido del archivo XML desde la URL.
Lectura de etiquetas específicas:

He cambiado la etiqueta de "empleado" por "PLANT" porque estoy utilizando un XML público de W3Schools como ejemplo (https://www.w3schools.com/xml/plant_catalog.xml).
Dentro del bucle, se lee el contenido de diferentes elementos dentro de "PLANT" como "COMMON", "BOTANICAL", "ZONE" y "PRICE". Esto es solo un ejemplo, y puedes cambiar las etiquetas según el XML que estés leyendo.
Normalización del documento:

Se utiliza document.getDocumentElement().normalize(); para asegurarse de que la estructura del XML esté correctamente interpretada.
Cerrar el InputStream:

Es importante cerrar el InputStream después de leer el contenido del archivo con inputStream.close(); para liberar recursos.
 */