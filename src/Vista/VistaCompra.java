/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;
import Controlador.CompraControlador;
import Modelo.Compra;
import Controlador.ControladorDetalleCompra;
import Modelo.DetalleCompra;
import Controlador.ControladorEmpleado;
import Modelo.Empleado;
import Controlador.ProductoControlador;
import Modelo.Producto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
/**
 *
 * @author Jassin Alexis
 */
public class VistaCompra extends javax.swing.JPanel {
    private final CompraControlador compraControlador;
    private final ControladorDetalleCompra controladorDetalleCompra;
    private final ControladorEmpleado controladorEmpleado;
    private final ProductoControlador productoControlador;

    private Integer idEmpleadoSeleccionado = null;
    private Integer idProductoSeleccionado = null;
    
    private Timer timer; // Variable de instancia para el Timer
    private boolean horabd = false;
    /**
     * Creates new form VistaCompra
     */
    public VistaCompra() {
        initComponents();
        this.compraControlador = new CompraControlador();
        this.controladorDetalleCompra = new ControladorDetalleCompra();
        this.controladorEmpleado = new ControladorEmpleado();
    this.productoControlador = new ProductoControlador();
    eventoComboProductos();
    
    selectorfechaCompra.setDate(new Date());
    ((JTextField) selectorfechaCompra.getDateEditor().getUiComponent()).setEditable(false);

    // Limpiar las filas vacías de tablaDetalles
    DefaultTableModel modelDetalles = (DefaultTableModel) tablaDetalles.getModel();
    modelDetalles.setRowCount(0);

    cargarDatosTablaCompras();
    cargarEmpleados();
    cargarProductos();
    actualizarHora();
    }
    
    private void limpiar() {
    textBuscar.setText("");
    idEmpleadoSeleccionado = null;
    selectorfechaCompra.setDate(new Date());

    // Limpiar la tabla de detalles
    tablaDetalles.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID Producto", "Producto", "Precio Unitario", "Cantidad", "Subtotal"}));

    cargarDatosTablaCompras();
    cargarEmpleados();
    cargarProductos();

    jButtonEliminar.setEnabled(true);
    jButtonGuardarCompra.setEnabled(true);

    horabd = false; // Restablece para mostrar hora actual
    actualizarHora(); // Vuelve a iniciar el timer
}
    
    private void actualizarHora() {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone("America/Managua"));

    // Detener el timer anterior si existe
    if (timer != null) {
        timer.stop();
    }

    if (horabd) {
        return; // No actualiza la hora si está cargada desde la base de datos
    }

