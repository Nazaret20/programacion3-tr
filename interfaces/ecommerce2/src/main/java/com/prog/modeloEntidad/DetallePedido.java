package com.prog.modeloEntidad;

public class DetallePedido {
    private int id_detalle, id_pedido, id_producto, cantidad;
    private double precio_unit, subtotal;

    public DetallePedido(int id_detalle, int id_pedido, int id_producto,int cantidad, double precio_unit, double subtotal) {
        this.id_detalle = id_detalle;
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unit = precio_unit;
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetallePedido [id_detalle=" + id_detalle + ", id_pedido=" + id_pedido + ", id_producto=" + id_producto
                + ", cantidad=" + cantidad + ", precio_unit=" + precio_unit + ", subtotal=" + subtotal + "]";
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(double precio_unit) {
        this.precio_unit = precio_unit;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    
}
