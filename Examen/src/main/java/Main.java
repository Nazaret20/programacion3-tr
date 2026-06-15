import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
    private ClinicaBD gestor;
    // MENU
    private JMenuBar menu;
    private JMenu mascotas, ingresos;
    private JMenuItem registrarMascota, nuevoIngreso, darAlta;

    // TABLA 1
    private DefaultTableModel traductor;
    private JTable tabla;
    private JScrollPane scroll;
    private JPanel panelTabla;

    // TABLA 2
    private DefaultTableModel traductor2;
    private JTable tabla2;
    private JScrollPane scroll2;
    private JPanel panelTabla2;

    // ESTADO
    private JLabel estado;

    public Main() {
        this.gestor = new ClinicaBD();
        try {
            gestor.conectarBD();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Error al conectar la BD" + e.getMessage());
        }

        initializeComponents();
        setupLayout();
        setupListeners();
        actualizarTablaMascotas();
        actualizarTablaIngresos();
    }

    /*------------------------MAIN------------------------- */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Main ui = new Main();
            ui.setVisible(true);
        });
    }

    /*------------------------COMPONENTES------------------------- */
    private void initializeComponents() {
        // MENU
        menu = new JMenuBar();

        mascotas = new JMenu("Mascotas");
        ingresos = new JMenu("Ingresos");

        registrarMascota = new JMenuItem("Registrar mascota");
        nuevoIngreso = new JMenuItem("Nuevo ingreso");
        darAlta = new JMenuItem("Dar de alta");

        // TABLA 1
        traductor = new DefaultTableModel();
        String[] cols = { "Id", "Nombre", "Especie", "Propietario", "Peso", "Ingresada" };
        for (String col : cols) {
            traductor.addColumn(col);
        }
        tabla = new JTable(traductor);
        scroll = new JScrollPane(tabla);
        panelTabla = new JPanel();

        // TABLA 2
        traductor2 = new DefaultTableModel();
        String[] cols2 = { "Id", "Id mascota", "Descripción", "Coste base", "Total IVA" };
        for (String col : cols2) {
            traductor2.addColumn(col);
        }
        tabla2 = new JTable(traductor2);
        scroll2 = new JScrollPane(tabla2);
        panelTabla2 = new JPanel();

        // ESTADO
        estado = new JLabel("Estado");

    }

    /*------------------------------LAYOUT------------------------------ */
    private void setupLayout() {
        setTitle("Clínica Veterinaria Digi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // MENU
        mascotas.add(registrarMascota);
        ingresos.add(nuevoIngreso);
        ingresos.add(darAlta);

        menu.add(mascotas);
        menu.add(ingresos);
        setJMenuBar(menu);
        add(menu);

        // TABLA 1
        panelTabla.add(scroll);
        add(panelTabla, BorderLayout.NORTH);

        // TABLA 2
        panelTabla2.add(scroll2);
        add(panelTabla2, BorderLayout.CENTER);

        // ESTADO
        add(estado, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /*------------------------------LISTENERS------------------------------ */
    private void setupListeners() {
        registrarMascota.addActionListener(e -> {
            accionRegistrarMascota();
        });

        nuevoIngreso.addActionListener(e -> {
            accionNuevoIngreso();
        });

        darAlta.addActionListener(e -> {
            accionDarDeAlta();
        });

    }

    /*------------------------ACCIONES LISTENER------------------------- */
    private void accionRegistrarMascota() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre de la mascota");
            String especie = JOptionPane.showInputDialog("Especie");
            String propietario = JOptionPane.showInputDialog("Propietario");
            String peso = JOptionPane.showInputDialog("Peso");
            double pesoParse = Double.parseDouble(peso);

            gestor.registrarMascota(nombre, especie, propietario, pesoParse);

            estado.setText("Mascota registrada");
            actualizarTablaMascotas();
            actualizarTablaIngresos();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "Error al registrar mascota: " + e.getMessage());
        }
    }

    private void accionNuevoIngreso() {
        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Id de la mascota"));
            String descripcion = JOptionPane.showInputDialog("Descripción");
            double costeBase = Double.parseDouble(JOptionPane.showInputDialog("Coste base"));

            boolean ingresada = gestor.estaIngresada(idMascota);
            if (ingresada) {
                gestor.registrarIngreso(idMascota, descripcion, costeBase);
                // calcular coste

            } else {
                estado.setText("Mascota ya está ingresada");
            }

            actualizarTablaMascotas();
            actualizarTablaIngresos();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "Error al registrar mascota: " + e.getMessage());
        }
    }

    private void accionDarDeAlta() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Id de mascota"));
            gestor.darDeAlta(id);
            estado.setText("Mascota dada de alta");
            actualizarTablaIngresos();
            actualizarTablaMascotas();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "Error al dar de alta: " + e.getMessage());

        }
    }

    /*------------------------------ACTUALIZAR------------------------------ */
    private void actualizarTablaMascotas() {
        try {
            traductor.setRowCount(0);
            Object[][] datos = gestor.obtenerMascotas();
            for (Object[] fila : datos) {
                traductor.addRow(fila);
            }

        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }

    private void actualizarTablaIngresos() {
        try {
            traductor2.setRowCount(0);
            Object[][] datos = gestor.obtenerIngresos();
            for (Object[] fila : datos) {
                traductor2.addRow(fila);
            }

        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }
}