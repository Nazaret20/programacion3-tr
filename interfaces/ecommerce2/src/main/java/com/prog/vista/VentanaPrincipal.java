package com.prog.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JTabbedPane pestanas;

    public VentanaPrincipal() {
        setTitle("E-Commerce");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelBase = new JPanel(new BorderLayout());

        pestanas = new JTabbedPane();
        pestanas.addTab("Productos", new ProductosVentana());
        pestanas.addTab("Clientes", new ClientesVentana());
        pestanas.addTab("Pedidos", new PedidosVentana());

        panelBase.add(pestanas, BorderLayout.CENTER);
        add(panelBase);
    }
}