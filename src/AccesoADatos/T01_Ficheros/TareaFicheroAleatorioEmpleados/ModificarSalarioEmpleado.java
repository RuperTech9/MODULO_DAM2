package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioEmpleados;

import java.io.*;
import java.util.Scanner;

public class ModificarSalarioEmpleado {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioEmpleados/EmpleadosAleatorio.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el ID del empleado: ");
            int idModificar = sc.nextInt();
            System.out.print("Introduce el nuevo salario: ");
            double nuevoSalario = sc.nextDouble();

            long posicion = (idModificar - 1) * 36;

            if (posicion >= file.length()) {
                System.out.println("El empleado con ID " + idModificar + " no existe.");
            } else {
                file.seek(posicion + 30); // Nos posicionamos donde empieza el salario
                file.writeDouble(nuevoSalario);
                System.out.println("Salario modificado correctamente.");
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}