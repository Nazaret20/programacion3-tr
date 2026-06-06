package com.prog.modeloEntidad;

public class Producto {
    private int id_producto, id_categoria, stock;
    private String nombre, descripcion;
    private double precio;

    public Producto(int id_producto, int id_categoria, int stock, String nombre, String descripcion, double precio) {
        this.id_producto = id_producto;
        this.id_categoria = id_categoria;
        this.stock = stock;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto() {
        
    }

    @Override
    public String toString() {
        return "Producto [id_producto=" + id_producto + ", id_categoria=" + id_categoria + ", stock=" + stock
                + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + "]";
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
}
