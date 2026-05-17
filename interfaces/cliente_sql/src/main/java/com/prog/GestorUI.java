package com.prog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class GestorUI extends JFrame {
    private GestorBD gestor = new GestorBD();

    GestorUI() {
        setTitle("Gestor BD");
        setLayout(new BorderLayout());

        /*------------Parte 1------------- */
        JPanel panelA = new JPanel(new GridLayout(2, 5, 5, 5));
        panelA.setPreferredSize(new Dimension(0, 65));
        panelA.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblU = new JLabel("Usuario:");
        JTextField txtFU = new JTextField();

        JLabel lblC = new JLabel("Contraseña:");
        JPasswordField txtFC = new JPasswordField();
        txtFC.setEchoChar('*');

        JLabel lblH = new JLabel("Host:");
        JTextField txtFH = new JTextField();

        JLabel lblBD = new JLabel("Base de Datos:");
        JTextField txtFBD = new JTextField();

        JButton btnC = new JButton("Conectar");

        panelA.add(lblU);
        panelA.add(txtFU);

        panelA.add(lblC);
        panelA.add(txtFC);

        panelA.add(btnC);

        panelA.add(lblH);
        panelA.add(txtFH);

        panelA.add(lblBD);
        panelA.add(txtFBD);

        panelA.add(new JLabel(""));

        add(panelA, BorderLayout.NORTH);

        /*------------Parte 2------------- */
        JPanel panelB = new JPanel(new BorderLayout(5, 5));
        panelB.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblInfo = new JLabel("Consulta SQL (SELECT):");
        panelB.add(lblInfo, BorderLayout.NORTH);

        JTextArea txtA = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtA);
        panelB.add(scroll, BorderLayout.CENTER);

        JButton btnQ = new JButton("Ejecutar Query");
        JButton btnL = new JButton("Limpiar");
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        panelBotones.add(btnQ);
        panelBotones.add(btnL);
        panelB.add(panelBotones, BorderLayout.SOUTH);

        add(panelB, BorderLayout.CENTER);

        /*------------Parte 3------------- */
        // 1. El cerebro de la tabla (donde meteremos las filas y columnas)
        DefaultTableModel modeloTabla = new DefaultTableModel();
        JTable tabla = new JTable(modeloTabla);

        // 3. El scroll (OBLIGATORIO para que se vean los nombres de las columnas)
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setPreferredSize(new Dimension(0, 200));
        scrollTabla.setBorder(new EmptyBorder(0, 10, 10, 10));
        add(scrollTabla, BorderLayout.SOUTH);

        btnC.addActionListener(e -> {
            String host = txtFH.getText();
            String bd = txtFBD.getText();
            String user = txtFU.getText();
            String pass = new String(txtFC.getPassword());
            gestor.openConnection(host, bd, user, pass);
        });

        btnL.addActionListener(e -> {
            txtA.setText("");
            modeloTabla.setRowCount(0);
            modeloTabla.setColumnCount(0);
        });

        btnQ.addActionListener(e -> {
            modeloTabla.setRowCount(0);
            modeloTabla.setColumnCount(0);
            String sql = txtA.getText().trim();

            // if (sql.isEmpty()) {
            //     JOptionPane.showMessageDialog(this, "Por favor, escribe una consulta SQL.", "Aviso", JOptionPane.WARNING_MESSAGE);
            //     return;
            // }

            gestor.ejecutarConsulta(sql, modeloTabla);
        });

        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
