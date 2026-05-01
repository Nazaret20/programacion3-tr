package com.prog;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Matricula {
    private int id, estudiante_id, curso_id;
    private LocalDate fecha_matricula;
    private double parcial1, parcial2, parcial3, final_exam, promedio;
    private String estatus;
    private LocalDateTime created_at, update_at;
   
    public void matricularEst(String nombre) {
         String sqlStr = "INSERT INTO matriculas (nombre, apellido, salario) VALUES (?, ?, ?)";

        try (Connection con = openConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {
            // pstmt.setString();
            // pstmt.setString();
            // pstmt.setDouble();
            
            pstmt.executeUpdate();

            System.out.println("Añadido correctamente.");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void promedios() {

    }

    public void showEst(String claveCurso) {
        String sqlStr = "SELECT e.matricula, e.nombre, e.apellidos, m.promedio, m.estatus, c.semestre " +
                "FROM cursos c " +
                "JOIN matriculas m ON m.curso_id = c.id " +
                "JOIN estudiantes e ON e.id = m.estudiante_id " +
                "WHERE c.clave = ?";

        try (Connection con = openConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {
            pstmt.setString(1, claveCurso);
            
            ResultSet rs = pstmt.executeQuery(sqlStr);

            
            while (rs.next()) {
                System.out.println("Curso " + claveCurso + " - " + rs.getString("semestre"));
                
                // System.out.println("Matrícula" + rs.getString("matricula") + rs.getString("nombre") + " " + rs.getString("apellidos"));

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    public void historialAcad() {
        
    }

    public static Connection openConnection() {
        Connection con = null;
        try {
            // Creamos la URL de conexión
            // Formato:
            // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
            String connectionUrl = "jdbc:mariadb://localhost:3306/universidad?user=root&password=1234";

            // Obtenemos el objeto Connection que representa la conexión
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            // Capturamos errores relacionados con SQL y la base de datos
            System.out.println("Excepción SQL: " + e.getMessage());
        }

        return con;
    }


}
