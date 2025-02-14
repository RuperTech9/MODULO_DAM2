package AccesoADatos.T06_DesarrolloComponentes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Producto implements Serializable {

    private String descripcion;
    private int idProducto;
    private int stockActual;
    private int stockMinimo;
    private float pvp;

    private PropertyChangeSupport propertySupport;

    public Producto(int idproducto, String descripcion,
                    int stockactual, int stockminimo, float pvp) {
        propertySupport = new PropertyChangeSupport(this);
        this.idProducto = idproducto;
        this.descripcion = descripcion;
        this.stockActual = stockactual;
        this.stockMinimo = stockminimo;
        this.pvp = pvp;
    }




    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        String oldDescripcion = this.descripcion;
        this.descripcion = descripcion;
        propertySupport.firePropertyChange("descripcion", oldDescripcion, descripcion);
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        int oldIdProducto = this.idProducto;
        this.idProducto = idProducto;
        propertySupport.firePropertyChange("idProducto", oldIdProducto, idProducto);
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int valorNuevo) {
        int valorAnterior = this.stockActual;
        this.stockActual = valorNuevo;
        propertySupport.firePropertyChange("stockActual", valorAnterior, this.stockActual);
        this.stockActual = valorAnterior;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        int oldStockMinimo = this.stockMinimo;
        this.stockMinimo = stockMinimo;
        propertySupport.firePropertyChange("stockMinimo", oldStockMinimo, stockMinimo);
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        float oldPvp = this.pvp;
        this.pvp = pvp;
        propertySupport.firePropertyChange("pvp", oldPvp, pvp);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
