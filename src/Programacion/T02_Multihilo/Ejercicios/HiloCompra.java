package Programacion.T02_Multihilo.Ejercicios;

public class HiloCompra extends Thread{
    Producto producto;
    Inventario inventario;


    public HiloCompra(String nombre, Producto producto, Inventario inventario) {
        setName(nombre);
        this.producto = producto;
        this.inventario = inventario;
    }

    public void run() {
        Producto interno;
        int actual = 0;
        if(inventario.existeProducto(producto)){
            interno = inventario.listaProductos.get(producto.getId());
            actual = interno.getCantidad();
            actual = actual + producto.getCantidad();
            interno.setCantidad(actual);
        } else {
            inventario.listaProductos.add(producto);
        }
    }
}
