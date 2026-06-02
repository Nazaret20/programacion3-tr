package com.prog;

import javax.swing.*;
import java.awt.*;

public class ExploradorUI extends JFrame {
    ExploradorUI() {
       setUpLayout();
    }

    private void setUpLayout() {
         try {
            // Establecer el Look & Feel a Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setTitle("Explorador de archivo");

        setLayout(new BorderLayout());

        //------------------
        JButton abrirDir = new JButton("Abrir Directorio");
        add(abrirDir, BorderLayout.NORTH);

        //------------------
        JTree tree = new JTree();
        add(tree, BorderLayout.WEST);
        
        
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
