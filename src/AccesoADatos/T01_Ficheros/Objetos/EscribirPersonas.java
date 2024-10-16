package AccesoADatos.T01_Ficheros.Objetos;

import java.io.*;
//import com.thoughtworks.xstream.XStream;

public class EscribirPersonas {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*File fichero = new File("PichPersona.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream datais = new ObjectInputStream(filein);
        System.out.println("Comienza el proceso...");

        // Creamos un objeto Lista de Personas
        ListaPersonas listaper = new ListaPersonas();

        try {
            while (true) {
                // Lectura del fichero
                Persona persona = (Persona) datais.readObject();
                listaper.add(persona); // Añadir persona a la lista
            }
        } catch (EOFException eo) {
            // Fin del fichero
        }

        datais.close(); // Cerrar stream de entrada

        try {
            XStream xstream = new XStream();

            // Cambiar de nombre a las etiquetas XML
            xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
            xstream.alias("DatosPersona", Persona.class);
            // Quitar etiqueta lista (atributo de la clase ListaPersonas)
            xstream.addImplicitCollection(ListaPersonas.class, "lista");
            // Insertar los objetos en el XML
            xstream.toXML(listaper,new FileOutputStream("Personas.xml"));
            System.out.println("Creando fichero XML...");

            // Aquí podrías procesar la conversión a XML o alguna otra funcionalidad
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
