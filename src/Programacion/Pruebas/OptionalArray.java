package Programacion.Pruebas;

import java.util.Optional;

public class OptionalArray {
    public static void main(String[] args) {
        String[] valores = {null, "Hola", "Mundo", null};

        for (String valor : valores) {
            System.out.println(obtenerMensajeSeguro(valor));
        }
    }

    public static String obtenerMensajeSeguro(String valor) {
        Optional<String> optional = Optional.ofNullable(valor);
        return optional.map(String::toUpperCase).orElse("Valor predeterminado");
    }
}

/*
Uso de Optional para evitar null
Crea un metodo que reciba un valor String y lo envuelva en un Optional.
Si el valor es null, devuelve un mensaje predeterminado.

Revisión y Variaciones:
Caso extremo: Si el valor no es null, modificarlo antes de retornarlo.
Variación: Usar Optional con un array completo.
 */