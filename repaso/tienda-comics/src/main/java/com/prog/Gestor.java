package com.prog;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestor {

    /*------- Insertar datos de txt a la bbdd ------ */
    public boolean importarComicDesdeFichero(String rutaArchivo) {
        boolean exito = false;
        String sqlStr = "INSERT INTO inventario (titulo, autor, genero, precio, stock) VALUES (?, ?, ?, ?, ?)";

        try (FileReader fr = new FileReader(new File(rutaArchivo));
                BufferedReader br = new BufferedReader(fr);
                Connection con = openConnection();
                PreparedStatement ps = con.prepareStatement(sqlStr);) {

            String linea = "";
            int cont = 1;

            while (linea != null) {
                linea = br.readLine();
                if (linea != null) {
                    String[] datosComic = linea.split(",");

                    Double precio = Double.parseDouble(datosComic[3]);
                    int stock = Integer.parseInt(datosComic[4]);

                    ps.setString(1, datosComic[0]);
                    ps.setString(2, datosComic[1]);
                    ps.setString(3, datosComic[2]);
                    ps.setDouble(4, precio);
                    ps.setInt(5, stock);

                    ps.executeUpdate();
                }
                cont++;
                exito = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return exito;

    }

    /*------------- Añadir venta ------------ */
    public boolean anadirVenta(String titulo, int cantidad) {
        String sqlStr = "SELECT stock FROM inventario WHERE titulo = ?";
        String sqlUpdate = "UPDATE inventario SET stock = ? WHERE titulo = ?";
        boolean exito = false;

        try (Connection con = openConnection();
                PreparedStatement psCheck = con.prepareStatement(sqlStr)) {

            psCheck.setString(1, titulo);

            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("stock");

                if (stock >= cantidad) {
                    int restoAInventario = stock - cantidad;

                    try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
                        psUpdate.setInt(1, restoAInventario);
                        psUpdate.setString(2, titulo);

                        psUpdate.executeUpdate();
                        exito = true;
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return exito;
    }

    /*------------- Eliminar venta ------------ */
    public boolean eliminarComic(String titulo) {
        String sqlCheck = "SELECT titulo FROM inventario WHERE titulo = ?";
        String sqlDlt = "DELETE FROM inventario WHERE titulo = ?";

        boolean exito = false;

        try (Connection con = openConnection();
                PreparedStatement psCheck = con.prepareStatement(sqlCheck)) {

            psCheck.setString(1, titulo);

            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                try (PreparedStatement psDlt = con.prepareStatement(sqlDlt)) {
                    psDlt.setString(1, titulo);
                    int filas = psDlt.executeUpdate();

                    if (filas > 0) {
                        exito = true;
                    }
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        return exito;
    }

    /*------------- Listar cómics y agotados ------------ */
    public String listarComics() {
        String sqlStr = "SELECT * FROM inventario";
        String print = "";

        try (Connection con = openConnection();
                PreparedStatement ps = con.prepareStatement(sqlStr)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");

                print += "Titulo: " + titulo + " | Autor: " + autor + " | Precio: " + precio + " Euros | Stock: "
                        + stock + "\n";
            }

        } catch (Exception e) {
            // TODO: handle exception
            print = "Error al cargar inventario";
        }

        if (print.isEmpty()) {
            print = "No hay cómics en el inventario.";
        }

        return print;

    }

    public String agotados() {
        String sqlStr = "SELECT * FROM inventario WHERE stock = 0";
        String resultado = "";
        try (Connection con = openConnection();
                PreparedStatement ps = con.prepareStatement(sqlStr);) {

                ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado += "Titulo: " + rs.getString("titulo") + " | Autor: " + rs.getString("autor") + " | Precio: " + rs.getDouble("precio") + " Euros | Stock: "
                        + rs.getInt("stock") + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (resultado.isEmpty()) {
            resultado = "No hay cómics agotados.\n";
        }

        return resultado;
    }

    public static Connection openConnection() {
        Connection con = null;
        try {
            // Creamos la URL de conexión
            // Formato:
            // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
            String connectionUrl = "jdbc:mariadb://localhost:3306/invent_comic?user=root&password=1234";

            // Obtenemos el objeto Connection que representa la conexión
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            // Capturamos errores relacionados con SQL y la base de datos
            System.out.println("Excepción SQL: " + e.getMessage());
        }

        return con;
    }
}
