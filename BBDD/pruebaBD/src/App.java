import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conexion = openConnection();
        System.out.println("Conectado a la BBDD");

       // Preparamos la consulta
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM test");

        // Iteramos sobre todos los registros del resultado
        while (rs.next()) {
            // Podemos acceder por nombre de columna o por índice
            System.out.println(rs.getString("nombre") + " (" + rs.getInt("edad") + ")");
                   
        }

        conexion.close();
    }

    public static Connection openConnection() {
        Connection con = null;
        try {
            // Creamos la URL de conexión
            // Formato:
            // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
            String connectionUrl = "jdbc:mariadb://localhost:3306/bdprueba1?user=root&password=nazaret";

            // Obtenemos el objeto Connection que representa la conexión
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            // Capturamos errores relacionados con SQL y la base de datos
            System.out.println("Excepción SQL: " + e.getMessage());
        }
    
        return con;
    }
}
