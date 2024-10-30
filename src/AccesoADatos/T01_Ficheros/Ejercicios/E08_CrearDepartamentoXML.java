package AccesoADatos.T01_Ficheros.Ejercicios;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class E08_CrearDepartamentoXML {
    public static void main(String[] args) {
        // Ruta del archivo Departamentos.dat
        String rutaArchivoDepartamentos = "./src//AccesoADatos//T01_Ficheros/DB4O/Departamentos.dat"; // Cambia esta ruta a la correcta
        String rutaArchivoXML = "./src//AccesoADatos//T01_Ficheros/DB4O/Departamentos.xml"; // Ruta para guardar el archivo XML

        // Verificar si el archivo existe
        File archivo = new File(rutaArchivoDepartamentos);
        if (!archivo.exists()) {
            System.out.println("El archivo Departamentos.dat no existe.");
            return;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivo))) {
            // Crear el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Crear el elemento raíz "Departamentos"
            Element root = document.createElement("Departamentos");
            document.appendChild(root);

            // Leer y añadir departamentos al XML
            while (dis.available() > 0) {
                int numeroDept = dis.readInt();       // Leer el número del departamento
                String nombreDept = dis.readUTF();    // Leer el nombre del departamento
                String localidadDept = dis.readUTF(); // Leer la localidad del departamento

                // Crear un nuevo elemento "departamento"
                Element departamento = document.createElement("departamento");
                root.appendChild(departamento);

                // Crear subelementos para número, nombre y localidad
                CrearElemento(document, departamento, "numero", Integer.toString(numeroDept));
                CrearElemento(document, departamento, "nombre", nombreDept);
                CrearElemento(document, departamento, "localidad", localidadDept);
            }

            // Guardar el documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Para hacer que el XML sea legible (con indentación)
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(rutaArchivoXML));

            // Transformar el árbol DOM en un archivo XML
            transformer.transform(source, result);
            System.out.println("El archivo XML se ha creado correctamente en: " + rutaArchivoXML);

        } catch (IOException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    // Método para crear un nuevo elemento XML con texto dentro
    private static void CrearElemento(Document document, Element parent, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(textContent));
        parent.appendChild(element);
    }
}
