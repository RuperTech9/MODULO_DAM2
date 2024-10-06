package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioEmpleados;

import java.io.*;

public class AñadirEmpleado {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioEmpleados/EmpleadosAleatorio.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            long posicion = 0;
            int ultimoId = 0;

            // Recorremos el archivo para encontrar el último ID válido
            while (posicion < file.length()) {
                file.seek(posicion);
                int id = file.readInt(); // Leemos el ID
                file.skipBytes(10 * 2 + 4 + 8); // Saltamos el apellido, departamento y salario

                // Actualizamos el último ID solo si es válido (mayor que 0)
                if (id > 0) {
                    ultimoId = id;
                }

                posicion += 36; // Cada empleado ocupa 36 bytes
            }

            // Nuevo ID es uno más que el último ID válido
            int nuevoId = ultimoId + 1;
            String apellido = "RAMIREZ"; // Nuevo apellido
            int departamento = 50; // Nuevo departamento
            double salario = 2700.50; // Nuevo salario

            // Escribimos los datos del nuevo empleado
            file.seek(file.length()); // Nos posicionamos al final del archivo
            file.writeInt(nuevoId); // Escribimos el nuevo ID
            StringBuffer buffer = new StringBuffer(apellido);
            buffer.setLength(10); // Ajustamos a 10 caracteres
            file.writeChars(buffer.toString());
            file.writeInt(departamento);
            file.writeDouble(salario);

            file.close();
            System.out.println("Empleado añadido correctamente con ID: " + nuevoId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}