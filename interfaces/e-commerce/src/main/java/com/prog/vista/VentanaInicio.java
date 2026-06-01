package com.prog.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {
    public VentanaInicio() {
        setTitle("E-Commerce");
        setLayout(new BorderLayout());

        //Panel izquierdo
        JPanel izq = new JPanel();
        izq.setPreferredSize(new Dimension(220, 720));
        izq.setBackground(new Color(129, 215, 255));


        JMenuBar menu = new JMenuBar();
        menu.setLayout(new GridLayout(3, 1, 10, 10));
        JMenuItem menuItem1 = new JMenuItem("Productos");
        JMenuItem menuItem2 = new JMenuItem("Clientes");
        JMenuItem menuItem3 = new JMenuItem("Pedidos");

        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);

        izq.add(menu);

        add(izq, BorderLayout.WEST);

        setSize(1000, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
