package Programacion.T02_Multihilo.Ejercicios;

import java.util.Random;

public class TiendaOnline {
    public static void main(String[] args) {
        Producto p1 = new Producto(1,3);
        Producto p2 = new Producto(2,6);

        Inventario almacen = new Inventario();
        almacen.addProducto(p1);
        almacen.addProducto(p2);

        Random random = new Random();

        HiloCompra h1 = new HiloCompra("hilo1", new Producto(1,random.nextInt(1, 5)), almacen);
        HiloCompra h2 = new HiloCompra("hilo2", new Producto(2,random.nextInt(1, 10)),almacen);

        HiloVenta h3 = new HiloVenta("hilo3", new Producto(1,random.nextInt(1, 3)),almacen);
        HiloVenta h4 = new HiloVenta("hilo4", new Producto(2,random.nextInt(2, 4)),almacen);

        h1.start();
        h2.start();
        h3.start();
        h4.start();



        System.out.println(almacen);

    }
}
