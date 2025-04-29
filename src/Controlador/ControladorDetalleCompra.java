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

    public ControladorDetalleCompra(CompraDAO compraDAO, DetalleCompraDAO detalleCompraDAO) {
        this.compraDAO = compraDAO;
        this.detalleCompraDAO = detalleCompraDAO;
    }

    

    // Método para crear una nueva compra con sus detalles
    public void crearCompra(int idEmpleado, Date fechaCompra, float totalCompra, List<DetalleCompra> detalles) {
        try {
            Compra compra = new Compra();
            compra.setEmpleado(idEmpleado);
            compra.setFechaCompra(fechaCompra);
            compra.setTotalCompra(totalCompra);
            int idCompra = compraDAO.crearCompra(compra);

            if (idCompra == -1) {
                throw new SQLException("No se pudo obtener el ID de la compra.");
            }

            // Asignar el id_compra a cada detalle y guardarlos
            for (DetalleCompra detalle : detalles) {
                detalle.setIdDetalleCompra(idCompra);
                detalleCompraDAO.crearDetalleCompra(detalle);
            }

            JOptionPane.showMessageDialog(null, "Compra y detalles creados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para obtener todas las compras
    public List<Compra> obtenerTodasCompras() {
        return compraDAO.leerTodasCompras();
    }

    // Método para actualizar una compra existente
    public void actualizarCompra(int idCompra, int idEmpleado, Date fechaCompra, float totalCompra) {
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
    public void eliminarCompra(int idCompra) {
          try {
              compraDAO.eliminarCompra(idCompra);
          } catch (SQLException ex) {
              Logger.getLogger(ControladorDetalleCompra.class.getName()).log(Level.SEVERE, null, ex);
          }
        JOptionPane.showMessageDialog(null, "Compra eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método main para pruebas
    public static void main(String[] args) {
       CompraDAO compraDAO = new CompraDAO();
       DetalleCompraDAO detalleCompraDAO = new DetalleCompraDAO();
       ControladorDetalleCompra controlador = new ControladorDetalleCompra(compraDAO, detalleCompraDAO);


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
    }
}

