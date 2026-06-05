package com.prog.modeloEntidad;

public class Pedido {
    private int id_pedido, id_cliente;
    private String fecha, estado;
    private double total;

    public Pedido(){

    }

    public Pedido(int id_pedido, int id_cliente, String fecha, String estado, double total) {
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido [id_pedido=" + id_pedido + ", id_cliente=" + id_cliente + ", fecha=" + fecha + ", estado="
                + estado + ", total=" + total + "]";
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}
