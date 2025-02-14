package AccesoADatos.T06_DesarrolloComponentes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable, PropertyChangeListener {
    private int numeroPedido;
    private Producto producto;
    private Date fecha;
    private int cantidad;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Stock anterior: " + evt.getOldValue());
        System.out.println("Stock nuevo: " + evt.getNewValue());

        System.out.println("Stock fecha: " + producto.getDescripcion());
    }

    public Pedido(){}
    public Pedido(int numeroPedido, Producto producto, Date fecha, int cantidad) {
        this.numeroPedido = numeroPedido;
        this.producto = producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }
    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
