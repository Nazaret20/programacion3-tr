import java.awt.*;
import java.awt.event.*;

public class Memory extends Frame {

    Memory() {
        setTitle("Memory Game");

        setLayout(new BorderLayout());

        Label lbl = new Label();
        add(lbl, BorderLayout.NORTH);

        Panel pnl = new Panel(new GridLayout(3, 2, 5, 5));
        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();
        Button btn5 = new Button();
        Button btn6 = new Button();
        pnl.add(btn1);
        pnl.add(btn2);
        pnl.add(btn3);
        pnl.add(btn4);
        pnl.add(btn5);
        pnl.add(btn6);
        add(pnl, BorderLayout.CENTER);


        setSize(450, 450);
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
        Memory app = new Memory();
    }
}
