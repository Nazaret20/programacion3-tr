
import java.awt.*;
import java.awt.event.*;

public class Calculadora {
    static Frame frame = new Frame("Calculadora");

    public static Panel grid() {
        Panel panelNums = new Panel(new GridLayout(4, 5));
        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button button0 = new Button("0");
        Button buttonPunto = new Button(".");
        Button buttonC = new Button("C");
        panelNums.add(button1);
        panelNums.add(button2);
        panelNums.add(button4);
        panelNums.add(button5);
        panelNums.add(button3);
        panelNums.add(button6);
        panelNums.add(button7);
        panelNums.add(button8);
        panelNums.add(button9);
        panelNums.add(button0);
        panelNums.add(buttonPunto);
        panelNums.add(buttonC);
        return panelNums;

    }

    public static Panel operadores() {
        Panel panelOper = new Panel(new GridLayout(5, 1));
        Button buttonMas = new Button("+");
        Button buttonMenos = new Button("-");
        Button buttonMult = new Button("*");
        Button buttonDividir = new Button("/");
        Button buttonIgual = new Button("=");
        buttonMas.setPreferredSize(new Dimension(80, 0));
        panelOper.add(buttonMas);
        panelOper.add(buttonMenos);
        panelOper.add(buttonMult);
        panelOper.add(buttonDividir);
        panelOper.add(buttonIgual);
        return panelOper;
    }

    public static Label resultado() {
        Label txtResultado = new Label();
        txtResultado.setFont(new Font("SansSerif", Font.PLAIN, 38));
        return txtResultado;
    }

    /*-------------------------------------- */
    public static void main(String[] args) {
        frame.setLayout(new BorderLayout());

        Label result = resultado();
        Panel nums = grid();
        Panel opers = operadores();

        frame.add(result, BorderLayout.NORTH);
        frame.add(nums, BorderLayout.CENTER);
        frame.add(opers, BorderLayout.EAST);

        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });
    }
}