    timer = new Timer(1000, e -> {
        Date now = new Date();
        hora.setText(sdf.format(now));
    });
    timer.start();
}
    
    private void cargarDatosTablaCompras() {
    List<Compra> compras = compraControlador.obtenerTodasCompras();

    if (compras != null) {

        DefaultTableModel model = (DefaultTableModel) jTableCompra.getModel();
        model.setRowCount(0);

        for (Compra c : compras) {

            Empleado empleado = controladorEmpleado.obtenerEmpleadoPorId(c.getIdEmpleado());
            String nombreEmpleado = empleado.getPrimerNombre() + " " + empleado.getPrimerApellido();

            Object[] row = {
                c.getIdCompra(),
                c.getFechaCompra(),
                nombreEmpleado,
                c.getTotalCompra()
            };
            model.addRow(row);
        }
    }
}
    
    private void cargarEmpleados() {
    try {
        // Obtener las categorías desde el controlador
        List<Empleado> empleados = controladorEmpleado.obtenerTodosEmpleados();

        // Limpiar el combo box por si tiene datos
        comboEmpleados.removeAllItems();

        // Agregar cada categoría al combo box
        for (Empleado e : empleados) {
            comboEmpleados.addItem(e.getPrimerNombre() + " " +e.getPrimerApellido()); // Mostrar el nombre
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
                "Error al cargar los empleados: " + e.getMessage());
    }
}
    
    private void cargarProductos() {
    try {
        // Obtener las categorías desde el controlador
        List<Producto> productos = productoControlador.obtenerTodosProductos();

        // Limpiar el combo box por si tiene datos
        comboProductos.removeAllItems();

        // Agregar cada categoría al combo box
        for (Producto p : productos) {
            comboProductos.addItem(p.getNombreProducto()); // Mostrar el nombre
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
                "Error al cargar los productos: " + e.getMessage());
    }
    }
    
    private void eventoComboProductos() {
    comboProductos.addActionListener(e -> {
        // Obtener el índice seleccionado
        int indiceSeleccionado = comboProductos.getSelectedIndex();

        if (indiceSeleccionado >= 0) { // Verificar que se haya seleccionado algo
            try {
                // Obtener la lista de categorías desde el controlador o memoria
                List<Producto> productos = productoControlador.obtenerTodosProductos();

                // Obtener el objeto de categoría correspondiente al índice seleccionado
                Producto productoSeleccionado = productos.get(indiceSeleccionado);

                // Actualizar la variable global con el ID de la categoría seleccionada
                idProductoSeleccionado = productoSeleccionado.getIdProducto();
                
                // Actualizar el campo textPrecio con el precio unitario del producto
                textPrecio.setText(String.valueOf(productoSeleccionado.getPrecioUnitario()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al seleccionar categoría: " + ex.getMessage());
            }
        }
    });
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textPrecio = new javax.swing.JTextField();
        textCantidad = new javax.swing.JTextField();
        jButtonAgregar = new javax.swing.JButton();
        jButtonGuardarCompra = new javax.swing.JButton();
        textBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboEmpleados = new javax.swing.JComboBox<>();
        selectorfechaCompra = new com.toedter.calendar.JDateChooser();
        comboProductos = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCompra = new javax.swing.JTable();
        jButtonEliminar = new javax.swing.JButton();
        jButtonQuitarDetalle = new javax.swing.JButton();
        jButton3Eliminar3 = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();

        textPrecio.setEnabled(false);
        textPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPrecioActionPerformed(evt);
            }
        });

        textCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCantidadActionPerformed(evt);
            }
        });

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregaraccionBotonAgregar(evt);
            }
        });

        jButtonGuardarCompra.setText("Guardar Compra");
        jButtonGuardarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarCompraaccionBotonGuardar(evt);
            }
        });

        textBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBuscarActionPerformed(evt);
            }
        });
        textBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textBuscarKeyTyped(evt);
            }
        });

        jLabel4.setText("Empleado");

        jLabel5.setText("Fecha");

        hora.setText("00:00:00");

        jLabel7.setText("Producto");

        jLabel8.setText("Precio");

        jLabel9.setText("Buscar");

        jLabel10.setText("Cantidad");

        comboEmpleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado 1", "Empleado 2", "Empleado 3", "Empleado 4" }));

        comboProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Producto", "Producto", "Precio Unitario", "Cantidad", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaDetalles);

        jTableCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Compra", "Fecha / Hora", "Empleado", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCompraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableCompra);

        jButtonEliminar.setText("Eliminar Compra");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminaraccionBotonEliminar(evt);
            }
        });

        jButtonQuitarDetalle.setText("Quitar Detalle");
        jButtonQuitarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionBotonQuitarDetalle(evt);
            }
        });

        jButton3Eliminar3.setText("Limpiar");
        jButton3Eliminar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3Eliminar3accionBotonLimpiar(evt);
            }
        });

        jButtonActualizar.setText("Actualizar Compra");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizaraccionBotonActualizar(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3Eliminar3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButtonGuardarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(comboEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectorfechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hora)))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(85, 85, 85)
                                .addComponent(jLabel8)))
                        .addGap(18, 29, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAgregar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                        .addComponent(jScrollPane3)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 409, Short.MAX_VALUE)
                            .addComponent(jButtonQuitarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(hora)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAgregar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(comboProductos)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectorfechaCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonQuitarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3Eliminar3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecioActionPerformed

    private void textCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCantidadActionPerformed

    private void jButtonAgregaraccionBotonAgregar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregaraccionBotonAgregar
        // TODO add your handling code here:
      try {
            // Obtener el índice seleccionado del comboProductos
            int indiceSeleccionado = comboProductos.getSelectedIndex();
            if (indiceSeleccionado < 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
              // Obtener la lista de productos
            List<Producto> productos = productoControlador.obtenerTodosProductos();
            Producto productoSeleccionado = productos.get(indiceSeleccionado);

            // Obtener el precio unitario del producto
            float precioUnitario = productoSeleccionado.getPrecioUnitario();

            // Obtener la cantidad ingresada
            String cantidadStr = textCantidad.getText().trim();
            if (cantidadStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadStr);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcular el subtotal
            float subtotal = precioUnitario * cantidad;

            // Agregar los datos a la tabla tablaDetalles
            DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
            Object[] row = {
                productoSeleccionado.getIdProducto(),
                productoSeleccionado.getNombreProducto(),
                precioUnitario,
                cantidad,
                subtotal
            };
            model.addRow(row);
            
             // Limpiar los campos después de agregar
            textCantidad.setText("");
            textPrecio.setText("");
            cargarProductos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAgregaraccionBotonAgregar

    private void jButtonGuardarCompraaccionBotonGuardar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarCompraaccionBotonGuardar
        // TODO add your handling code here:
        try {
            // Obtener el índice seleccionado de empleados
            int indiceEmpleado = comboEmpleados.getSelectedIndex();
            if (indiceEmpleado < 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la lista de  empleados
            List<Empleado> empleados = controladorEmpleado.obtenerTodosEmpleados();
            int idEmpleado = empleados.get(indiceEmpleado).getIdEmpleado();

            // Obtener la fecha seleccionada
            Date fechaVenta = selectorfechaCompra.getDate();
            if (fechaVenta == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener los detalles de la tabla tablaDetalles
            DefaultTableModel modelDetalles = (DefaultTableModel) tablaDetalles.getModel();
            int rowCount = modelDetalles.getRowCount();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(this, "Agregue al menos un producto a la compra.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear lista de detalles y calcular total
            List<DetalleCompra> detalles = new ArrayList<>();
            float totalVenta = 0;
            for (int i = 0; i < rowCount; i++) {
                int idProducto = (int) modelDetalles.getValueAt(i, 0); // ID Producto como Integer
                float precioUnitario = ((Number) modelDetalles.getValueAt(i, 2)).floatValue(); // Precio Unitario como Float
                int cantidad = (int) modelDetalles.getValueAt(i, 3); // Cantidad como Integer
                float subtotal = ((Number) modelDetalles.getValueAt(i, 4)).floatValue(); // Subtotal como Float

                // Crear objeto DetalleCompra
                DetalleCompra detalle = new DetalleCompra();
                detalle.setProducto(idProducto);
                detalle.setCantidad(cantidad);
                detalle.setPrecioUnitario(precioUnitario);
                detalles.add(detalle);

                totalVenta += subtotal;
            }

            // Crear y guardar la compra
            compraControlador.crearCompra(idEmpleado, (java.sql.Date) fechaVenta, totalVenta, detalles);

            limpiar();

            // Recargar la tabla de compras
            cargarDatosTablaCompras();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGuardarCompraaccionBotonGuardar

    private void textBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBuscarActionPerformed
        // TODO add your handling code here:                                   
        String textoBusqueda = textBuscar.getText().trim().toLowerCase();
        List<Compra> compras = compraControlador.obtenerTodasCompras();

        DefaultTableModel modelo = (DefaultTableModel) jTableCompra.getModel();
        modelo.setRowCount(0);

        if (compras != null) {
            for (Compra c : compras) {
                Empleado empleado = controladorEmpleado.obtenerEmpleadoPorId(c.getIdEmpleado());
                String nombreEmpleado = empleado.getPrimerNombre() + " " + empleado.getPrimerApellido();
                
                if (textoBusqueda.isEmpty() ||
                    nombreEmpleado.toLowerCase().contains(textoBusqueda)) {
                    Object[] fila = {
                        c.getIdCompra(),
                        c.getFechaCompra(),
                        nombreEmpleado,
                        c.getTotalCompra()
                    };
                    modelo.addRow(fila);
                }
            }
        }
    }//GEN-LAST:event_textBuscarActionPerformed

    private void textBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyTyped
  
    }//GEN-LAST:event_textBuscarKeyTyped

    private void jTableCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCompraMouseClicked
        // TODO add your handling code here:
        // Verificar si es un doble clic
        if (evt.getClickCount() == 2) {
            try {
                
                jButtonEliminar.setEnabled(false);
                jButtonGuardarCompra.setEnabled(false);
                
                // Obtener el índice de la fila seleccionada en tablaCompras
                int filaSeleccionada = jTableCompra.getSelectedRow();
                if (filaSeleccionada == -1) {
                    return; // No hacer nada si no hay fila seleccionada
                }

                // Obtener el idVenta de la fila seleccionada
                DefaultTableModel modelVentas = (DefaultTableModel) jTableCompra.getModel();
                int idCompra = (int) modelVentas.getValueAt(filaSeleccionada, 0);

                // Obtener la venta seleccionada para acceder a sus datos
                List<Compra> compras = compraControlador.obtenerTodasCompras();
                Compra compraSeleccionada = null;
                for (Compra c : compras) {
                    if (c.getIdCompra() == idCompra) {
                        compraSeleccionada = c;
                        break;
                    }
                }
                if (compraSeleccionada == null) {
                    JOptionPane.showMessageDialog(this, "Compra no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Cargar empleado en comboEmpleados
                List<Empleado> empleados = controladorEmpleado.obtenerTodosEmpleados();
                int indiceEmpleado = -1;
                for (int i = 0; i < empleados.size(); i++) {
                    if (empleados.get(i).getIdEmpleado() == compraSeleccionada.getIdEmpleado()) {
                        indiceEmpleado = i;
                        break;
                    }
                }
                if (indiceEmpleado != -1) {
                    idEmpleadoSeleccionado = compraSeleccionada.getIdEmpleado();
                    comboEmpleados.setSelectedIndex(indiceEmpleado);
                } else {
                    JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Detener el timer actual
                if (timer != null) {
                    timer.stop();
                }
                
                // Asignar la hora al label
                horabd = true;
                java.text.SimpleDateFormat horaFormato = new java.text.SimpleDateFormat("HH:mm:ss");
                String horaCompra = horaFormato.format(compraSeleccionada.getFechaCompra());
                hora.setText(horaCompra); // Ajusta 'horaLabel' al nombre real de tu JLabel

                // Cargar la fecha en selectorfechaContratacion
                selectorfechaCompra.setDate(compraSeleccionada.getFechaCompra());

                // Limpiar y cargar los detalles en tablaDetalles
                DefaultTableModel modelDetalles = (DefaultTableModel) tablaDetalles.getModel();
                modelDetalles.setRowCount(0);

                List<DetalleCompra> detalles = controladorDetalleCompra.obtenerTodasDetallesCompras();
                if (detalles != null) {
                    for (DetalleCompra detalle : detalles) {
                        if (detalle.getCompra() == idCompra) {
                            Producto producto = productoControlador.obtenerProductoPorId(detalle.getProducto());
                            String nombreProducto = (producto != null) ? producto.getNombreProducto() : "Desconocido";

                            Object[] row = {
                                detalle.getProducto(),
                                nombreProducto,
                                detalle.getPrecioUnitario(),
                                detalle.getCantidad(),
                                detalle.getPrecioUnitario() * detalle.getCantidad() // Subtotal
                            };
                            modelDetalles.addRow(row);
                        }
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar los datos de la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jTableCompraMouseClicked

    private void jButtonEliminaraccionBotonEliminar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminaraccionBotonEliminar
        // TODO add your handling code here:
        try {
            // Obtener el índice de la fila seleccionada en tablaVentas
            int filaSeleccionada = jTableCompra.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una venta para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el idVenta de la fila seleccionada
            DefaultTableModel model = (DefaultTableModel) jTableCompra.getModel();
            int idVenta = (int) model.getValueAt(filaSeleccionada, 0);

            // Confirmar con el usuario antes de eliminar
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar la venta con ID " + idVenta + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Eliminar la venta
                controladorVenta.eliminarVenta(idVenta);

                // Recargar la tabla de ventas
                cargarDatosTablaCompras();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEliminaraccionBotonEliminar

    private void accionBotonQuitarDetalle(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionBotonQuitarDetalle
        // TODO add your handling code here:
        try {
            // Obtener el índice de la fila seleccionada en tablaDetalles
            int filaSeleccionada = tablaDetalles.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un detalle para quitar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Eliminar la fila seleccionada del modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
            model.removeRow(filaSeleccionada);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al quitar el detalle: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_accionBotonQuitarDetalle

    private void jButton3Eliminar3accionBotonLimpiar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3Eliminar3accionBotonLimpiar
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jButton3Eliminar3accionBotonLimpiar

    private void jButtonActualizaraccionBotonActualizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizaraccionBotonActualizar
        // TODO add your handling code here:                          
        try {
            // Obtener el índice de la fila seleccionada en tablaVentas
            int filaSeleccionada = jTableCompra.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una compra para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el idVenta de la fila seleccionada
            DefaultTableModel modelVentas = (DefaultTableModel) jTableCompra.getModel();
            int idCompra = (int) modelVentas.getValueAt(filaSeleccionada, 0);

            // Obtener el índice seleccionado de empleados
            int indiceEmpleado = comboEmpleados.getSelectedIndex();
            if ( indiceEmpleado < 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la lista de empleados
            List<Empleado> empleados = controladorEmpleado.obtenerTodosEmpleados();
            int idEmpleado = empleados.get(indiceEmpleado).getIdEmpleado();

            // Obtener la fecha seleccionada
            Date fechaVenta = selectorfechaCompra.getDate();
            if (fechaVenta == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener los detalles de la tabla tablaDetalles
            DefaultTableModel modelDetalles = (DefaultTableModel) tablaDetalles.getModel();
            int rowCount = modelDetalles.getRowCount();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(this, "Agregue al menos un producto a la compra.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcular el total de la compra
            float totalCompra = 0;
            for (int i = 0; i < rowCount; i++) {
                totalCompra += ((Number) modelDetalles.getValueAt(i, 4)).floatValue(); // Suma los subtotales
            }

            // Actualizar la compra principal
            compraControlador.actualizarCompra(idCompra, idEmpleado, (java.sql.Date) fechaVenta, totalCompra);

            // Eliminar los detalles antiguos de la compra
            List<DetalleCompra> detallesAntiguos = controladorDetalleCompra();
            if (detallesAntiguos != null) {
                for (DetalleCompra detalle : detallesAntiguos) {
                    if (detalle.getCompra() == idCompra) {
                        controladorDetalleCompra.eliminarDetalleCompra(detalle.getIdDetalleCompra());
                    }
                }
                }
            // Insertar los nuevos detalles
            for (int i = 0; i < rowCount; i++) {
                int idProducto = (int) modelDetalles.getValueAt(i, 0);
                float precioUnitario = ((Number) modelDetalles.getValueAt(i, 2)).floatValue();
                int cantidad = (int) modelDetalles.getValueAt(i, 3);

                // Crear y guardar el nuevo detalle
                DetalleCompra detalle = new DetalleCompra();
                detalle.setCompra(idCompra);
                detalle.setProducto(idProducto);
                detalle.setCantidad(cantidad);
                detalle.setPrecioUnitario(precioUnitario);
                controladorDetalleCompra.crearDetalleCompra(idCompra, idProducto, cantidad, precioUnitario);
            }

            // Limpiar la tabla de detalles y el formulario
            tablaDetalles.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID Producto", "Producto", "Precio Unitario", "Cantidad", "Subtotal"}));
            limpiar();

            // Recargar la tabla de compras
            cargarDatosTablaCompras();

            // Habilitar botones nuevamente
            jButtonEliminar.setEnabled(true);
            jButtonGuardarCompra.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonActualizaraccionBotonActualizar


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboEmpleados;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JLabel hora;
    private javax.swing.JButton jButton3Eliminar3;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardarCompra;
    private javax.swing.JButton jButtonQuitarDetalle;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableCompra;
    private com.toedter.calendar.JDateChooser selectorfechaCompra;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextField textCantidad;
    private javax.swing.JTextField textPrecio;
    // End of variables declaration//GEN-END:variables
}
