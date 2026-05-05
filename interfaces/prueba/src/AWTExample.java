
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWTExample {
    public static void main(String[] args) {
        Frame frame = new Frame("Ejemplo AWT");
        Button button = new Button("Pulsa aquí");

        frame.add(button);
        frame.setSize(300, 200);
        frame.setVisible(true);

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Archivo");

        MenuItem newItem = new MenuItem("Nuevo");
        MenuItem openItem = new MenuItem("Abrir");
        MenuItem exitItem = new MenuItem("Salir");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        frame.setMenuBar(menuBar);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });
    }
}
