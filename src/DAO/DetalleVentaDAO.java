/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.*;
import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Estudiantes
 */
public class DetalleVentaDAO {
    public void crearDetalleVenta(DetalleVenta detalle) throws SQLException {
    String sql = """
        INSERT INTO Detalles_Ventas (
            id_venta, 
            id_producto, 
            cantidad, 
            precio_unitario
        ) VALUES (?, ?, ?, ?)""";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, detalle.getIdVenta());
        stmt.setInt(2, detalle.getIdProducto());
        stmt.setInt(3, detalle.getCantidad());
        stmt.setFloat(4, detalle.getPrecioUnitario());
        stmt.executeUpdate();
    }
}

// Métodos para Actualizar y Eliminar
// Método para actualizar un detalle de venta
public void actualizarDetalleVenta(DetalleVenta detalle) throws SQLException {
    String sql = "UPDATE Detalles_Ventas SET id_venta = ?, id_producto = ?, cantidad = ?, precio_unitario = ? WHERE id_detalle_venta = ?";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, detalle.getIdVenta());
        stmt.setInt(2, detalle.getIdProducto());
        stmt.setInt(3, detalle.getCantidad());
        stmt.setFloat(4, detalle.getPrecioUnitario());
        stmt.setInt(5, detalle.getDetalleVenta());
        stmt.executeUpdate();
    }
}

// Método para eliminar un detalle de venta
public void eliminarDetalleVenta(int idDetalleVenta) throws SQLException {
    String sql = "DELETE FROM Detalles_Ventas WHERE id_detalle_venta = ?";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, idDetalleVenta);
        stmt.executeUpdate();
    }
}

// Método Main
public static void main(String[] args) {
    try {
        DetalleVentaDAO dao = new DetalleVentaDAO();
        
        // Actualizar un detalle de venta
        DetalleVenta detalle = new DetalleVenta();
        detalle.setDetalleVenta(1); // ID existente
        detalle.setIdVenta(1);
        detalle.setIdProducto(3);
        detalle.setCantidad(2);
        detalle.setPrecioUnitario(200.0f);
        dao.actualizarDetalleVenta(detalle);
        System.out.println("Detalle de venta actualizado.");
        
        // Eliminar un detalle de venta
        dao.eliminarDetalleVenta(2); // ID a eliminar
        System.out.println("Detalle de venta eliminado.");
        
        // Leer y mostrar todos los detalles de venta para verificar
        List<DetalleVenta> detalles = dao.leerTodosDetallesVenta();
        System.out.println("Lista de detalles de venta:");
        for (DetalleVenta det : detalles) {
            System.out.println("ID: " + det.getDetalleVenta() + 
                               ", Venta ID: " + det.getIdVenta() + 
                               ", Producto ID: " + det.getIdProducto() + 
                               ", Cantidad: " + det.getCantidad() + 
                               ", Precio Unitario: " + det.getPrecioUnitario());
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    }
}

    public List<DetalleVenta> leerTodosDetallesVenta() throws SQLException {
    List<DetalleVenta> detalles = new ArrayList<>();
    String sql = "SELECT id_detalle_venta, id_venta, id_producto, cantidad, precio_unitario FROM Detalles_Ventas";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setDetalleVenta(rs.getInt("id_detalle_venta"));
            detalle.setIdVenta(rs.getInt("id_venta"));
            detalle.setIdProducto(rs.getInt("id_producto"));
            detalle.setCantidad(rs.getInt("cantidad"));
            detalle.setPrecioUnitario(rs.getFloat("precio_unitario"));
            
            detalles.add(detalle);
        }
    }
    
    return detalles;
}

}
