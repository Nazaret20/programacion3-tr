package com.prog.controlador;

import java.sql.*;
import java.util.*;

import com.prog.modeloEntidad.Cliente;

public class ClienteControlador {
    
    public void anadirCliente(String nombre, String telefono, String email, String direccion) {

    }

    public void modificarCliente() {

    }

    public static ArrayList<Cliente> getClientes() {
         ArrayList<Cliente> listaClientes = new ArrayList<>();

        String sqlStr = "SELECT * FROM clientes";
        try (Connection con = Conexion.openConnection();
                Statement s = con.createStatement();
        ) {
            ResultSet rs = s.executeQuery(sqlStr);

            while (rs.next()) {

                   int id = rs.getInt("id_cliente");
                   String nombre = rs.getString("nombre");
                   String email = rs.getString("email");
                   String telefono = rs.getString("telefono");
                   String direccion = rs.getString("direccion");

                Cliente cliente = new Cliente(id, nombre, telefono, email, direccion);
                listaClientes.add(cliente);
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        return listaClientes;
     
    }


}
