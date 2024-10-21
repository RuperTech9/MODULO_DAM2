package Programacion.T02_Multihilo.Ejercicios;

import java.util.ArrayList;

public class Inventario {
    ArrayList<Producto> listaProductos = new ArrayList<>();

    public boolean addProducto(Producto producto) {
        return listaProductos.add(producto);
    }
    public boolean removeProducto(Producto producto) {
        return listaProductos.remove(producto);
    }

    public boolean existeProducto(Producto producto) {
        return listaProductos.contains(producto);
    }

}
