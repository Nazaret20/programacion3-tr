package com.prog;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
                ExploradorUI ui = new ExploradorUI();
                ui.setVisible(true);
        });
    }
}