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

        // Inicialización del parser XML:
        // Se crea una instancia de DocumentBuilderFactory para iniciar el proceso de construcción del documento XML.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            // Creación del documento XML:
            // Se crea un DocumentBuilder para construir el documento XML.
            DocumentBuilder builder = factory.newDocumentBuilder();
            // DOMImplementation se utiliza para crear el documento XML.
            DOMImplementation implementation = builder.getDOMImplementation();
            // Se inicializa el documento con el nodo raíz Empleados.
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");

            // Lectura del archivo de empleados:
            for (;;) {
                file.seek(posicion); // Se posiciona en el archivo en la ubicación actual
                id = file.readInt(); // Lee el ID del empleado

                // Lectura del apellido:
                // Un bucle for lee el apellido carácter por carácter desde el archivo y lo guarda en el arreglo apellido.
                for (int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux;
                }
                String apellidos = new String(apellido);
                dep = file.readInt();
                salario = file.readDouble();

                // Creación de los elementos XML si el ID es válido:
                if (id > 0) { // id válidos a partir de 1 y
                    // Se crea un nodo XML empleado dentro del documento.
                    Element raiz = document.createElement("empleado"); // nodo empleado
                    document.getDocumentElement().appendChild(raiz);

                    // Para cada atributo del empleado, se llama al metodo CrearElemento,
                    // Añadir ID
                    CrearElemento("id", Integer.toString(id), raiz, document);
                    // Añadir Apellido
                    CrearElemento("apellido", apellidos.trim(), raiz, document);
                    // Añadir DEP
                    CrearElemento("dep", Integer.toString(dep), raiz, document);
                    // Añadir salario
                    CrearElemento("salario", Double.toString(salario), raiz, document);
                }
                // Actualización de la posición y finalización del bucle:
                posicion += 36; // me posiciono para el siguiente empleado
                if (file.getFilePointer() == file.length()) break; // se comprueba si se ha llegado al final del archivo para romper el bucle.
            }// Fin del for que recorre el fichero

            // Escritura del documento XML:
            // Se convierte el objeto document a un archivo XML utilizando un Transformer y se guarda en la ubicación especificada.
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("./src//AccesoADatos//T01_Ficheros/Empleados.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        // Manejo de excepciones y cierre del archivo:
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        file.close(); // cerrar fichero
    }

    // Insertar los datos del empleado
    // Este metodo crea un elemento XML (por ejemplo, "id" o "apellido") y lo agrega como hijo del nodo raíz (empleado),
    // junto con el valor correspondiente (por ejemplo, el ID o el apellido).
    static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor); // damos valor
        raiz.appendChild(elem); // pegamos el elemento hijo a la raíz
        elem.appendChild(text); // pegamos el valor
    }
} // fin de la clase

/*
Este código genera un archivo XML con los datos de los empleados a partir de un archivo binario.
El archivo XML se estructura de tal manera que cada empleado tiene nodos hijos que contienen su ID, apellido, departamento y salario.
 */