package Programacion.T02_Multihilo.Ejemplos;

import java.util.ArrayList;

public class Buffer2 {
    ArrayList<Integer> lista = new ArrayList<>();

    public synchronized void agregar (int numero) {
        lista.add(numero);
        System.out.println("Elemento agregado: " + numero);
        notify();
    }

    public synchronized void eliminar (int numero) throws InterruptedException {
        while(lista.isEmpty()){
            wait();
        }
        if (lista.contains(numero)) {
            lista.remove(numero);
            System.out.println("Elemento eliminado: " + numero);
        } else {
            System.out.println("Elemento no encontrado: " + numero);
        }
        notify();
    }

    public synchronized void imprimir (){
        System.out.println("Contenido: " + lista);
    }
}