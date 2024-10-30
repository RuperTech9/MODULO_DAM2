package AccesoADatos.T01_Ficheros.Ejercicios;

import java.io.*;

/*
 * BOOLEAN Crear un fichero con una lista de personas, puede ser el array del ejer anterior, pero queremos añadir un booleano que especifique si esa persona va a acudir a una reunion o no
 * Falso que no va a asistir, Verdadero que va a acudir. Array asociado a cada persona, y que segun elijan me cree una lista de asistentes y otra lista de no asistentes
 */

public class EjercicioProfe3_PersonasReunion {

	public static void main(String[] args) throws IOException {
        // Archivo donde se guardarán los datos
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/DB4O/PruebaProfe3_personasReunion.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);
        DataOutputStream dataOut = new DataOutputStream(fileOut);

        // Datos de las personas
        String[] nombres = {"Ana", "Luis", "Alicia", "Pedro", "Manuel", "Andrés"};
        int[] edades = {14, 15, 13, 14, 16, 15};
        boolean[] asistencia = {true, true, true, true, false, true};

        // Escribir los datos en el archivo
        for (int i = 0; i < nombres.length; i++) {
        	dataOut.writeUTF(nombres[i]); // Escribimos el nombre
        	dataOut.writeInt(edades[i]);  // Escribimos la edad
        	dataOut.writeBoolean(asistencia[i]); // Escribimos si va a asistir
        }

        dataOut.close(); // Cerramos el flujo de datos

        // Lectura de los datos desde el archivo
        FileInputStream fileIn = new FileInputStream(fichero);
        DataInputStream dataIn = new DataInputStream(fileIn);

        // Listas para almacenar asistentes y no asistentes
        StringBuilder asistentes = new StringBuilder("Asistentes a la reunión:\n");
        StringBuilder noAsistentes = new StringBuilder("No asistentes a la reunión:\n");

        try {
            while (true) { // Leemos todos los datos hasta el final del archivo
                String nombre = dataIn.readUTF();
                int edad = dataIn.readInt();
                boolean vaAsistir = dataIn.readBoolean();

                // Clasificamos a la persona según su asistencia
                if (vaAsistir) {
                    asistentes.append(nombre).append(" (Edad: ").append(edad).append(")\n");
                } else {
                    noAsistentes.append(nombre + "\n");
                }
            }
        } catch (IOException e) {
            // Se alcanza el final del archivo
        } finally {
        	dataIn.close(); // Cerramos el flujo de datos
        }
        // Mostrar listas de asistentes y no asistentes
        System.out.println(asistentes);
        System.out.println(noAsistentes);
    }
}