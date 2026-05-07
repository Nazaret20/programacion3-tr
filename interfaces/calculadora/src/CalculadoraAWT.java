import java.awt.*;
import java.awt.event.*;

public class CalculadoraAWT extends Frame{

    CalculadoraAWT(){
        setTitle("Calculadora con AWT");

        Font fuente = new Font("Arial", Font.ROMAN_BASELINE, 30);

        //PARA LA PANTALLA
        Label lblVisor = new Label("0", Label.RIGHT);
        lblVisor.setFont(fuente);
        //PARA LOS NUMEROS

        Button num1 = new Button("1"); num1.setFont(fuente);
        Button num2 = new Button("2"); num2.setFont(fuente);
        Button num3 = new Button("3"); num3.setFont(fuente);
        Button num4 = new Button("4"); num4.setFont(fuente);
        Button num5 = new Button("5"); num5.setFont(fuente);
        Button num6 = new Button("6"); num6.setFont(fuente);
        Button num7 = new Button("7"); num7.setFont(fuente);
        Button num8 = new Button("8"); num8.setFont(fuente);
        Button num9 = new Button("9"); num9.setFont(fuente);
        Button num0 = new Button("0"); num0.setFont(fuente);
        Button punto = new Button("."); punto.setFont(fuente);
        Button c = new Button("C"); c.setFont(fuente);

        //creamos el panel de los numeros
        Panel pnlNumero = new Panel(new GridLayout(4, 3, 5, 5));    
        //añadimos los botones a nuestro panel
        pnlNumero.add(num1);
        pnlNumero.add(num2);
        pnlNumero.add(num3);
        pnlNumero.add(num4);
        pnlNumero.add(num5);
        pnlNumero.add(num6);
        pnlNumero.add(num7);
        pnlNumero.add(num8);
        pnlNumero.add(num9);
        pnlNumero.add(num0);
        pnlNumero.add(punto);
        pnlNumero.add(c);

        //PANEL OPERACIONES
        Button btnM = new Button("+"); btnM.setFont(fuente); btnM.setPreferredSize(new Dimension(60, 60));
        Button btnMe = new Button("-"); btnMe.setFont(fuente);
        Button btnMu = new Button("*"); btnMu.setFont(fuente);
        Button btnD = new Button("/"); btnD.setFont(fuente);
        Button btnI = new Button("="); btnI.setFont(fuente);

        Panel pnlOper = new Panel(new GridLayout(5, 1, 5, 5));
        pnlOper.add(btnM);
        pnlOper.add(btnMe);
        pnlOper.add(btnMu);
        pnlOper.add(btnD);
        pnlOper.add(btnI);

        //PRINCIPAL
        setLayout(new BorderLayout(5, 5));

        add(lblVisor, BorderLayout.NORTH);
        add(pnlNumero, BorderLayout.CENTER);
        add(pnlOper, BorderLayout.EAST);

        //FUNCIONALIDAD
        num1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("1");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "1");
                }
            }
           
        });

        num2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("2");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "2");
                }
            }
           
        });

        num3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("3");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "3");
                }
            }
           
        });

        num4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("4");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "4");
                }
            }
           
        });

        num5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("5");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "5");
                }
            }
           
        });

        num6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("6");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "6");
                }
            }
           
        });

        num7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("7");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "7");
                }
            }
           
        });

        num8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("8");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "8");
                }
            }
           
        });

        num9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("9");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "9");
                }
            }
           
        });

        num0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("0");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "0");
                }
            }
           
        });

        punto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText(".");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + ".");
                }
            }
           
        });

        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblVisor.getText().equals("0")) {
                    lblVisor.setText("1");
                }
                else{
                    lblVisor.setText(lblVisor.getText() + "1");
                }
            }
           
        });
       
       

        //TAMAÑO APLICACIÓN
        setSize(300, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        CalculadoraAWT app = new CalculadoraAWT();
    }
}
