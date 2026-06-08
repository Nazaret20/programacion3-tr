package com.prog.controlador;

import java.sql.*;
import java.util.ArrayList;

import com.prog.modeloEntidad.Pedido;
import com.prog.modeloEntidad.DetallePedido;
import com.prog.modeloEntidad.Producto;

public class PedidoGestor {

    //Crear un pedido nuevo en el carrito----------------------------
    public boolean crearPedido(int idCliente, ArrayList<DetallePedido> carrito) {
        String sqlDePedido = "INSERT INTO pedidos (id_cliente, estado) VALUES (?, 'pendiente')";
        String sqlDeUltimoId = "SELECT MAX(id_pedido) FROM pedidos";
        String sqlDetallePedido = "INSERT INTO detalles_pedido (id_pedido, id_producto, cantidad, precio_unit, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.openConnection();
                PreparedStatement psPedido = con.prepareStatement(sqlDePedido);) {

            //Primero insertamos el pedido
            psPedido.setInt(1, idCliente);
            psPedido.executeUpdate();

            //Miramos el ID del pedido recién hecho
            int idPedido = 0;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlDeUltimoId);
            if (rs.next()) {
                idPedido = rs.getInt(1);
            }

            //Insertamos cada línea del carrito
            PreparedStatement psDetalle = con.prepareStatement(sqlDetallePedido);
            for (DetallePedido detalle : carrito) {
                psDetalle.setInt(1, idPedido);
                psDetalle.setInt(2, detalle.getId_producto());
                psDetalle.setInt(3, detalle.getCantidad());
                psDetalle.setDouble(4, detalle.getPrecio_unit());
                psDetalle.setDouble(5, detalle.getSubtotal());
                psDetalle.executeUpdate();
            }
            return true;

        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("No se ha podido crear pedido: " + e.getMessage());
            return false;
        }
    }

    //Cambair estado del pedido-------------------------------
    public void cambiarEstado(Pedido p) {
        String sql = "UPDATE pedidos SET estado = ? WHERE id_pedido = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, p.getEstado());
            ps.setInt(2, p.getId_pedido());
            ps.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("No se ha podido cambiar el estado del pedido: " + e.getMessage());
        }
    }

    //Mostrar los detalles de un pedido------------------------
    public ArrayList<DetallePedido> obtenerDetalles(int idPedido) {
        ArrayList<DetallePedido> listaDetallesDePed = new ArrayList<>();
        String sql = "SELECT * FROM detalles_pedido WHERE id_pedido = ?";

        try (Connection con = Conexion.openConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPedido);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetallePedido detallePed = new DetallePedido(
                        rs.getInt("ID_Detalle"),
                        rs.getInt("ID_Pedido"),
                        rs.getInt("ID_Producto"),
                        rs.getInt("Cantidad"),
                        rs.getDouble("Precio_Unit"),
                        rs.getDouble("Subtotal"));
                listaDetallesDePed.add(detallePed);
            }

        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("No se han podido mostrar los detalles del pedido: " + e.getMessage());
        }
        return listaDetallesDePed;
    }

    //Mostrar todos los pedidos-------------------------------
    public ArrayList<Pedido> showPedidos() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection con = Conexion.openConnection();
                Statement s = con.createStatement();) {

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id_pedido"), rs.getInt("id_cliente"), rs.getString("fecha_pedido"),
                        rs.getString("estado"), rs.getDouble("total"));

                listaPedidos.add(pedido);
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se han podido mostrar los pedidos: " + e.getMessage());
        }
        return listaPedidos;
    }
}
