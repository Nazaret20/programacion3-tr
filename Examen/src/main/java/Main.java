import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
    private VideoclubBD videoclubG;
    private JMenuBar menu;
    private JMenu menuInternoPelis, menuInternoAlq;
    private JMenuItem anadirPeli, catalogoPeli, nuevoAlq, devolver, verAlq;
    private JTable tabla;
    private DefaultTableModel traductorTabla;
    private JLabel estadoLbl;
    private JDialog anadir;
    JDesktopPane jdp = new JDesktopPane();

    Main() {
        videoclubG = new VideoclubBD();
        initializeComponents();
        setupLayout();
    }

    // --------------------------------MAIN--------------------------------------------
    public static void main(String[] args) {
        Main ventanaPrincipal = new Main();
    }

    // -------------------------------------UI------------------------------------
    public void initializeComponents() {
        menu = new JMenuBar();
        menuInternoPelis = new JMenu("Películas");
        menuInternoAlq = new JMenu("Alquileres");
        anadirPeli = new JMenuItem("Añadir película");
        catalogoPeli = new JMenuItem("Ver catálogo");
        nuevoAlq = new JMenuItem("Nuevo alquiler");
        devolver = new JMenuItem("Devolver ");
        verAlq = new JMenuItem("Ver alquileres");

        tabla = new JTable();
        traductorTabla = new DefaultTableModel();
        estadoLbl = new JLabel();
    }

    public void setupLayout() {
        setTitle("VideoClub Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // Añadir el menú en north
        menuInternoPelis.add(anadirPeli);
        menuInternoPelis.add(catalogoPeli);

        menuInternoAlq.add(nuevoAlq);
        menuInternoAlq.add(devolver);
        menuInternoAlq.add(verAlq);

        menu.add(menuInternoPelis);
        menu.add(menuInternoAlq);

        add(menu, BorderLayout.NORTH);

        // Panel de tabla center
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);

        add(panelTabla, BorderLayout.CENTER);

        estadoLbl = new JLabel("Estado actualizado: ");
        add(estadoLbl, BorderLayout.SOUTH);

        anadirPeli.addActionListener(e -> {
            JInternalFrame jif = new JInternalFrame("Sin título...", true, true, true, true);
            JTextArea jta = new JTextArea();
            jif.add(jta);
            jdp.add(jif);
            jif.setSize(200, 200);
            jif.setVisible(true);
            accionAnyadirPelicula();
        });

        setVisible(true);
        pack();

    }

    public void actualizarTabla() {

        Object[][] data = videoclubG.executeQuery(query);
        if (data != null) {
            traductorTabla.setDataVector(data, videoclubG.getColumnNames());
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionAnyadirPelicula() {

    }
}