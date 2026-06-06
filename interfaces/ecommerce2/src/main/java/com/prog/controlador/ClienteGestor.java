package com.prog.controlador;

import java.sql.*;
import java.util.ArrayList;

import com.prog.modeloEntidad.Cliente;
import com.prog.modeloEntidad.Pedido;

public class ClienteGestor {

    //Introducir un cliente nuevo-------------------------------
    public void insertCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());

            ps.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido insertar el cliente: " + e.getMessage());

        }
    }

    //Actualizar datos de un cliente-------------------------------
    public void updateCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, telefono = ?, direccion = ? WHERE id_cliente = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());

            ps.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido modificar el cliente: " + e.getMessage());

        }
    }

    //Eliminar un cliente-------------------------------------------
    public void deleteCliente(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setInt(1, idCliente);
            ps.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido eliminar el cliente: " + e.getMessage());

        }
    }

    //Mostrar historial del cliente-------------------------------
    public ArrayList<Pedido> showHistorialCliente(int idCliente) {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        String sql = "SELECT p.id_pedido, p.total, p.estado, c.id_cliente FROM pedidos p JOIN clientes c ON c.id_cliente = p.id_cliente WHERE p.id_cliente = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setEstado(rs.getString("estado"));
                pedido.setId_cliente(rs.getInt("id_cliente"));

                listaPedidos.add(pedido);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("No se ha podido mostrar el historial: " + e.getMessage());

        }
        return listaPedidos;
    }

    //Buscar cliente por el nombre-----------------------------
    public ArrayList<Cliente> buscarPorNombre(String nombre) {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nombre = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("email"),
                        rs.getString("telefono"), rs.getString("direccion"));

                listaClientes.add(cliente);
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se ha podido encontrar al cliente: " + e.getMessage());
        }
        return listaClientes;
    }

     //Mostrar todos los clientes
    public ArrayList<Cliente> showClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";

        try (Connection con = Conexion.openConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("ID_Cliente"),
                    rs.getString("Nombre"),
                    rs.getString("Email"),
                    rs.getString("Telefono"),
                    rs.getString("Direccion")
                );
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }
        return listaClientes;
    }

}
