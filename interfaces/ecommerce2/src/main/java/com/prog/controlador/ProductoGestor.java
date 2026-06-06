package com.prog.controlador;

import java.sql.*;
import java.util.*;

import com.prog.modeloEntidad.Producto;

public class ProductoGestor {
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    // Introducir un producto nuevo-------------------------------
    public void insertProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, id_categoria, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getId_categoria());
            ps.setInt(5, producto.getStock());

            ps.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido insertar el producto: " + e.getMessage());

        }
    }

    // Actualizar datos de un producto-------------------------------
    public void updateProdcuto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, id_categoria = ?, stock = ? WHERE id_producto = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getId_categoria());
            ps.setInt(5, producto.getStock());
            ps.setInt(6, producto.getId_producto());

            ps.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido modificar el producto: " + e.getMessage());

        }
    }

    // Eliminar producto-------------------------------------------
    public void deleteProducto(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setInt(1, idProducto);
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
    public ArrayList<Producto> buscarPorNombre(String producto) {
        String sql = "SELECT * FROM productos WHERE nombre = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, producto);

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                Producto productoBuscado = new Producto(
                        rs.getInt("id_producto"), rs.getInt("id_categoria"), rs.getInt("stock"), rs.getString("nombre"),
                        rs.getString("descripcion"), rs.getDouble("precio"));

                listaProductos.add(productoBuscado);
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se han podido encontrar productos: " + e.getMessage());
        }
        return listaProductos;

    }

    public ArrayList<Producto> buscarPorCategoria(int idCategoria) {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE id_categoria = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getInt("id_categoria"),
                        rs.getInt("stock"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"));
                listaProductos.add(producto);

            }
        } catch (SQLException e) {
            System.out.println("No se ha podido buscar por categoría: " + e.getMessage());
        }
        return listaProductos;
    }

}
