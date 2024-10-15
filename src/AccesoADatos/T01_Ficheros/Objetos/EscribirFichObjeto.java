package AccesoADatos.T01_Ficheros.Objetos;

import java.io.*;

public class EscribirFichObjeto {
    public static void main(String[] args) throws IOException {
        Persona persona = new Persona();

        File fichero = new File("./src//AccesoADatos//T01_Ficheros/Objetos/PruebaPersonas.dat");

        FileOutputStream fileOut = new FileOutputStream(fichero);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileOut);

        String nombres[] = {"Ana","Miguel","Alicia","Pedro"};
        int edades[] = {1,2,3,4};

        for (int i = 0; i < edades.length; i++) { // Recorro los arrays
            persona = new Persona(nombres[i],edades[i]);
            dataOS.writeObject(persona); // Escribo la persona en el fichero
        }
        System.out.println("Fichero escrito correctamente\n");
        dataOS.close();
        fileOut.close();

        // Lectura de objetos Persona desde el fichero y mostrar datos
        FileInputStream fileIn = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(fileIn);

        try {
            while (true) {
                persona = (Persona) dataIS.readObject(); // Leo un objeto Persona del fichero
                System.out.println(persona.toString()); // Muestro los datos usando el toString()
            }
        } catch (EOFException e) {
            // Fin del archivo alcanzado, salimos del bucle
            System.out.println("Fin de la lectura del fichero.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            dataIS.close();
            fileIn.close();
        }
    }
}
