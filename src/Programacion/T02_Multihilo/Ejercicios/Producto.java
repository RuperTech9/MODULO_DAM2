package Programacion.T02_Multihilo.Ejercicios;

public class Producto {
    private int id;
    private int cantidad;

    public Producto(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
