package AccesoADatos.T01_Ficheros;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class E26_CrearEmpleadoXml {
    public static void main(String args[]) throws IOException {
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/PruebaAleatorioEmpl.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, dep, posicion=0; // para situarnos al principio del fichero
        Double salario;
        char apellido[] = new char[10], aux;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");

            for (;;) {
                file.seek(posicion); // nos posicionamos
                id = file.readInt(); // obtengo id de empleado

                for (int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux;
                }
                String apellidos = new String(apellido);
                dep = file.readInt();
                salario = file.readDouble();

                if (id > 0) { // id válidos a partir de 1
                    Element raiz = document.createElement("empleado"); // nodo empleado
                    document.getDocumentElement().appendChild(raiz);

                    // Añadir ID
                    CrearElemento("id", Integer.toString(id), raiz, document);
                    // Añadir Apellido
                    CrearElemento("apellido", apellidos.trim(), raiz, document);
                    // Añadir DEP
                    CrearElemento("dep", Integer.toString(dep), raiz, document);
                    // Añadir salario
                    CrearElemento("salario", Double.toString(salario), raiz, document);
                }
                posicion += 36; // me posiciono para el siguiente empleado

                if (file.getFilePointer() == file.length()) break;
            }// Fin del for que recorre el fichero

            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("./src//AccesoADatos//T01_Ficheros/Empleados.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        file.close(); // cerrar fichero
    }

    // Insertar los datos del empleado
    static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor); // damos valor
        raiz.appendChild(elem); // pegamos el elemento hijo a la raíz
        elem.appendChild(text); // pegamos el valor
    }
} // fin de la clase