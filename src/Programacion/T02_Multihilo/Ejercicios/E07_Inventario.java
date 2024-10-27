package Programacion.T02_Multihilo.Ejercicios;

import java.util.List;
import java.util.ArrayList;

class E07_Inventario {
    private final List<E07_Producto> productos = new ArrayList<>();

    public void agregarProducto(E07_Producto producto) {
        productos.add(producto);
    }

    // Método para comprar un producto
    public synchronized boolean comprarProducto(int idProducto, int cantidad) {
        for (E07_Producto producto : productos) {
            if (producto.getId() == idProducto) {
                if (producto.getCantidad() >= cantidad) {
                    System.out.println(Thread.currentThread().getName() + " compra realizada para el producto " + idProducto);
                    producto.actualizarCantidad(producto.getCantidad() - cantidad);
                    System.out.println("Cantidad restante del producto " + idProducto + ": " + producto.getCantidad());
                    return true;
                } else {
                    System.out.println(Thread.currentThread().getName() + " no pudo realizar la compra: stock insuficiente para el producto " + idProducto);
                    return false;
                }
            }
        }
        System.out.println("Producto con id " + idProducto + " no encontrado.");
        return false;
    }
}
