/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Categoria;
import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Estudiantes
 */
public class CategoriaDAO {
    public void crearCategoria(Categoria categoria) throws SQLException{
        String sql = "INSERT INTO Categoria (nombre_categoria, Descripcion_categoria)VALUES (?, ?)";
        try (Connection c = ConexionDB.getConnection();
            PreparedStatement stmt = c.prepareStatement(sql)){
            stmt.setString(1, categoria.getNombreCategoria());
            stmt.setString(2, categoria.getDescripcionCategoria());
            stmt.executeUpdate();
        }
    }
    
    public static void main(String[] args) {
        try {
            CategoriaDAO dao = new CategoriaDAO();
            Categoria c1 = new Categoria();
            c1.setNombreCategoria("Herramientas");
            c1.setDescripcionCategoria("Categoria para herramientas de ferreteria");
            dao.crearCategoria(c1);
            System.err.println("Categoria creada con exito");     
        } catch (SQLException e){
            System.err.println("Error al crear categoria: " + e.getMessage());
        }
    
    }
    
}
