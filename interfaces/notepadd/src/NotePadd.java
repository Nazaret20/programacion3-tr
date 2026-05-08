import java.awt.*;
import java.awt.event.*;

public class NotePadd extends Frame {
    NotePadd() {
        setTitle("NotePadd AWT");

        setLayout(new BorderLayout());

        MenuBar menuBar = new MenuBar();
        Menu items = new Menu("Archivo");

        MenuItem newI = new MenuItem("Abrir");
        MenuItem saveI = new MenuItem("Guardar");
        MenuItem exitI = new MenuItem("Salir");

        items.add(newI);
        items.add(saveI);
        items.addSeparator();
        items.add(exitI);

        menuBar.add(items);
        setMenuBar(menuBar);

        TextArea txtArea = new TextArea();
        add(txtArea, BorderLayout.CENTER);

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
        NotePadd app = new NotePadd();
    }
}
