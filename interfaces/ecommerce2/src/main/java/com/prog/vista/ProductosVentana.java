package com.prog.vista;

import com.prog.controlador.ProductoGestor;
import com.prog.controlador.CategoriaGestor;
import com.prog.modeloEntidad.Producto;
import com.prog.modeloEntidad.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class ProductosVentana extends JPanel {

    private JTextField buscarTxf, nombreTxf, descripcionTxf, precioTxf, stockTxf;
    private JComboBox<String> cmbCategoria, cmbBuscarCategoria;
    private JButton btnBuscar, btnGuardar, btnEliminar, btnLimpiar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private ProductoGestor productoGestor;
    private CategoriaGestor categoriaGestor;
    private ArrayList<Categoria> listaCategorias;

    public ProductosVentana() {
        productoGestor = new ProductoGestor();
        categoriaGestor = new CategoriaGestor();
        listaCategorias = categoriaGestor.showCategorias();

        initializeComponents();
        setupLayout();
        setupListeners();
        cargarTabla();
    }

    // Componentes-------------------------------------------------
    private void initializeComponents() {
        buscarTxf = new JTextField(20);
        nombreTxf = new JTextField(20);
        descripcionTxf = new JTextField(20);
        precioTxf = new JTextField(20);
        stockTxf = new JTextField(20);

        cmbCategoria = new JComboBox<>();
        for (Categoria cateogria : listaCategorias) {
            cmbCategoria.addItem(cateogria.getNombre());
        }

        cmbBuscarCategoria = new JComboBox<>();
        cmbBuscarCategoria.addItem("Todas");
        for (Categoria categoria : listaCategorias) {
            cmbBuscarCategoria.addItem(categoria.getNombre());
        }

        btnBuscar = new JButton("Buscar");
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Categoria");
        modeloTabla.addColumn("Stock");
        tabla = new JTable(modeloTabla);
    }

    // Layout----------------------------------------------------
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(buscarTxf);
        panelBusqueda.add(new JLabel("Categoria:"));
        panelBusqueda.add(cmbBuscarCategoria);
        panelBusqueda.add(btnBuscar);

        // Formulario
        JPanel panelFormulario = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del producto"));
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(nombreTxf);
        panelFormulario.add(new JLabel("Descripcion:"));
        panelFormulario.add(descripcionTxf);
        panelFormulario.add(new JLabel("Precio:"));
        panelFormulario.add(precioTxf);
        panelFormulario.add(new JLabel("Stock:"));
        panelFormulario.add(stockTxf);
        panelFormulario.add(new JLabel("Categoria:"));
        panelFormulario.add(cmbCategoria);

        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnEliminar);
        panelFormulario.add(btnLimpiar);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(800, 200));

        add(panelBusqueda, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);
    }

    // Listeners----------------------------------------------
    private void setupListeners() {
        btnBuscar.addActionListener(e -> {
            String texto = buscarTxf.getText();
            int indexCategoria = cmbBuscarCategoria.getSelectedIndex();

            if (indexCategoria == 0) {
                if (texto.isEmpty()) {
                    cargarTabla();
                } else {
                    cargarDatosEnTabla(productoGestor.buscarPorNombre(texto));
                }
            } else {
                int idCategoria = listaCategorias.get(indexCategoria - 1).getId_categoria();
                cargarDatosEnTabla(productoGestor.buscarPorCategoria(idCategoria));
            }
        });

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                nombreTxf.setText(modeloTabla.getValueAt(fila, 1).toString());
                descripcionTxf.setText(modeloTabla.getValueAt(fila, 2).toString());
                precioTxf.setText(modeloTabla.getValueAt(fila, 3).toString());
                cmbCategoria.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
                stockTxf.setText(modeloTabla.getValueAt(fila, 5).toString());
            }
        });

        btnGuardar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            Producto producto = new Producto();
            producto.setNombre(nombreTxf.getText());
            producto.setDescripcion(descripcionTxf.getText());
            producto.setPrecio(Double.parseDouble(precioTxf.getText()));
            producto.setStock(Integer.parseInt(stockTxf.getText()));
            producto.setId_categoria(listaCategorias.get(cmbCategoria.getSelectedIndex()).getId_categoria());

            if (fila == -1) {
                productoGestor.insertProducto(producto);
            } else {
                producto.setId_producto(Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString()));
                productoGestor.updateProdcuto(producto);
            }
            limpiarFormulario();
            cargarTabla();
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona el producto que quieres eliminar");
                return;
            }
            productoGestor.deleteProducto(Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString()));
            limpiarFormulario();
            cargarTabla();
        });

        btnLimpiar.addActionListener(e -> limpiarFormulario());
    }

    // Métodos para los listeners-----------------------------------------
    private void cargarTabla() {
        cargarDatosEnTabla(productoGestor.showProductos());
    }

    private void cargarDatosEnTabla(ArrayList<Producto> lista) {
        modeloTabla.setRowCount(0);
        for (Producto producto : lista) {
            String nombreCategoria = "";
            for (Categoria categoria : listaCategorias) {
                if (categoria.getId_categoria() == producto.getId_categoria()) {
                    nombreCategoria = categoria.getNombre();
                    break;
                }
            }
            String stockTexto = String.valueOf(producto.getStock());
            if (producto.getStock() < 5) {
                stockTexto = producto.getStock() + "¡Alerta!";
            }
            modeloTabla.addRow(new Object[] {
                    producto.getId_producto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    nombreCategoria,
                    stockTexto
            });
        }
    }

    private void limpiarFormulario() {
        nombreTxf.setText("");
        descripcionTxf.setText("");
        precioTxf.setText("");
        stockTxf.setText("");
        cmbCategoria.setSelectedIndex(0);
        tabla.clearSelection();
    }
}