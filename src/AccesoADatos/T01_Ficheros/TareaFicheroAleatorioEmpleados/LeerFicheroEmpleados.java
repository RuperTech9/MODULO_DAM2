package AccesoADatos.T01_Ficheros.TareaFicheroAleatorioEmpleados;

import java.io.*;

public class LeerFicheroEmpleados {
    public static void main(String[] args) {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros/TareaFicheroAleatorioEmpleados/EmpleadosAleatorio.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            int id, departamento;
            double salario;
            char[] apellido = new char[10];
            char aux;

            long posicion = 0;

            while (posicion < file.length()) {
                file.seek(posicion);
                id = file.readInt();
                for (int i = 0; i < 10; i++) {
                    aux = file.readChar();
                    apellido[i] = aux;
                }
                String apellidoStr = new String(apellido).trim();
                departamento = file.readInt();
                salario = file.readDouble();

                if (id > 0) {
                    System.out.printf("ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f\n",
                            id, apellidoStr, departamento, salario);
                }

                posicion += 36;
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}