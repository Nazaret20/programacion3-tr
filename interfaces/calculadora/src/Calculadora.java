
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculadora {
    public static void main(String[] args) {
        Frame frame = new Frame("Calculadora");
        TextArea txtResultado = new TextArea();
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
        Button buttonMas = new Button("+");
        Button buttonMenos = new Button("-");
        Button buttonMult = new Button("*");
        Button buttonDividir = new Button("/");
        Button buttonIgual = new Button("=");

        frame.setLayout(new GridLayout(4, 4));

        frame.add(txtResultado);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
        frame.add(button0);
        frame.add(buttonPunto);
        frame.add(buttonC);
        frame.add(buttonMas);
        frame.add(buttonMenos);
        frame.add(buttonMult);
        frame.add(buttonDividir);
        frame.add(buttonIgual);

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
        