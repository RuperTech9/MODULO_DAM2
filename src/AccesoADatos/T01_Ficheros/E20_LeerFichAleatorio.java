package AccesoADatos.T01_Ficheros;

import java.io.*;

public class E20_LeerFichAleatorio {
    public static void main(String[] args) throws IOException {
        File fichero = new File("./src//AccesoADatos//T01_Ficheros/PruebaAleatorioEmpl.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, dept, posicion;
        double salario;
        char[] apellido = new char[10];
        char aux;

        posicion = 0; // Para situarnos al principio

        while (true) { // Recorro el fichero
            file.seek(posicion); // Nos posicionamos en la posición actual
            id = file.readInt(); // Obtengo ID del empleado

            // Recorro uno a uno los caracteres del apellido
            for (int i = 0; i < apellido.length; i++) {
                aux = file.readChar();
                apellido[i] = aux; // Los voy guardando en el array
            }

            // Convierto a String el array de caracteres, eliminando espacios en blanco
            String apellidos = new String(apellido).trim();
            dept = file.readInt(); // Obtengo el departamento
            salario = file.readDouble(); // Obtengo el salario

            if (id > 0) {
                // Corrige la forma de imprimir los datos utilizando printf
                System.out.printf("ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f %n",
                        id, apellidos, dept, salario);
            }

            // Me posiciono para el siguiente empleado, cada empleado ocupa 36 bytes
            posicion = posicion + 36;

            // Si he recorrido todos los bytes, salgo del while
            if (file.getFilePointer() == file.length()) {
                break;
            }
        }
        file.close(); // Cerrar el archivo al finalizar
    }
}