/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.CompraDAO;
import Modelo.Compra;
import DAO.DetalleCompraDAO;
import Modelo.DetalleCompra;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Date;

/**
 *
 * @author Estudiantes
 */
public class CompraControlador {

    private final CompraDAO compraDAO;
    private final DetalleCompraDAO detalleCompraDAO;

    public CompraControlador() {
        this.compraDAO = new CompraDAO();
        this.detalleCompraDAO = new DetalleCompraDAO();
    }

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
                detalle.setCompra(idCompra);
                detalleCompraDAO.crearDetalleCompra(detalle);
            }

            JOptionPane.showMessageDialog(null, "Compra y detalles creados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Compra> obtenerTodasCompras() {
        try {
            return compraDAO.leerTodasCompras();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer las compras: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void actualizarCompra(int idCompra, int idEmpleado, Date fechaCompra, float totalCompra) {
        try {
            Compra compra = new Compra();
            compra.setIdCompra(idCompra);
            compra.setEmpleado(idEmpleado);
            compra.setFechaCompra(fechaCompra);
            compra.setTotalCompra(totalCompra);
            compraDAO.actualizarCompra(compra);
            JOptionPane.showMessageDialog(null, "Compra actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarCompra(int idCompra) {
        try {
            compraDAO.eliminarCompra(idCompra);
            JOptionPane.showMessageDialog(null, "Compra eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
