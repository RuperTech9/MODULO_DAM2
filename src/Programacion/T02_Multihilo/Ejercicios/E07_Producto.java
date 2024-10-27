package Programacion.T02_Multihilo.Ejercicios;

public class E07_Producto {
    public int cantidad;
    public final int id;

    public E07_Producto(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    // Método para obtener la cantidad disponible del producto
    public int getCantidad() {
        return cantidad;
    }

    // Método para actualizar la cantidad del producto
    public void actualizarCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }
}
