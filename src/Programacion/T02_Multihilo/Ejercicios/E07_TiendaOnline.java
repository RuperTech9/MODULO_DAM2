package Programacion.T02_Multihilo.Ejercicios;

public class E07_TiendaOnline {
    public static void main(String[] args) {
        // Crear inventario y agregar productos
        E07_Inventario inventario = new E07_Inventario();
        E07_Producto producto1 = new E07_Producto(1, 10); // Producto con id 1 y cantidad 10
        inventario.agregarProducto(producto1);

        // Crear dos hilos que intentan comprar simultáneamente el mismo producto
        Thread hilo1 = new Thread(new E07_CompraProducto(inventario, 1, 5), "Hilo-1");
        Thread hilo2 = new Thread(new E07_CompraProducto(inventario, 1, 7), "Hilo-2");

        // Ejecutar los hilos simultáneamente
        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
