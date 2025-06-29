package Controlador;

import DAO.CompraDAO;
import DAO.DetalleCompraDAO;
import Modelo.Compra;
import Modelo.DetalleCompra;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorDetalleCompra {
      private final CompraDAO compraDAO;
    private final DetalleCompraDAO detalleCompraDAO;

    public ControladorDetalleCompra() {
        this.compraDAO = new CompraDAO();
        this.detalleCompraDAO = new DetalleCompraDAO();
    }

    

    // Método para crear una nueva compra con sus detalles
    public void crearDetalleCompra(int idCompra, int idProducto, int cantidad, float precioUnitario) {
        try {
            DetalleCompra detalle = new DetalleCompra();
            detalle.setCompra(idCompra);
            detalle.setProducto(idProducto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precioUnitario);
            detalleCompraDAO.crearDetalleCompra(detalle);
            JOptionPane.showMessageDialog(null, "Detalle de compra creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el detalle de compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

     // Método para obtener todos los detalles de compra
    public List<DetalleCompra> obtenerTodosDetallesCompra() {
        try {
            return detalleCompraDAO.leerTodosDetallesCompra();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los detalles de compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
        

    // Método para obtener todas las compras

    // Método para actualizar una compra existente
    public void actualizarDetalleCompra(int idCompra, int idEmpleado, Date fechaCompra, float totalCompra) {
        Compra compra = new Compra();
        compra.setIdCompra(idCompra);
        compra.setEmpleado(idEmpleado);
        compra.setFechaCompra(fechaCompra);
        compra.setTotalCompra(totalCompra);
          try {
              compraDAO.actualizarCompra(compra);
          } catch (SQLException ex) {
              Logger.getLogger(ControladorDetalleCompra.class.getName()).log(Level.SEVERE, null, ex);
          }
        JOptionPane.showMessageDialog(null, "Compra actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para eliminar una compra
    public void eliminarDetalleCompra(int idCompra) {
          try {
              compraDAO.eliminarCompra(idCompra);
          } catch (SQLException ex) {
              Logger.getLogger(ControladorDetalleCompra.class.getName()).log(Level.SEVERE, null, ex);
          }
        JOptionPane.showMessageDialog(null, "Compra eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método main para pruebas
   /* public static void main(String[] args) throws SQLException {
       CompraControlador controlador = new CompraControlador();


        // Crear una lista de detalles de compra
        List<DetalleCompra> detalles = new ArrayList<>();
        DetalleCompra detalle1 = new DetalleCompra();
        detalle1.setProducto(4);
        detalle1.setCantidad(11);
        detalle1.setPrecioUnitario(51.51f);
        detalles.add(detalle1);

        // Crear una compra con detalles
        controlador.crearCompra(1, new Date(), 150.50f, detalles);

        // Leer todas las compras
        List<Compra> compras = controlador.obtenerTodasCompras();
        if (compras != null) {
            System.out.println("Lista de compras:");
            for (Compra c : compras) {
                System.out.println("ID: " + c.getIdCompra()
                        + ", Empleado: " + c.getEmpleado()
                        + ", Total: " + c.getTotalCompra());
            }
        }

        // Actualizar una compra (suponiendo que ID 1 existe)
        controlador.actualizarCompra(1, 2, new Date(), 200.75f);

        // Eliminar una compra
        controlador.eliminarCompra(1);
    }*/
}

