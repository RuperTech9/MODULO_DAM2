package AccesoADatos.T01_Ficheros;

import java.io.*;
import java.util.Scanner;

public class E22_AñadirEmpleadoAleatorio {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src/AccesoADatos/T01_Ficheros/PruebaAleatorioEmpl.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw"); // "rw" para leer y escribir

            Scanner sc = new Scanner(System.in);

            System.out.print("Introduce el ID del nuevo empleado: ");
            int nuevoID = sc.nextInt();

            // Verificar si el ID ya existe en el archivo
            if (existeEmpleado(file, nuevoID)) {
                System.out.println("El empleado con ID " + nuevoID + " ya existe. No se puede añadir.");
            } else {
                System.out.print("Introduce el apellido del empleado: ");
                String apellidoNuevo = sc.next();

                System.out.print("Introduce el departamento del empleado: ");
                int nuevoDepartamento = sc.nextInt();

                System.out.print("Introduce el salario del empleado: ");
                double nuevoSalario = sc.nextDouble();

                // Calcular la posición para el nuevo registro
                long posicionInsercion = (nuevoID - 1) * 36;

                // Posicionar el puntero al final o en la posición calculada
                if (posicionInsercion > file.length()) {
                    System.out.println("El identificador es mayor que el tamaño actual del archivo, se insertará al final.");
                    posicionInsercion = file.length();
                }

                file.seek(posicionInsercion); // Nos posicionamos donde va el nuevo empleado
                file.writeInt(nuevoID); // Escribimos el ID

                // Ajustar el apellido a 10 caracteres
                StringBuffer buffer = new StringBuffer(apellidoNuevo);
                buffer.setLength(10); // Definir un tamaño fijo de 10 caracteres
                file.writeChars(buffer.toString()); // Insertamos el apellido

                // Insertar el departamento y el salario
                file.writeInt(nuevoDepartamento);
                file.writeDouble(nuevoSalario);

                System.out.println("Empleado insertado correctamente.");
            }

            file.close(); // Cerrar el archivo

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para verificar si un empleado con el ID dado ya existe en el archivo
    private static boolean existeEmpleado(RandomAccessFile file, int idBuscado) throws IOException {
        long numRegistros = file.length() / 36; // Calcular el número de registros en el archivo
        file.seek(0); // Posicionar el puntero al principio del archivo

        for (int i = 0; i < numRegistros; i++) {
            int id = file.readInt(); // Leer el ID del empleado

            // Saltar el resto de los datos del empleado (apellido, departamento, salario)
            file.skipBytes(10 * 2); // Saltar 10 caracteres de apellido (20 bytes)
            file.skipBytes(4);      // Saltar el departamento (4 bytes)
            file.skipBytes(8);      // Saltar el salario (8 bytes)

            if (id == idBuscado) {
                return true; // Si el ID coincide, el empleado ya existe
            }
        }

        return false; // Si no se encuentra el ID, el empleado no existe
    }
}