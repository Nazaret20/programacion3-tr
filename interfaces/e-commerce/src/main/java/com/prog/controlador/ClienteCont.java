package com.prog.controlador;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteCont {
    public void addCliente(String nombre, String email, int telefono, String direccion) {
        String sqlStr = "INSERT INTO empleados (Nombre, Email, Telefono, Direccion) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.openConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);
            pstmt.setInt(3, telefono);
            pstmt.setString(4, direccion);
            
            pstmt.executeUpdate();

            System.out.println("Añadido correctamente.");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void modificarCliente(String nombre, String email, int telefono, String direccion) {

    }

    public void showClientes() {
        String sqlStr = "SELECT * FROM clientes";
        try (Connection con = Conexion.openConnection();
                Statement s = con.createStatement();
        ) {
            ResultSet rs = s.executeQuery(sqlStr);

                System.out.printf("%30s %30s %30s %30s\n\n",
                   "Nombre",
                   "Email",
                   "Telefono",
                   "Direccion"
                );
            while (rs.next()) {
                System.out.printf("%30s %30s %30s %30s\n",
                   rs.getString("nombre"),
                   rs.getString("email"),
                   rs.getInt("telefono"),
                   rs.getString("direccion")

                );
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
