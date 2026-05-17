package com.prog;

import java.sql.*;

import javax.swing.table.DefaultTableModel;

public class GestorBD {
    private Connection conexion = null;
    private Statement statement = null;
    private ResultSet result = null;

    public Connection openConnection(String host, String bd, String usuario, String contrasena) {

        try {
            if (conexion != null) {
                conexion.close();
            }
            // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
            String connectionUrl = "jdbc:mariadb://" + host + "/" + bd;

            // Obtenemos el objeto Connection que representa la conexión
            conexion = DriverManager.getConnection(connectionUrl, usuario, contrasena);
            System.out.println("Conexión realizada con éxito.");

        } catch (SQLException e) {
            // Capturamos errores relacionados con SQL y la base de datos
            System.out.println("Excepción SQL: " + e.getMessage());
        }
        return conexion;
    }

    public void ejecutarConsulta(String sql, DefaultTableModel modelo) {
    // Comprobamos primero si estamos conectados
    if (conexion == null) {
        System.out.println("Error: No hay ninguna conexión abierta.");
        return;
    }

    // Usamos Statement y ResultSet para ejecutar el SELECT
    try (Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        // --- PASO A: Averiguar los nombres de las columnas (MetaData) ---
        ResultSetMetaData metaData = rs.getMetaData();
        int numeroColumnas = metaData.getColumnCount();

        // Añadimos los títulos a la tabla en la pantalla
        for (int i = 1; i <= numeroColumnas; i++) {
            modelo.addColumn(metaData.getColumnName(i));
        }

        // --- PASO B: Sacar las filas de la base de datos y meterlas en la tabla ---
        while (rs.next()) {
            Object[] fila = new Object[numeroColumnas];
            for (int i = 1; i <= numeroColumnas; i++) {
                fila[i - 1] = rs.getObject(i); // Guardamos cada celda
            }
            modelo.addRow(fila); // Añadimos la fila entera al modelo visual
        }

        System.out.println("¡Consulta ejecutada y tabla actualizada!");

    } catch (SQLException e) {
        System.out.println("Error al ejecutar la query: " + e.getMessage());
    }
}

}
