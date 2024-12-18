package AccesoADatos.T01_Ficheros.XMLconvertir;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class E29_Convertidor {
    public static void main(String[] args) throws IOException {
        String hojaEstilo = "./src//AccesoADatos//T01_Ficheros/XMLconvertir/alumnosPlantilla.xsl";
        String datosAlumnos = "./src//AccesoADatos//T01_Ficheros/XMLconvertir/alumnos.xml";
        File pagHTML = new File("./src//AccesoADatos//T01_Ficheros/XMLconvertir/mipagina.html");

        // Crear fichero HTML
        FileOutputStream os = new FileOutputStream(pagHTML);
        Source estilos = new StreamSource(hojaEstilo); // fuente XSL
        Source datos = new StreamSource(datosAlumnos); // fuente XML
        Result result = new StreamResult(os); // resultado de la transformación

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, result); // obtiene el HTML
        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            os.close(); // cerrar fichero
        }
    }
}
