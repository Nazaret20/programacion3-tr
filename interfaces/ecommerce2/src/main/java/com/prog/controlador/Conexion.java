package com.prog.controlador;

import java.sql.*;

public class Conexion {
     public static Connection openConnection() {
        Connection con = null;
        try {
            // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
            String connectionUrl = "jdbc:mariadb://localhost:3306/ecommerce?user=root&password=1234";

            // Obtenemos el objeto Connection que representa la conexión
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            // Capturamos errores relacionados con SQL y la base de datos
            System.out.println("Excepción SQL: " + e.getMessage());
        }
        return con;
    }

     public static Statement createStatement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createStatement'");
     }    
}
