package AccesoADatos.T01_Ficheros;

import java.io.*;
import java.util.Scanner;

public class E23_ModificarSalarioEmpleadoAleatorio {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src/AccesoADatos/T01_Ficheros/PruebaAleatorioEmpl.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw"); // "rw" para leer y escribir

            Scanner sc = new Scanner(System.in);

            // Solicitar ID del empleado y el importe a sumar
            System.out.print("Introduce el ID del empleado: ");
            int idBuscado = sc.nextInt();

            System.out.print("Introduce el importe a sumar al salario: ");
            double importe = sc.nextDouble();

            // Verificar si el ID existe y actualizar el salario
            if (!actualizarSalario(file, idBuscado, importe)) {
                System.out.println("El empleado con ID " + idBuscado + " no existe.");
            }

            file.close(); // Cerrar el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar el empleado por ID y actualizar su salario
    private static boolean actualizarSalario(RandomAccessFile file, int idBuscado, double importe) throws IOException {
        long numRegistros = file.length() / 36; // Calcular el número de registros
        file.seek(0); // Posicionar el puntero al principio del archivo

        for (int i = 0; i < numRegistros; i++) {
            long posicionRegistro = file.getFilePointer(); // Guardar la posición actual del registro
            int id = file.readInt(); // Leer el ID del empleado

            // Saltar apellido
            char[] apellido = new char[10];
            for (int j = 0; j < apellido.length; j++) {
                apellido[j] = file.readChar(); // Leer los caracteres del apellido
            }
            String apellidoStr = new String(apellido).trim(); // Convertir apellido a String

            int departamento = file.readInt(); // Leer el departamento
            double salarioAntiguo = file.readDouble(); // Leer el salario actual

            if (id == idBuscado) {
                // Si el ID coincide, actualizar el salario
                double salarioNuevo = salarioAntiguo + importe;

                // Volver a la posición del salario para actualizarlo
                file.seek(posicionRegistro + 4 + 20 + 4); // ID (4 bytes) + apellido (20 bytes) + departamento (4 bytes)
                file.writeDouble(salarioNuevo); // Escribir el nuevo salario

                // Mostrar la información del empleado
                System.out.printf("Empleado encontrado: Apellido: %s, Salario antiguo: %.2f, Salario nuevo: %.2f\n",
                        apellidoStr, salarioAntiguo, salarioNuevo);

                return true; // Empleado encontrado y actualizado
            }

            // Si no es el ID buscado, saltar al siguiente registro (ya se ha leído todo el registro)
        }

        return false; // Si no se encontró el empleado con el ID dado
    }
}
