package com.prog;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class GestorDatos {
<<<<<<< HEAD
    
=======
>>>>>>> fedbf9ffaed7bfa87397751552cdcbca05a874d7
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void addRegistro(LocalDate fecha, double tMax, double tMin) {
        try (Connection con = openConnection();) {
            String sqlStr = "INSERT INTO registro (fecha, tMax, tMin) VALUES ('" + fecha + "', " + tMax + ", " + tMin
                    + ")";

            Statement s = con.createStatement();
            s.executeUpdate(sqlStr);

<<<<<<< HEAD
    public void anadir(LocalDate fecha, double tMax, double tMin) {
        try (Connection con = openConnection();) {
            String sqlString = "INSERT INTO registro (fecha, tMax, tMin) VALUES ('" + fecha + "', " + tMax + ", " + tMin + ")";

            Statement s = con.createStatement();
            s.executeUpdate(sqlString);

=======
>>>>>>> fedbf9ffaed7bfa87397751552cdcbca05a874d7
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
<<<<<<< HEAD
    
    public void modificarDatos(LocalDate fecha, double tMax, double tMin) {
       try (Connection con = openConnection();) {
            String sqlString = "UPDATE registro SET fecha = '" + fecha + "', tMax = " + tMax + ", tMin = " + tMin + " WHERE fecha = '" + fecha + "";

            Statement s = con.createStatement();
            s.executeUpdate(sqlString);
=======

    public void updateRegistro(LocalDate fecha, double tMax, double tMin) {
        try (Connection con = openConnection();) {
            String sqlStr = "UPDATE registro SET fecha='" + fecha + "', tMax=" + tMax + ", tMin=" + tMin
                    + " WHERE fecha='" + fecha + "'";

            Statement s = con.createStatement();
            s.executeUpdate(sqlStr);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void showRegistro(LocalDate fecha) {
        try (Connection con = openConnection();) {
            String sqlStr = "SELECT * FROM registro";

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlStr);

            while (rs.next()) {
                LocalDate fechaBD = LocalDate.parse(rs.getString("fecha"));
                if (fecha.equals(fechaBD)) {
                    System.out.print(fecha.format(formato));
                    System.out.print("\t" + rs.getDouble("tMax"));
                    System.out.print("\t" + rs.getDouble("tMin") + "\n");
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public double[] calcularPromedio(int mes) {
        double[] promedios = new double[2];
        try (Connection con = openConnection();) {
            String sqlStr = "SELECT AVG(tMax) AS pMax, AVG(tMin) AS pMin FROM registro WHERE MONTH(fecha)=" + mes;

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlStr);

            if (rs.next()) {
                promedios[0] = rs.getDouble("pMax");
                promedios[1] = rs.getDouble("pMin");
            }
>>>>>>> fedbf9ffaed7bfa87397751552cdcbca05a874d7

        } catch (Exception e) {
            // TODO: handle exception
        }
<<<<<<< HEAD
    }

    public void consultarDatos(LocalDate fecha) {
        try (Connection con = openConnection();) {
            String sqlString = "SELECT * FROM registro";

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlString);

            while (rs.next()) {
                LocalDate fechaBD = LocalDate.parse(rs.getString("fecha"));

                if (fecha.equals(fechaBD)) {
                    System.out.println(fecha.format(formato));
                    System.out.println("\t" + rs.getDouble("tMax"));
                    System.out.println("\t" + rs.getDouble("tMin") + "\n");

                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public double[] calcularMedia(int mes) {
            double[] promedios = new double[2];

        try (Connection con = openConnection();) {
            String sqlString = "SELECT AVG(tMax) AS mediaMax, AVG(tMin) AS mediaMin FROM registro WHERE MONTH(fecha) = " + mes;

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlString);

            if(rs.next()) {
                promedios[0] = rs.getDouble("mediaMax");
                promedios[1] = rs.getDouble("mediaMin");
            }

          
        } catch (Exception e) {
            // TODO: handle exception
        }

        return promedios;
 
    }

    public static Connection openConnection() {
        Connection con = null;
        try {
            // Creamos la URL de conexión
            // Formato:
            // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
            String connectionUrl = "jdbc:mariadb://localhost:3306/registro_temp?user=root&password=nazaret";

            // Obtenemos el objeto Connection que representa la conexión
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            // Capturamos errores relacionados con SQL y la base de datos
            System.out.println("Excepción SQL: " + e.getMessage());
        }
    
        return con;
    }

}
=======

        return promedios;
    }

    public static Connection openConnection() {
        Connection con = null;
        try {
            // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
            String connectionUrl = "jdbc:mariadb://localhost:3306/temperaturas_db?user=root&password=1234";

            // Obtenemos el objeto Connection que representa la conexión
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            // Capturamos errores relacionados con SQL y la base de datos
            System.out.println("Excepción SQL: " + e.getMessage());
        }
        return con;
    }
}
>>>>>>> fedbf9ffaed7bfa87397751552cdcbca05a874d7
