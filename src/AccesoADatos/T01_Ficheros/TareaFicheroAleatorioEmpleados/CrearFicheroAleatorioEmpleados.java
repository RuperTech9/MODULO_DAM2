package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioEmpleados;

import java.io.*;

public class CrearFicheroAleatorioEmpleados {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioEmpleados/EmpleadosAleatorio.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            // Datos predefinidos
            String[] apellidos = {"GARCIA", "PEREZ", "MARTINEZ", "GONZALEZ", "RODRIGUEZ"};
            int[] departamentos = {10, 20, 30, 40, 50};
            double[] salarios = {2000.00, 1500.50, 1800.75, 2100.90, 2200.00};

            StringBuffer buffer;
            int n = apellidos.length;

            for (int i = 0; i < n; i++) {
                file.writeInt(i + 1); // ID del empleado
                buffer = new StringBuffer(apellidos[i]);
                buffer.setLength(10); // Rellenamos el apellido a 10 caracteres
                file.writeChars(buffer.toString());
                file.writeInt(departamentos[i]);
                file.writeDouble(salarios[i]);
            }

            file.close();
            System.out.println("Fichero de empleados creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}