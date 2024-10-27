package Programacion.T02_Multihilo.Ejercicios;

class E07_CompraProducto implements Runnable {
    private final E07_Inventario inventario;
    private final int idProducto;
    private final int cantidad;

    public E07_CompraProducto(E07_Inventario inventario, int idProducto, int cantidad) {
        this.inventario = inventario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        inventario.comprarProducto(idProducto, cantidad);
    }
}