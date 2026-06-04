package com.prog.controlador;

import java.sql.*;
import java.util.ArrayList;

import com.prog.modeloEntidad.Producto;

public class ProductoGestor {
    public ArrayList<Producto> showProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM Productos";

        try (Connection con = Conexion.openConnection();
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery(sql)) { 
            
            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id_producto"), rs.getInt("id_categoria"), rs.getInt("stock"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio"));

                listaProductos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar productos: " + e.getMessage());
        }
        return listaProductos;

    }
}
