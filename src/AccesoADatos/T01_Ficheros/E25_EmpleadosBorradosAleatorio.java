package AccesoADatos.T01_Ficheros;

import java.io.*;

public class E25_EmpleadosBorradosAleatorio {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/PruebaAleatorioEmpl.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            long posicion = 0; // Inicializar la posición

            while (posicion < file.length()) {
                file.seek(posicion); // Posicionarse al inicio del registro
                int id = file.readInt(); // Leer el ID

                if (id == 1) { // Verificar si el empleado fue eliminado
                    char[] apellido = new char[10];
                    for (int i = 0; i < 10; i++) {
                        apellido[i] = file.readChar(); // Leer el apellido (que contiene el ID original)
                    }
                    String idOriginal = new String(apellido).trim();
                    System.out.printf("Empleado borrado con ID original: %s\n", idOriginal);
                }

                posicion += 36; // Avanzar al siguiente registro
            }

            file.close(); // Cerrar el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}