package com.prog.controlador;

import com.prog.modeloEntidad.Categoria;

import java.sql.*;
import java.util.ArrayList;

public class CategoriaGestor {

    // Mostrar todas las categorías-------------------------------
    public ArrayList<Categoria> obtenerTodas() {
        ArrayList<Categoria> listaCateogiras = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Connection con = Conexion.openConnection();
                Statement s = con.createStatement();) {

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"));
                listaCateogiras.add(categoria);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("No se han podido mostrar las categorias: " + e.getMessage());
        }
        return listaCateogiras;
    }
}