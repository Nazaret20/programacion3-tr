package com.prog;

import java.sql.*;

public class GestorDatos {

    public void addEmpleado(String nombre, String apellido, double salario) {
        String sqlStr = "INSERT INTO empleados (nombre, apellido, salario) VALUES (?, ?, ?)";

        try (Connection con = openConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setDouble(3, salario);
            
            pstmt.executeUpdate();

            System.out.println("Añadido correctamente.");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void rmvEmpleado(String nombre, String apellido) {
        String sqlStr = "DELETE FROM empleados WHERE nombre = ? AND apellido = ?";
        try (Connection con = openConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);

            System.out.println(nombre + " " + apellido + " se eliminó correctamente.");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
   
    public void updateEmpleado(String nombre, String columna, Object datoAModificar) {
        try (Connection con = openConnection();) {
            String sqlStr = "UPDATE empleados SET " + columna + " = ? WHERE nombre = ?";

            PreparedStatement pstmt = con.prepareStatement(sqlStr);
            pstmt.setObject(1, datoAModificar);
            pstmt.setString(2, nombre); 

            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Actualizado con éxito.");
            } else {
                System.out.println("No se encontró al empleado.");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void showEmpleados() {
        try (Connection con = openConnection();) {
            String sqlStr = "SELECT * FROM empleados";

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlStr);

            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " " + rs.getString("apellido") + ", " + rs.getDouble("salario"));

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static Connection openConnection() {
        Connection con = null;
        try {
            // Creamos la URL de conexión
            // Formato:
            // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
            String connectionUrl = "jdbc:mariadb://localhost:3306/empleadosdb?user=root&password=1234";

            // Obtenemos el objeto Connection que representa la conexión
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            // Capturamos errores relacionados con SQL y la base de datos
            System.out.println("Excepción SQL: " + e.getMessage());
        }

        return con;
    }
}
