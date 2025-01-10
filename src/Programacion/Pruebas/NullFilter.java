package Programacion.Pruebas;

import java.util.ArrayList;
import java.util.List;

public class NullFilter {
    public static void main(String[] args) {
        String[] cadenas = {null, "Pedro", "Maria", null, "Pedro"};

        String[] sinNulos = eliminarNulos(cadenas);
        System.out.println("Array sin elementos nulos:");
        for (String cadena : sinNulos) {
            System.out.println(cadena);
        }
    }

    public static String[] eliminarNulos(String[] array) {
        List<String> lista = new ArrayList<>();
        for (String elemento : array) {
            if (elemento != null && !lista.contains(elemento)) {
                lista.add(elemento);
            }
        }
        return lista.toArray(new String[0]);
    }
}
/*
 Trabajar con un array de objetos que puede contener null
 Declara un array de tipo String con 3 elementos, asigna valores solo a dos posiciones,
 y crea un metodo que cuente cuántos elementos son null.

 Revisión y Variaciones:
Caso extremo: Agregar lógica para evitar elementos duplicados.
Variación: Crear un metodo que devuelva un nuevo array sin los elementos null.
 */