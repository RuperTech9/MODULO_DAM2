package Programacion.Pruebas;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        String texto1 = "Java";
        String texto2 = null;

        System.out.println(obtenerMensajeSeguro(texto1)); // Salida: Java
        System.out.println(obtenerMensajeSeguro(texto2)); // Salida: Valor predeterminado
    }

    public static String obtenerMensajeSeguro(String valor) {
        Optional<String> optional = Optional.ofNullable(valor);
        return optional.orElse("Valor predeterminado");
    }
}

/*
Uso de Optional para evitar null
Crea un metodo que reciba un valor String y lo envuelva en un Optional.
Si el valor es null, devuelve un mensaje predeterminado.
 */