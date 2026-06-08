import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VideoclubBD {
    private String[] columnNames;

    /*------------------------------------PARTE 1--------------------------------------- */

    //Añadir (insert) película nueva--------------------------------------------------
    public void anyadirPelicula(String titulo, String genero, int anyo) throws SQLException {
        String sqlStr = "INSERT INTO peliculas (titulo, genero, anyo) VALUES (?, ?, ?)";
        try (Connection con = openConnection();
                PreparedStatement ps = con.prepareStatement(sqlStr);) {
            ps.setString(1, titulo);
            ps.setString(2, genero);
            ps.setInt(3, anyo);

            ps.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido insertar película: " + e.getMessage());
        }

    }


    //Registrar (insert) un alquiler nuevo-----------------------------------------------------
    public void registrarAlquiler(String cliente, String dni, int idPelicula, int dias) throws SQLException {
        String sqlStr = "INSERT INTO alquileres (cliente, dni, idPelicula, int dias) VALUES (?, ?, ?, ?)";
        try (Connection con = openConnection();
                PreparedStatement ps = con.prepareStatement(sqlStr);) {
            ps.setString(1, cliente);
            ps.setString(2, dni);
            ps.setInt(3, idPelicula);
            ps.setInt(4, dias);

            ps.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido registrar alquiler: " + e.getMessage());
        }

    }
    
    //Método cerrar conexión--------------------------------------------------
    public void cerrarConexion() {
        try {
            if (openConnection() != null && !openConnection().isClosed()) {
                openConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    //Método (select) obtener películas--------------------------------------------------
    public Object[][] obtenerPeliculas(String query) throws SQLException {
        try {
            Statement stmt = openConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();

            // Obtener nombres de columnas
            columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            // Obtener datos
            List<Object[]> rows = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                rows.add(row);
            }

            // Convertir List a array bidimensional
            Object[][] data = new Object[rows.size()][columnCount];
            for (int i = 0; i < rows.size(); i++) {
                data[i] = rows.get(i);
            }

            rs.close();
            stmt.close();
            return data;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

     public String[] getColumnNames() {
        return columnNames;
    }

    public static Connection openConnection() {
        Connection con = null;
        try {
            // Cargamos el driver de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");

            // Creamos la URL de conexión
            // Formato:
            // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
            String connectionUrl = "jdbc:mariadb://localhost:3306/clientes?user=root&password=root";

            // Obtenemos el objeto Connection que representa la conexión
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            // Capturamos errores relacionados con SQL y la base de datos
            System.out.println("Excepción SQL: " + e.getMessage());
        } catch (ClassNotFoundException cE) {
            // Capturamos errores si no se encuentra el driver
            System.out.println("Excepción de Clase no Encontrada: " + cE.getMessage());
        }
        return con;
    }

    /*------------------------------------PARTE 1--------------------------------------- */
    
    private int siguienteId(String tabla) throws SQLException {

    }

    public boolean estaDisponible(int idPelicula) throws SQLException {
        //Mirar id de la película
        //Leer columna de estado
        //Devolver resultado
    }

    public double calcularCoste(int idPelicula, int dias) throws SQLException {

    }

    public Object[][] obtenerAlquileres() throws SQLException {

    }

}