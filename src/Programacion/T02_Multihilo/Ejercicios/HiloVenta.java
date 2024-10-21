package Programacion.T02_Multihilo.Ejercicios;

public class HiloVenta extends Thread{
    Producto producto;
    Inventario inventario;


    public HiloVenta(String nombre, Producto producto, Inventario inventario) {
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

            if(actual >= producto.getCantidad()){
                actual = actual - producto.getCantidad();
                interno.setCantidad(actual);
                if(actual == 0) {
                    inventario.removeProducto(interno);
                }
            }else {
                System.out.println("Cantidad: " + interno.getCantidad());
            }
        } else {
                System.out.println("El producto no existe");
        }
    }
}
