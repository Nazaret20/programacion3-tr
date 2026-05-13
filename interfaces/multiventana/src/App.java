import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame {
    App() {
         try {
            // Establecer el Look & Feel a Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Multiventana");

        setLayout(new BorderLayout());

        JMenuBar barra = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
        JMenuItem nuevo = new JMenuItem("Nuevo");
        JMenuItem abrir = new JMenuItem("Abrir");
        JMenuItem guardar = new JMenuItem("Guardar");
        JSeparator separador = new JSeparator();
        JMenuItem salir = new JMenuItem("Salir");

        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(separador);
        archivo.add(salir);

        barra.add(archivo);

        add(barra, BorderLayout.NORTH);

        JDesktopPane multi = new JDesktopPane();
        multi.setSize(200, 200);
        multi.setVisible(true);
        add(multi, BorderLayout.CENTER);
        
        

        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }
    public static void main(String[] args) throws Exception {
        App app = new App();
    }
}