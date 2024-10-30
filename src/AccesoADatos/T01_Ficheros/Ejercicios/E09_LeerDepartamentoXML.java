package AccesoADatos.T01_Ficheros.Ejercicios;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class E09_LeerDepartamentoXML {
    public static void main(String[] args) {
        // Especifica la ruta del archivo XML
        String rutaArchivoXML = "./src//AccesoADatos//T01_Ficheros/DB4O/Departamentos.xml"; // Cambia esta ruta por la correcta

        try {
            // Crear la fábrica y el builder para leer el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Leer el documento XML
            File archivoXML = new File(rutaArchivoXML);
            Document document = builder.parse(archivoXML);
            document.getDocumentElement().normalize(); // Normalizar el XML

            // Obtener el elemento raíz
            System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());

            // Obtener todos los nodos "departamento"
            NodeList listaDepartamentos = document.getElementsByTagName("departamento");

            // Recorrer todos los nodos de departamentos
            for (int i = 0; i < listaDepartamentos.getLength(); i++) {
                Node nodoDepartamento = listaDepartamentos.item(i);

                // Asegurarse de que el nodo es un elemento (para evitar otros tipos de nodos)
                if (nodoDepartamento.getNodeType() == Node.ELEMENT_NODE) {
                    Element departamento = (Element) nodoDepartamento;

                    // Leer y mostrar el número de departamento
                    String numero = departamento.getElementsByTagName("numero").item(0).getTextContent();
                    String nombre = departamento.getElementsByTagName("nombre").item(0).getTextContent();
                    String localidad = departamento.getElementsByTagName("localidad").item(0).getTextContent();

                    // Mostrar los datos del departamento en la consola
                    System.out.printf("Departamento %s: %s, Localidad: %s%n", numero, nombre, localidad);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

