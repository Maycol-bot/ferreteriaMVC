/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControladorVenta;
import Modelo.Venta;
import Controlador.ControladorDetalleVenta;
import Modelo.DetalleVenta;
import Controlador.ControladorCliente;
import Modelo.Cliente;
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
 * @author Maryi 
 */
public class VistaVentas extends javax.swing.JPanel {
    
    
    private final ControladorVenta controladorVenta;
    private final ControladorDetalleVenta controladorDetalleVenta;
    private final ControladorEmpleado controladorEmpleado;
    private final ControladorCliente controladorCliente;
    private final ProductoControlador productoControlador;
    private Integer idEmpleadoSeleccionado = null;
    private Integer idClienteSeleccionado = null;
    private Integer idProductoSeleccionado = null;
    private Timer timer; // Variable de instancia para el Timer
    private boolean horabd = false;

    /**
     * Creates new form VistaCategorias
     */
    public VistaVentas() {
        initComponents();
         this.controladorVenta = new ControladorVenta();
    this.controladorDetalleVenta = new ControladorDetalleVenta();
    this.controladorEmpleado = new ControladorEmpleado();
    this.controladorCliente = new ControladorCliente();
    this.productoControlador = new ProductoControlador();
    eventoComboProductos();
    
     selectorfechaVenta.setDate(new Date());
    ((JTextField) selectorfechaVenta.getDateEditor().getUiComponent()).setEditable(false);
    
    // Limpiar las filas vacías de tablaDetalles
    DefaultTableModel modelDetalles = (DefaultTableModel) tablaDetalles.getModel();
    modelDetalles.setRowCount(0);
    
    cargarDatosTablaVentas();
    cargarClientes();
    cargarEmpleados();
    cargarProductos();
    actualizarHora();
        
    }

    
    private void limpiar() {
    textBuscar.setText("");
    idEmpleadoSeleccionado = null;
    selectorfechaVenta.setDate(new Date());
    
    // Limpiar la tabla de detalles
    tablaDetalles.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID Producto", "Producto", "Precio Unitario", "Cantidad", "Subtotal"}));
    
    cargarDatosTablaVentas();
    cargarClientes();
    cargarEmpleados();
    cargarProductos();
    
    btnEliminar.setEnabled(true);
    btnGuardar.setEnabled(true);
    
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
    
    private void cargarDatosTablaVentas() {
    List<Venta> ventas = controladorVenta.obtenerTodasVentas();

    if (ventas != null) {
        DefaultTableModel model = (DefaultTableModel) jTableVenta.getModel();
        model.setRowCount(0);

        for (Venta v : ventas) {
            Cliente cliente = controladorCliente.obtenerClientePorId(v.getIdCliente());
            String nombreCliente = cliente.getPrimerNombre() + " " + cliente.getPrimerApellido();
                       
            Empleado empleado = controladorEmpleado.obtenerEmpleadoPorId(v.getIdEmpleado());
            String nombreEmpleado = empleado.getPrimerNombre() + " " + empleado.getPrimerApellido();
            
            Object[] row = {
                v.getIdVenta(),
                v.getFechaVenta(),
                nombreCliente,
                nombreEmpleado,
                v.getTotalVenta()
            };
            model.addRow(row);
        }
    }
}
    
    private void cargarClientes() {
    try {
        // Obtener las categorías desde el controlador
        List<Cliente> clientes = controladorCliente.obtenerTodosClientes();

        // Limpiar el combo box por si tiene datos
        comboClientes.removeAllItems();

        // Agregar cada categoría al combo box
        for (Cliente c : clientes) {
            comboClientes.addItem(c.getPrimerNombre() + " " +c.getPrimerApellido()); // Mostrar el nombre
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
                "Error al cargar los clientes: " + e.getMessage());
    }
    
    private void cargarEmpleados() {
    try {
        // Obtener las categorías desde el controlador
        List<Empleado> empleados = empleadoControlador.obtenerTodosEmpleados();

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textPrecio = new javax.swing.JTextField();
        textCantidad = new javax.swing.JTextField();
        jButton1Guardar = new javax.swing.JButton();
        jButton3Eliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        textBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboClientes = new javax.swing.JComboBox<>();
        comboEmpleados = new javax.swing.JComboBox<>();
        selectorfechaVenta = new com.toedter.calendar.JDateChooser();
        comboProductos = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVenta = new javax.swing.JTable();
        jButton3Eliminar1 = new javax.swing.JButton();
        jButton3Eliminar2 = new javax.swing.JButton();
        jButton3Eliminar3 = new javax.swing.JButton();
        jButton3Eliminar4 = new javax.swing.JButton();

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

        jButton1Guardar.setText("Guardar");
        jButton1Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1GuardarActionPerformed(evt);
            }
        });

        jButton3Eliminar.setText("Guardar Venta");
        jButton3Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3EliminarActionPerformed(evt);
            }
        });

        jLabel3.setText("Cliente");

        textBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBuscarActionPerformed(evt);
            }
        });

        jLabel4.setText("Empleado");

        jLabel5.setText("Fecha");

        hora.setText("00:00:00");

        jLabel7.setText("Producto");

        jLabel8.setText("Precio");

        jLabel9.setText("Buscar");

        jLabel10.setText("Cantidad");

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboEmpleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jTableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Venta", "Fecha / Hora", "Cliente", "Vendedor", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableVenta);

        jButton3Eliminar1.setText("Eliminar Venta");
        jButton3Eliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3Eliminar1ActionPerformed(evt);
            }
        });

        jButton3Eliminar2.setText("Quitar Detalle");
        jButton3Eliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3Eliminar2ActionPerformed(evt);
            }
        });

        jButton3Eliminar3.setText("Limpiar");
        jButton3Eliminar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3Eliminar3ActionPerformed(evt);
            }
        });

        jButton3Eliminar4.setText("Actualizar Venta");
        jButton3Eliminar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3Eliminar4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selectorfechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hora)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(85, 85, 85)
                        .addComponent(jLabel8)))
                .addGap(18, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1Guardar)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
                        .addComponent(jScrollPane3)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(95, 95, 95)
                            .addComponent(jLabel4))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(comboEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3Eliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3Eliminar3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton3Eliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jButton3Eliminar4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(hora)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1Guardar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(comboProductos)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectorfechaVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3Eliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3Eliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3Eliminar3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3Eliminar4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCantidadActionPerformed

    private void textPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecioActionPerformed

    private void jButton3EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3EliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3EliminarActionPerformed

    private void textBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBuscarActionPerformed

    private void jButton1GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1GuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1GuardarActionPerformed

    private void jButton3Eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3Eliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3Eliminar1ActionPerformed

    private void jButton3Eliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3Eliminar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3Eliminar2ActionPerformed

    private void jButton3Eliminar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3Eliminar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3Eliminar3ActionPerformed

    private void jButton3Eliminar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3Eliminar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3Eliminar4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboClientes;
    private javax.swing.JComboBox<String> comboEmpleados;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JLabel hora;
    private javax.swing.JButton jButton1Guardar;
    private javax.swing.JButton jButton3Eliminar;
    private javax.swing.JButton jButton3Eliminar1;
    private javax.swing.JButton jButton3Eliminar2;
    private javax.swing.JButton jButton3Eliminar3;
    private javax.swing.JButton jButton3Eliminar4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableVenta;
    private com.toedter.calendar.JDateChooser selectorfechaVenta;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextField textCantidad;
    private javax.swing.JTextField textPrecio;
    // End of variables declaration//GEN-END:variables
}
