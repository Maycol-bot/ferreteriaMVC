package Controlador;

import DAO.CategoriaDAO;
import Modelo.Categoria;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorCategoria {
    private final CategoriaDAO CategoriaDAO;

    public ControladorCategoria() {
        this.CategoriaDAO = new CategoriaDAO();
    }
    
    
   
    // Método para crear una nueva categoría
    public void crearCategoria(String nombre, String descripcion) {
        try {
            Categoria Categoria = new Categoria();
            Categoria.setNombreCategoria(nombre);
            Categoria.setDescripcionCategoria(descripcion);
            CategoriaDAO.crearCategoria(Categoria);
            JOptionPane.showMessageDialog(null, "Categoría creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para obtener todas las categorías
    public List<Categoria> obtenerTodasCategorias() {
        try {
            return CategoriaDAO.leerTodasCategorias();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer las categorías: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    // Método para actualizar una categoría existente
    public void actualizarCategoria(int id, String nombre, String descripcion) {
        try {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(id);
            categoria.setNombreCategoria(nombre);
            categoria.setDescripcionCategoria(descripcion);
            CategoriaDAO.actualizarCategoria(categoria);
            JOptionPane.showMessageDialog(null, "Categoría actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar una categoría
    public void eliminarCategoria(int id) {
        try {
            CategoriaDAO.eliminarCategoria(id);
            JOptionPane.showMessageDialog(null, "Categoría eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}