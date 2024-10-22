package AccesoADatos.T01_Ficheros;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class E27_LeerEmpleadoXml {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            // Se crea el DocumentBuilder: Un objeto que se usará para construir el documento XML.
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("./src//AccesoADatos//T01_Ficheros/Empleados.xml"));
            document.getDocumentElement().normalize();

            System.out.printf("Elemento raíz: %s %n",
                    document.getDocumentElement().getNodeName());

            // Crea una lista con todos los nodos "empleado"
            NodeList empleados = document.getElementsByTagName("empleado");
            System.out.printf("Nodos empleado a recorrer: %d %n",
                    empleados.getLength());

            // Recorrer la lista de empleados
            for (int i = 0; i < empleados.getLength(); i++) {
                Node empleado = empleados.item(i); // obtener un nodo empleado

                if (empleado.getNodeType() == Node.ELEMENT_NODE) { // tipo de nodo es "ELEMENT_NODE"
                    Element elemento = (Element) empleado;
                    System.out.printf("ID = %s%n",
                            elemento.getElementsByTagName("id").item(0).getTextContent());
                    System.out.printf("Apellido = %s%n",
                            elemento.getElementsByTagName("apellido").item(0).getTextContent());
                    System.out.printf("Departamento = %s%n",
                            elemento.getElementsByTagName("dep").item(0).getTextContent());
                    System.out.printf("Salario = %s%n",
                            elemento.getElementsByTagName("salario").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} // fin de la clase