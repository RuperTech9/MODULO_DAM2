package AccesoADatos.T01_Ficheros.Ejercicios;

import java.io.*;

public class E07_CopiarFicheros {
    public static void main(String[] args) {
        String rutaArchivoOrigen = "./src//AccesoADatos//T01_Ficheros/DB4O/Departamentos.dat";
        String rutaArchivoDestino = "./src//AccesoADatos//T01_Ficheros/Objetos/Departamentos.dat";

        File archivoOrigen = new File(rutaArchivoOrigen);
        File archivoDestino = new File(rutaArchivoDestino);

        // Verificar si el archivo origen existe
        if (!archivoOrigen.exists()) {
            System.out.println("El archivo origen no existe.");
            return;
        }


        try (FileInputStream fis = new FileInputStream(archivoOrigen);
             FileOutputStream fos = new FileOutputStream(archivoDestino)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while ((bytesLeidos = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLeidos);
            }

            System.out.println("Archivo copiado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}