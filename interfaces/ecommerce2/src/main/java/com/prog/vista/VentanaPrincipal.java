package com.prog.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JTabbedPane barraPestanas;

    public VentanaPrincipal() {
        setTitle("E-Commerce");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        barraPestanas = new JTabbedPane();
        barraPestanas.addTab("Clientes", new ClientesVentana());
        barraPestanas.addTab("Productos", new ProductosVentana());
        barraPestanas.addTab("Pedidos", new PedidosVentana());

        add(barraPestanas);
    }
}