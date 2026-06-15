
import java.sql.*;
import java.util.ArrayList;

public class ClinicaBD {
    private Connection con;

    /*------------------------PARTE 1--------------------------- */
    // insert
    public void registrarMascota(String nombre, String especie, String propietario, double peso) throws SQLException {
        String sql = "INSERT INTO mascotas (nombre, especie, propietario, peso) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, especie);
            ps.setString(3, propietario);
            ps.setDouble(4, peso);
            ps.executeUpdate();
        }
    }

    // select
    public Object[][] obtenerMascotas() throws SQLException {
        String sql = "SELECT * FROM mascotas";

        try (Statement s = con.createStatement()) {
            ResultSet rs = s.executeQuery(sql);

            ArrayList<Object[]> lista = new ArrayList<>();

            while (rs.next()) {
                boolean ingresada = rs.getBoolean("ingresada");
                String ingreso;

                if (ingresada) {
                    ingreso = "En clínica";
                } else {
                    ingreso = "En casa";
                }

                Object[] fila = new Object[] {
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("propietario"),
                        rs.getDouble("peso"),
                        ingreso
                };
                lista.add(fila);
            }

            Object[][] datos = new Object[lista.size()][6];

            for (int i = 0; i < lista.size(); i++) {
                datos[i] = lista.get(i);
            }

            return datos;
        }
    }

    // insert
    public void registrarIngreso(int idMascota, String descripcion, double costeBase) throws SQLException {
        String sql = "INSERT INTO ingresos (id_mascota, descripcion, coste_base) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMascota);
            ps.setString(2, descripcion);
            ps.setDouble(3, costeBase);
            ps.executeUpdate();
        }
    }

    // delte
    public void darDeAlta(int idMascota) throws SQLException {
        String sql = "DELETE FROM ingresos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMascota);
        }

        String sqlUp = "UPDATE mascotas SET ingresada = FALSE WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sqlUp)) {
            ps.setInt(1, idMascota);
        }
    }

    /*------------------------PARTE 2--------------------------- */
    public boolean estaIngresada(int idMascota) throws SQLException {
        String sql = "SELECT ingresada FROM mascotas WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            boolean ingresada = false;
            ps.setInt(1, idMascota);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ingresada = rs.getBoolean("ingresada");
                return true;
            }
            return ingresada;
        }
    }

    public Object[][] obtenerIngresos() throws SQLException {
        String sql = "SELECT * FROM ingresos";

        try (Statement s = con.createStatement()) {
            ResultSet rs = s.executeQuery(sql);

            ArrayList<Object[]> lista = new ArrayList<>();

            while (rs.next()) {
                Object[] fila = new Object[] {
                        rs.getInt("id"),
                        rs.getInt("id_mascota"),
                        rs.getString("descripcion"),
                        rs.getDouble("coste_base")

                };
                lista.add(fila);
            }

            Object[][] datos = new Object[lista.size()][6];

            for (int i = 0; i < lista.size(); i++) {
                datos[i] = lista.get(i);
            }

            return datos;
        }
    }

    // public double calcularCosteConIVA(int idMascota, double costeBase) throws
    // SQLException {
    // String sql = "SELECT id, FROM v_ingresos WHERE id = ?";
    // try (PreparedStatement ps = con.prepareStatement(sql)) {
    // ResultSet rs = ps.executeQuery();

    // if (rs.next()) {
    // rs.getInt("id");

    // switch (rs) {
    // case value:

    // break;

    // default:
    // break;
    // }

    // }

    // return 0;
    // }
    // }

    /*------------------------CONEXIONES--------------------------- */
    public void conectarBD() throws SQLException {
        // jdbc:mariadb://servidor:puerto/nombreBaseDatos?usuario=xxx&contraseña=xxx
        String connectionUrl = "jdbc:mariadb://localhost:3306/clinica?user=root&password=nazaret";

        // Obtenemos el objeto Connection que representa la conexión
        con = DriverManager.getConnection(connectionUrl);
    }

    public void cerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}