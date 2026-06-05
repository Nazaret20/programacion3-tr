package com.prog.controlador;

import java.sql.*;
import java.util.ArrayList;

import com.prog.modeloEntidad.Producto;

public class ProductoGestor {
    private ArrayList<Producto> listaProductos = new ArrayList<>();

     // Introducir un producto nuevo-------------------------------
    public void insertProducto(Producto p) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, id_cateogria, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getId_categoria());
            ps.setInt(5, p.getStock());

            ps.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido insertar el producto: " + e.getMessage());

        }
    }

    //Actualizar datos de un producto-------------------------------
    public void updateProdcuto(Producto p) {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, id_categoria = ?, stock = ? WHERE id_producto = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getId_categoria());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getId_producto());

            ps.executeUpdate();
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido modificar el producto: " + e.getMessage());

        }
    }

    //Eliminar producto-------------------------------------------
    public void deleteProducto(Producto p) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setInt(1, p.getId_producto());
            ps.executeUpdate();
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido eliminar el producto: " + e.getMessage());

        }
    }

    // Mostrar todos los productos-------------------------------
    public ArrayList<Producto> showProductos() {
        String sql = "SELECT * FROM productos";

        try (Connection con = Conexion.openConnection();
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery(sql);) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id_producto"), rs.getInt("id_categoria"), rs.getInt("stock"), rs.getString("nombre"),
                        rs.getString("descripcion"), rs.getDouble("precio"));

                listaProductos.add(producto);
            }

        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("No se han podido mostrar los productos: " + e.getMessage());

        }
        return listaProductos;
    }

    // Buscar productos por el nombre-----------------------------
    public ArrayList<Producto> buscarPorNombre(Producto p) {
        String sql = "SELECT * FROM productos WHERE nombre = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, p.getNombre());

            try (ResultSet rs = ps.executeQuery(sql)) {
                while (rs.next()) {
                    Producto producto = new Producto(
                            rs.getInt("id_producto"), rs.getInt("id_categoria"), rs.getInt("stock"), rs.getString("nombre"),
                            rs.getString("descripcion"), rs.getDouble("precio"));
    
                    listaProductos.add(producto);
                }
            }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("No se han podido encontrar productos: " + e.getMessage());
            }
            return listaProductos;
        }
    }

   
}
