package AccesoADatos.T01_Ficheros.TareaFichObjetoCanciones;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class E08_Convertidor {
    public static void main(String[] args) throws IOException {
        String hojaEstilo = "./src//AccesoADatos//T01_Ficheros/TareaFichObjetoCanciones/cancionesPlantilla.xsl";
        String datosAlumnos = "./src//AccesoADatos//T01_Ficheros/TareaFichObjetoCanciones/canciones.xml";
        File pagHTML = new File("./src//AccesoADatos//T01_Ficheros/TareaFichObjetoCanciones/mipagina.html");

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
