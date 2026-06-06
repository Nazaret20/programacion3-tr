package com.prog.vista;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.prog.controlador.ClienteGestor;
import com.prog.modeloEntidad.Cliente;
import com.prog.modeloEntidad.Pedido;

public class ClientesVentana extends JPanel {
    private JTextField buscarTxf, nombreTxf, emailTxf, telefonoTxf, direccionTxf;
    private JButton btnBuscar, btnGuardar, btnEliminar, btnLimpiar, btnHistorial;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private ClienteGestor clienteGestor;

    public ClientesVentana() {
        clienteGestor = new ClienteGestor();
        initializeComponents();
        setupLayout();
        setupListeners();
        cargarTabla();
    }

    // Componentes-------------------------------------------------
    private void initializeComponents() {
        // Textfields
        buscarTxf = new JTextField(20);
        nombreTxf = new JTextField();
        emailTxf = new JTextField();
        telefonoTxf = new JTextField();
        direccionTxf = new JTextField();

        // Botones
        btnBuscar = new JButton("Buscar");
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnHistorial = new JButton("Ver historial");

        // Tabla y modelo tabla, la forma fácil de insertar los nombres de las cols
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Direccion");
        tabla = new JTable(modeloTabla);
    }

    // Layout----------------------------------------------------
    private void setupLayout() {
        setLayout(new BorderLayout(5, 5));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(buscarTxf);
        panelBusqueda.add(btnBuscar);

        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del cliente"));
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(nombreTxf);
        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(emailTxf);
        panelFormulario.add(new JLabel("Telefono:"));
        panelFormulario.add(telefonoTxf);
        panelFormulario.add(new JLabel("Direccion:"));

        panelFormulario.add(direccionTxf);
        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnEliminar);
        panelFormulario.add(btnLimpiar);
        panelFormulario.add(btnHistorial);

        add(panelBusqueda, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelFormulario, BorderLayout.EAST);
    }
    
    // Listeners----------------------------------------------
    private void setupListeners() {
        btnBuscar.addActionListener(e -> {
            if (buscarTxf.getText().isEmpty()) {
                cargarTabla();
            } else {
                cargarDatosEnTabla(clienteGestor.buscarPorNombre(buscarTxf.getText()));
            }
        });

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                nombreTxf.setText(modeloTabla.getValueAt(fila, 1).toString());
                emailTxf.setText(modeloTabla.getValueAt(fila, 2).toString());
                telefonoTxf.setText(modeloTabla.getValueAt(fila, 3).toString());
                direccionTxf.setText(modeloTabla.getValueAt(fila, 4).toString());
            }
        });

        btnGuardar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            Cliente cliente = new Cliente();
            cliente.setNombre(nombreTxf.getText());
            cliente.setEmail(emailTxf.getText());
            cliente.setTelefono(telefonoTxf.getText());
            cliente.setDireccion(direccionTxf.getText());

            if (fila == -1) {
                clienteGestor.insertCliente(cliente);
            } else {
                cliente.setId_cliente(Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString()));
                clienteGestor.updateCliente(cliente);
                ;
            }
            limpiarFormulario();
            cargarTabla();
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona el cliente que quieras eliminar");
                return;
            }
            clienteGestor.deleteCliente(Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString()));
            limpiarFormulario();
            cargarTabla();
        });

        btnLimpiar.addActionListener(e -> limpiarFormulario());

        btnHistorial.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona el cliente que quieras para ver su historial");
                return;
            }

            Cliente cliente = new Cliente();
            cliente.setId_cliente(Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString()));
            cliente.setNombre(modeloTabla.getValueAt(fila, 1).toString());

            ArrayList<Pedido> historial = clienteGestor.showHistorialCliente(cliente.getId_cliente());

            JDialog dialogo = new JDialog();
            dialogo.setTitle("Historial de " + cliente.getNombre());
            dialogo.setSize(500, 300);
            dialogo.setLocationRelativeTo(this);

            String[] columnas = { "ID Pedido", "Estado", "Total" };
            DefaultTableModel modeloHistorial = new DefaultTableModel(columnas, 0);
            for (Pedido pedido : historial) {
                modeloHistorial.addRow(new Object[] { pedido.getId_pedido(), pedido.getEstado(), pedido.getTotal() });
            }

            dialogo.add(new JScrollPane(new JTable(modeloHistorial)));
            dialogo.setVisible(true);
        });
    }

    // Métodos para los listeners-----------------------------------------
    private void cargarTabla() {
        cargarDatosEnTabla(clienteGestor.showClientes());
    }

    private void cargarDatosEnTabla(ArrayList<Cliente> lista) {
        modeloTabla.setRowCount(0);
        for (Cliente cliente : lista) {
            modeloTabla.addRow(new Object[] {
                    cliente.getId_cliente(),
                    cliente.getNombre(),
                    cliente.getEmail(),
                    cliente.getTelefono(),
                    cliente.getDireccion()
            });
        }
    }

    private void limpiarFormulario() {
        nombreTxf.setText("");
        emailTxf.setText("");
        telefonoTxf.setText("");
        direccionTxf.setText("");
        tabla.clearSelection();
    }
}
