package com.prog.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.prog.controlador.ClienteControlador;
import com.prog.modeloEntidad.Cliente;

import java.awt.*;
import java.util.ArrayList;

public class ClienteUI extends JFrame {
    private JTable resultTable;
    private DefaultTableModel modeloTabla;
    private ClienteControlador clienteBack = new ClienteControlador();

    public ClienteUI() {
        initializeComponents();
        cargarCliente();
    }

    private void initializeComponents() {
        setTitle("E-Commerce");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String[] nombresColumnas = { "Nombre", "Email", "Telefono", "Dirección" };
        modeloTabla = new DefaultTableModel(nombresColumnas, 0);
        resultTable = new JTable(modeloTabla);

        JPanel resultPanel = new JPanel(new BorderLayout());

        resultPanel.add(new JScrollPane(resultTable), BorderLayout.CENTER);

        
        add(resultPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    public void cargarCliente() {
        ArrayList<Cliente> listaCliente2 = clienteBack.getClientes();

        modeloTabla.setRowCount(0);

        for (Cliente cliente : listaCliente2) {
            modeloTabla.addRow(new Object[] {
                    cliente.getNombre(),
                    cliente.getEmail(), 
                    cliente.getTelefono(),
                    cliente.getDireccion()
            });
        }
    }
}
