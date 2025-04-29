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
public class DetalleCompraDAO {
    public void crearDetalleCompra(DetalleCompra detalle) throws SQLException {
    String sql = """
        INSERT INTO Detalles_Compras (
            id_compra, 
            id_producto, 
            cantidad, 
            precio_unitario
        ) VALUES (?, ?, ?, ?)""";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, detalle.getCompra());
        stmt.setInt(2, detalle.getProducto());
        stmt.setInt(3, detalle.getCantidad());
        stmt.setFloat(4, detalle.getPrecioUnitario());
        stmt.executeUpdate();
    }
}
    
    
// Método para actualizar un detalle de compra
public void actualizarDetalleCompra(DetalleCompra detalle) throws SQLException {
    String sql = "UPDATE Detalles_Compras SET id_compra = ?, id_producto = ?, cantidad = ?, precio_unitario = ? WHERE id_detalle_compra = ?";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, detalle.getCompra());
        stmt.setInt(2, detalle.getProducto());
        stmt.setInt(3, detalle.getCantidad());
        stmt.setFloat(4, detalle.getPrecioUnitario());
        stmt.setInt(5, detalle.getIdDetalleCompra());
        stmt.executeUpdate();
    }
}

// Método para eliminar un detalle de compra
public void eliminarDetalleCompra(int idDetalleCompra) throws SQLException {
    String sql = "DELETE FROM Detalles_Compras WHERE id_detalle_compra = ?";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, idDetalleCompra);
        stmt.executeUpdate();
    }
}



// Método Main
public static void main(String[] args) {
    try {
        DetalleCompraDAO dao = new DetalleCompraDAO();
        
        // Actualizar un detalle de compra
        DetalleCompra detalle = new DetalleCompra();
        detalle.setIdDetalleCompra(1); // ID existente
        detalle.setCompra(1);
        detalle.setProducto(2);
        detalle.setCantidad(5);
        detalle.setPrecioUnitario(100.0f);
        dao.actualizarDetalleCompra(detalle);
        System.out.println("Detalle de compra actualizado.");
        
        // Eliminar un detalle de compra
        dao.eliminarDetalleCompra(2); // ID a eliminar
        System.out.println("Detalle de compra eliminado.");
        
        // Leer y mostrar todos los detalles de compra para verificar
        List<DetalleCompra> detalles = dao.leerTodosDetallesCompra();
        System.out.println("Lista de detalles de compra:");
        for (DetalleCompra det : detalles) {
            System.out.println("ID: " + det.getIdDetalleCompra() + 
                               ", Compra ID: " + det.getCompra()+ 
                               ", Producto ID: " + det.getProducto()+ 
                               ", Cantidad: " + det.getCantidad() + 
                               ", Precio Unitario: " + det.getPrecioUnitario());
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    }
}

    private List<DetalleCompra> leerTodosDetallesCompra() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
