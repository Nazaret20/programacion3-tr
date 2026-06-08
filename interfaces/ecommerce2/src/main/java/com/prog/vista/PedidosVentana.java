package com.prog.vista;

import com.prog.controlador.PedidoGestor;
import com.prog.controlador.ProductoGestor;
import com.prog.controlador.ClienteGestor;
import com.prog.modeloEntidad.Pedido;
import com.prog.modeloEntidad.Producto;
import com.prog.modeloEntidad.Cliente;
import com.prog.modeloEntidad.DetallePedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PedidosVentana extends JPanel {

    private JTable tablaPedidos, tablaCarrito;
    private DefaultTableModel modeloPedidos, modeloCarrito;
    private JComboBox<String> cmbCliente, cmbProducto;
    private JTextField cantidadTxf;
    private JButton btnAgregarCarrito, btnCrearPedido, btnLimpiarCarrito, btnCambiarEstado;
    private JComboBox<String> cmbEstado;

    private PedidoGestor pedidoGestor;
    private ProductoGestor productoGestor;
    private ClienteGestor clienteGestor;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Producto> listaProductos;
    private ArrayList<DetallePedido> carrito;

    public PedidosVentana() {
        pedidoGestor = new PedidoGestor();
        productoGestor = new ProductoGestor();
        clienteGestor = new ClienteGestor();
        listaClientes = clienteGestor.showClientes();
        listaProductos = productoGestor.showProductos();
        carrito = new ArrayList<>();

        initializeComponents();
        setupLayout();
        setupListeners();
        cargarTablaPedidos();
    }

    // Componentes-------------------------------------------------
    private void initializeComponents() {
        // Combo clientes
        cmbCliente = new JComboBox<>();
        for (Cliente cliente : listaClientes) {
            cmbCliente.addItem(cliente.getNombre());
        }

        // Combo productos
        cmbProducto = new JComboBox<>();
        for (Producto producto : listaProductos) {
            cmbProducto.addItem(producto.getNombre());
        }

        cantidadTxf = new JTextField(5);

        // Combo para cambiar el estado de un pedido
        cmbEstado = new JComboBox<>();
        cmbEstado.addItem("pendiente");
        cmbEstado.addItem("procesando");
        cmbEstado.addItem("enviado");
        cmbEstado.addItem("entregado");
        cmbEstado.addItem("cancelado");

        btnAgregarCarrito = new JButton("Agregar al carrito");
        btnCrearPedido = new JButton("Crear pedido");
        btnLimpiarCarrito = new JButton("Limpiar carrito");
        btnCambiarEstado = new JButton("Cambiar estado");

        // Tabla pedidos
        modeloPedidos = new DefaultTableModel();
        modeloPedidos.addColumn("ID");
        modeloPedidos.addColumn("ID Cliente");
        modeloPedidos.addColumn("Fecha");
        modeloPedidos.addColumn("Estado");
        modeloPedidos.addColumn("Total");
        tablaPedidos = new JTable(modeloPedidos);

        // Tabla carrito
        modeloCarrito = new DefaultTableModel();
        modeloCarrito.addColumn("ID Producto");
        modeloCarrito.addColumn("Nombre");
        modeloCarrito.addColumn("Cantidad");
        modeloCarrito.addColumn("Precio Unit");
        modeloCarrito.addColumn("Subtotal");
        tablaCarrito = new JTable(modeloCarrito);
    }

    // Layout----------------------------------------------------
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Panel nuevo pedido arriba
        JPanel panelNuevoPedido = new JPanel(new BorderLayout(5, 5));
        panelNuevoPedido.setBorder(BorderFactory.createTitledBorder("Nuevo pedido"));

        JPanel panelSeleccion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSeleccion.add(new JLabel("Cliente:"));
        panelSeleccion.add(cmbCliente);
        panelSeleccion.add(new JLabel("Producto:"));
        panelSeleccion.add(cmbProducto);
        panelSeleccion.add(new JLabel("Cantidad:"));
        panelSeleccion.add(cantidadTxf);
        panelSeleccion.add(btnAgregarCarrito);

        JScrollPane scrollCarrito = new JScrollPane(tablaCarrito);
        scrollCarrito.setPreferredSize(new Dimension(800, 250));

        JPanel panelBotonesCarrito = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotonesCarrito.add(btnCrearPedido);
        panelBotonesCarrito.add(btnLimpiarCarrito);

        panelNuevoPedido.add(panelSeleccion, BorderLayout.NORTH);
        panelNuevoPedido.add(scrollCarrito, BorderLayout.CENTER);
        panelNuevoPedido.add(panelBotonesCarrito, BorderLayout.SOUTH);

        // Panel pedidos con estado
        JPanel panelPedidos = new JPanel(new BorderLayout(5, 5));
        panelPedidos.setBorder(BorderFactory.createTitledBorder("Pedidos existentes"));

        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstado.add(new JLabel("Estado:"));
        panelEstado.add(cmbEstado);
        panelEstado.add(btnCambiarEstado);

        panelPedidos.add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);
        panelPedidos.add(panelEstado, BorderLayout.SOUTH);

        add(panelNuevoPedido, BorderLayout.NORTH);
        add(panelPedidos, BorderLayout.CENTER);
    }

    // Listeners----------------------------------------------
    private void setupListeners() {
        btnAgregarCarrito.addActionListener(e -> {
            int index = cmbProducto.getSelectedIndex();
            Producto producto = listaProductos.get(index);
            int cantidad = Integer.parseInt(cantidadTxf.getText());

            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId_producto(producto.getId_producto());
            detallePedido.setCantidad(cantidad);
            detallePedido.setPrecio_unit(producto.getPrecio());
            detallePedido.setSubtotal(producto.getPrecio() * cantidad);
            carrito.add(detallePedido);

            modeloCarrito.addRow(new Object[] {
                    producto.getId_producto(),
                    producto.getNombre(),
                    cantidad,
                    producto.getPrecio(),
                    detallePedido.getSubtotal()
            });
            cantidadTxf.setText("");
        });

        btnCrearPedido.addActionListener(e -> {
            if (carrito.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El carrito está vacío");
                return;
            }
            int idCliente = listaClientes.get(cmbCliente.getSelectedIndex()).getId_cliente();
            boolean exito = pedidoGestor.crearPedido(idCliente, carrito);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Pedido creado con éxito");
                carrito.clear();
                modeloCarrito.setRowCount(0);
                cargarTablaPedidos();
            } else {
                JOptionPane.showMessageDialog(this, "No se ha podido crear el pedido");
            }
        });

        btnLimpiarCarrito.addActionListener(e -> {
            carrito.clear();
            modeloCarrito.setRowCount(0);
        });

        btnCambiarEstado.addActionListener(e -> {
            int fila = tablaPedidos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un pedido");
                return;
            }
            Pedido p = new Pedido();
            p.setId_pedido(Integer.parseInt(modeloPedidos.getValueAt(fila, 0).toString()));
            p.setEstado(cmbEstado.getSelectedItem().toString());
            pedidoGestor.cambiarEstado(p);
            cargarTablaPedidos();
        });
    }
    // Método para los listeners-----------------------------------------
    private void cargarTablaPedidos() {
        modeloPedidos.setRowCount(0);
        ArrayList<Pedido> lista = pedidoGestor.showPedidos();
        for (Pedido pedido : lista) {
            modeloPedidos.addRow(new Object[] {
                    pedido.getId_pedido(),
                    pedido.getId_cliente(),
                    pedido.getFecha(),
                    pedido.getEstado(),
                    pedido.getTotal()
            });
        }
    }
}