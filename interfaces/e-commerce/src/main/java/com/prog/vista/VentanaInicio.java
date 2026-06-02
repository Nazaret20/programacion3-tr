package com.prog.vista;

import javax.swing.*;

import com.prog.controlador.ClienteCont;

import java.awt.*;

public class VentanaInicio extends JFrame {
    public VentanaInicio() {
        createLayout();
    }

    private void createLayout() {
        setTitle("E-Commerce");
        setLayout(new BorderLayout());

        // Panel izquierdo
        JPanel izq = new JPanel();
        izq.setPreferredSize(new Dimension(220, 720));
        izq.setBackground(new Color(129, 215, 255));

        JMenuBar menu = new JMenuBar();
        menu.setLayout(new GridLayout(3, 1, 10, 10));
        JMenuItem btnProductos = new JMenuItem("Productos");
        JMenuItem btnClientes = new JMenuItem("Clientes");
        JMenuItem btnPedidos = new JMenuItem("Pedidos");

        menu.add(btnProductos);
        menu.add(btnClientes);
        menu.add(btnPedidos);

        btnClientes.addActionListener(e -> {
            ClienteCont clienteBack = new ClienteCont();
            clienteBack.showClientes();
        });

        izq.add(menu);

        add(izq, BorderLayout.WEST);

        setSize(1000, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void printDatos() {

    }
}
