/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControladorEmpleado;
import Modelo.Empleado;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import javax.swing.JTextField;
/**
 *
 * @author Estudiantes
 */
public class VistaEmpleado extends javax.swing.JPanel {
private final ControladorEmpleado controladorEmpleado;
    /**
     * Creates new form VistaEmpleado
     */
    private Integer idEmpleadoSeleccionado = null;
    public VistaEmpleado() {
        initComponents();
        this.controladorEmpleado = new ControladorEmpleado();
        selectorFechaContratacion.setDate(new Date());
        ((JTextField) selectorFechaContratacion.getDateEditor().getUiComponent()).setEditable(false);
        cargarDatosTabla();
    }
private void cargarDatosTabla() {
    List<Empleado> empleados = controladorEmpleado.obtenerTodosEmpleados();
    if (empleados != null) {
        DefaultTableModel model = (DefaultTableModel) jTableEmpleado.getModel();
        model.setRowCount(0);
        
        for (Empleado emp : empleados) {
            Object[] row = {
                emp.getIdEmpleado(),
                emp.getPrimerNombre(),
                emp.getSegundoNombre(),
                emp.getPrimerApellido(),
                emp.getSegundoApellido(),
                emp.getCelular(),
                emp.getCargo(),
                emp.getFechaContratacion()
            };
        model.addRow(row);
        }
    }
}

private void limpiar() {
    jTextPrimerNombre.setText("");
    jTextSegundoNombre.setText("");
    jTextPrimerApellido.setText("");
    jTextSegundoApellido.setText("");
    jTextCelular.setText("");
    jTextBuscar.setText("");
    idEmpleadoSeleccionado = null;
    selectorFechaContratacion.setDate(new Date());
    jButtonEliminar.setEnabled(true);
    jButtonGuardar.setEnabled(true);
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
        jLabelPrimerNombre = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelSegundoNombre = new javax.swing.JLabel();
        jTextPrimerNombre = new javax.swing.JTextField();
        jTextSegundoNombre = new javax.swing.JTextField();
        jTextPrimerApellido = new javax.swing.JTextField();
        jTextSegundoApellido = new javax.swing.JTextField();
        jTextCelular = new javax.swing.JTextField();
        jTextBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmpleado = new javax.swing.JTable();
        jComboCargo = new javax.swing.JComboBox<>();
        jButtonLimpiar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        selectorFechaContratacion = new com.toedter.calendar.JDateChooser();

        jLabelPrimerNombre.setText("Primer Nombre");

        jLabel2.setText("Fecha Contratacion");

        jLabel3.setText("Buscar");

        jLabel4.setText("Cargo");

        jLabel5.setText("Celular");

        jLabel6.setText("Segundo Apellido");

        jLabel7.setText("Primer Apellido");

        jLabelSegundoNombre.setText("Segundo Nombre");

        jTextBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextBuscarActionPerformed(evt);
            }
        });

        jTableEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Emplea", "Nombre1", "Nombre2", "Apellido1", "Apellido2", "Celular", "Cargo", "Fecha Contratacion "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEmpleado);

        jComboCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vendedor", "Administrador" }));
        jComboCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCargoActionPerformed(evt);
            }
        });

        jButtonLimpiar.setText("Limpiar");

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionBotonGuardar(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionBotonEliminar(evt);
            }
        });

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionBotonActualizar(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPrimerNombre)
                            .addComponent(jTextPrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSegundoNombre)
                            .addComponent(jTextSegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(selectorFechaContratacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButtonLimpiar)
                        .addGap(28, 28, 28)
                        .addComponent(jButtonGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonActualizar)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrimerNombre)
                    .addComponent(jLabelSegundoNombre)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextPrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextSegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(selectorFechaContratacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLimpiar)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonActualizar))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboCargoActionPerformed

    private void accionBotonGuardar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionBotonGuardar
        // TODO add your handling code here:
        Date fecha = this.selectorFechaContratacion.getDate();
        java.sql.Date fechaContratacion = new java.sql.Date(fecha.getTime());
        String PrimerNombre = jTextPrimerNombre.getText();
        String SegundoNombre = jTextSegundoNombre.getText();
        String PrimerApellido = jTextPrimerApellido.getText();
        String segundoApellido = jTextSegundoApellido.getText();
        String Celular = jTextCelular.getText();
        String Cargo = (String)jComboCargo.getSelectedItem();
        
        if (!PrimerNombre.isEmpty() && !PrimerApellido.isEmpty() && !Celular.isEmpty() && ! Cargo.isEmpty()){
        try {
            controladorEmpleado.crearEmpleado(PrimerNombre, SegundoNombre, PrimerApellido, segundoApellido, Celular, Cargo, fecha);
            limpiar();
            cargarDatosTabla();
            } catch (Exception e){
                javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            }else {
                javax.swing.JOptionPane.showMessageDialog(this,"Llene los campos requeridos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
        
    }//GEN-LAST:event_accionBotonGuardar

    private void accionBotonEliminar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionBotonEliminar
        // TODO add your handling code here:
        int filaSeleccionada = jTableEmpleado.getSelectedRow();
        if (filaSeleccionada != -1){
            int idEmpleado = (int) jTableEmpleado.getValueAt(filaSeleccionada, 0);
            controladorEmpleado.eliminarEmpleado(idEmpleado);
            cargarDatosTabla();
        }else {
        javax.swing.JOptionPane.showMessageDialog(this,"Seleccionar fila a eliminar", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_accionBotonEliminar

    private void jTableEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmpleadoMouseClicked
        // TODO add your handling code here:
        
        if (evt.getClickCount() == 2) {
            int filaSeleccionada = jTableEmpleado.getSelectedRow();
            if (filaSeleccionada != -1){
            idEmpleadoSeleccionado = (int) jTableEmpleado.getValueAt(filaSeleccionada, 0);
            String PrimerNombre = (String) jTableEmpleado.getValueAt(filaSeleccionada, 1);
            String SegundoNombre = (String) jTableEmpleado.getValueAt(filaSeleccionada, 2);
            String PrimerApellido = (String) jTableEmpleado.getValueAt(filaSeleccionada, 3);
            String SegundoApellido = (String) jTableEmpleado.getValueAt(filaSeleccionada, 4);
            String Celular = (String) jTableEmpleado.getValueAt(filaSeleccionada, 5);
            String Cargo = (String) jTableEmpleado.getValueAt(filaSeleccionada, 6);
            Date Fecha = (Date) jTableEmpleado.getValueAt(filaSeleccionada, 7);
            
            jTextPrimerNombre.setText(PrimerNombre);
            jTextSegundoNombre.setText(SegundoNombre != null ? SegundoNombre : "");
            jTextPrimerApellido.setText(PrimerApellido);
            jTextSegundoApellido.setText(SegundoApellido != null ? SegundoApellido : "");
            jTextCelular.setText(Celular);
            jComboCargo.setSelectedItem(Cargo != null ? Cargo : "Seleccionar");
            
            selectorFechaContratacion.setDate(Fecha);
            
            jButtonEliminar.setEnabled(false);
            jButtonGuardar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jTableEmpleadoMouseClicked

    private void accionBotonActualizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionBotonActualizar
        // TODO add your handling code here:
        String PrimerNombre = jTextPrimerNombre.getText();
        String SegundoNombre = jTextSegundoNombre.getText();
        String PrimerApellido = jTextPrimerApellido.getText();
        String SegundoApellido = jTextSegundoApellido.getText();
        String Celular = jTextCelular.getText();
        String Cargo = (String) jComboCargo.getSelectedItem();
        Date Fecha = this.selectorFechaContratacion.getDate();
        java.sql.Date fechaContratacion = new java.sql.Date(Fecha.getTime());
        
        if (idEmpleadoSeleccionado != null && ! PrimerNombre.isEmpty() && !PrimerApellido.isEmpty() && !Celular.isEmpty()){
            try {
            controladorEmpleado.actualizarEmpleado(idEmpleadoSeleccionado, PrimerNombre, SegundoNombre, PrimerApellido,
                    SegundoApellido, Celular, Cargo, Fecha);
            cargarDatosTabla();
            limpiar();
            } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error" + e.getMessage(),"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Llene los campos obligatorios.", "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_accionBotonActualizar

    private void jTextBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextBuscarActionPerformed
        // TODO add your handling code here:
        String textoBusqueda = jTextBuscar. getText().trim().toLowerCase();
        List<Empleado> empleado = controladorEmpleado.obtenerTodosEmpleados();
        
        DefaultTableModel modelo = (DefaultTableModel) jTableEmpleado.getModel();
        modelo.setRowCount(0);
        
        if (empleado != null) {
        for (Empleado emp : empleado) {
            if (textoBusqueda.isEmpty() ||
                    emp.getPrimerNombre().toLowerCase().contains(textoBusqueda)||
                    (emp.getSegundoNombre() != null && emp.getSegundoNombre().toLowerCase().contains(textoBusqueda))||
                    emp.getPrimerApellido().toLowerCase().contains(textoBusqueda)||
                    (emp.getSegundoApellido() != null && emp.getSegundoApellido().toLowerCase().contains(textoBusqueda))||
                    emp.getCelular().toLowerCase().contains(textoBusqueda)||
                    emp.getCargo().toLowerCase().contains(textoBusqueda)){
            Object[] fila = {
            emp.getIdEmpleado(),
                emp.getPrimerNombre(),
                emp.getSegundoNombre(),
                emp.getPrimerApellido(),
                emp.getSegundoApellido(),
                emp.getCelular(),
                emp.getCargo(),
                emp.getFechaContratacion()
                    };
            modelo.addRow(fila);
                }
            }
        }
    }//GEN-LAST:event_jTextBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JComboBox<String> jComboCargo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelPrimerNombre;
    private javax.swing.JLabel jLabelSegundoNombre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmpleado;
    private javax.swing.JTextField jTextBuscar;
    private javax.swing.JTextField jTextCelular;
    private javax.swing.JTextField jTextPrimerApellido;
    private javax.swing.JTextField jTextPrimerNombre;
    private javax.swing.JTextField jTextSegundoApellido;
    private javax.swing.JTextField jTextSegundoNombre;
    private com.toedter.calendar.JDateChooser selectorFechaContratacion;
    // End of variables declaration//GEN-END:variables
}
