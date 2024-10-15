package AccesoADatos.T01_Ficheros.Ejercicios;

import java.io.File;

public class E06_EliminarDirectorio {
    public static void main(String[] args) {
        // Define aquí directamente la ruta del directorio a eliminar
        String nombreDirectorio = "./src//AccesoADatos//T01_Ficheros/DIRECTORIO"; // Cambia esta ruta por la que desees eliminar

        File directorio = new File(nombreDirectorio);

        if (!directorio.exists()) {
            System.out.println("El directorio no existe.");
            return;
        }

        eliminarDirectorio(directorio);
        System.out.println("Directorio y sus ficheros eliminados correctamente.");
    }

    // Método para eliminar el directorio y sus contenidos
    private static void eliminarDirectorio(File directorio) {
        File[] ficheros = directorio.listFiles();

        if (ficheros != null) {
            for (File fichero : ficheros) {
                if (fichero.isDirectory()) {
                    eliminarDirectorio(fichero); // Llamada recursiva para eliminar subdirectorios
                } else {
                    fichero.delete(); // Eliminar fichero
                }
            }
        }
        directorio.delete(); // Finalmente eliminar el directorio
    }
}