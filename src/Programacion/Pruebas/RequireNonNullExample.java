package Programacion.Pruebas;

import java.util.Objects;

public class RequireNonNullExample {
    public static void main(String[] args) {
        try {
            String texto = null;
            imprimirTexto(Objects.requireNonNull(texto, "El texto no puede ser null"));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage()); // Salida: El texto no puede ser null
        }
    }

    public static void imprimirTexto(String texto) {
        System.out.println("Texto: " + texto);
    }
}

/*
Uso de Objects.requireNonNull
Escribe un metodo que reciba una cadena y lance una excepción si el valor es null.
 */