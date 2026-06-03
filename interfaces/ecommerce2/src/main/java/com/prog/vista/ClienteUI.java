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
    }

    private void initializeComponents() {
        setTitle("E-Commerce");
        // Tabla de resultados
        String[] nombresColumnas = { "Nombre", "Email", "Telefono", "Dirección" };
        modeloTabla = new DefaultTableModel(nombresColumnas, 0);
        resultTable = new JTable(modeloTabla);
        

        JPanel resultPanel = new JPanel(new BorderLayout());

        resultPanel.add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // Añadir todos los paneles al frame
        add(resultPanel, BorderLayout.SOUTH);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void cargarCliente() {
        ArrayList<Cliente> listaCliente2 = clienteBack.getClientes();
        //Hacer for y hacer esto
        modeloTabla.addRow(new Object[] {
                "Diana",
                "tucutu@gmail.com",
                "123456",
                "C/ Benito"
        });
        
    }
}
