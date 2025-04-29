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
public class EmpleadoDAO {
    public void crearEmpleado(Empleado empleado) throws SQLException {
    String sql = """
        INSERT INTO Empleados (
            primer_nombre, 
            segundo_nombre, 
            primer_apellido, 
            segundo_apellido, 
            celular, 
            cargo, 
            fecha_contratacion
        ) VALUES (?, ?, ?, ?, ?, ?, ?)""";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setString(1, empleado.getPrimerNombre());
        stmt.setString(2, empleado.getSegundoNombre());
        stmt.setString(3, empleado.getPrimerApellido());
        stmt.setString(4, empleado.getSegundoApellido());
        stmt.setString(5, empleado.getCelular());
        stmt.setString(6, empleado.getCargo());
        stmt.setDate(7, new java.sql.Date(empleado.getFechaContratacion().getTime()));
        stmt.executeUpdate();
    }
}

// Método para actualizar un empleado
public void actualizarEmpleado(Empleado empleado) throws SQLException {
    String sql = "UPDATE Empleados SET primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ?, celular = ?, cargo = ?, fecha_contratacion = ? WHERE id_empleado = ?";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setString(1, empleado.getPrimerNombre());
        stmt.setString(2, empleado.getSegundoNombre());
        stmt.setString(3, empleado.getPrimerApellido());
        stmt.setString(4, empleado.getSegundoApellido());
        stmt.setString(5, empleado.getCelular());
        stmt.setString(6, empleado.getCargo());
        stmt.setDate(7, new java.sql.Date(empleado.getFechaContratacion().getTime()));
        stmt.setInt(8, empleado.getIdEmpleado());
        stmt.executeUpdate();
    }
}

// Método para eliminar un empleado
public void eliminarEmpleado(int idEmpleado) throws SQLException {
    String sql = "DELETE FROM Empleados WHERE id_empleado = ?";
    
    try (Connection c = ConexionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, idEmpleado);
        stmt.executeUpdate();
    }
}

// Método Main
public static void main(String[] args) {
    try {
        EmpleadoDAO dao = new EmpleadoDAO();
        
        // Actualizar un empleado
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(1); // ID existente
        empleado.setPrimerNombre("Ana");
        empleado.setSegundoNombre("María");
        empleado.setPrimerApellido("López");
        empleado.setSegundoApellido("Martínez");
        empleado.setCelular("987654321");
        empleado.setCargo("Gerente");
        empleado.setFechaContratacion(new java.util.Date());
        dao.actualizarEmpleado(empleado);
        System.out.println("Empleado actualizado.");
        
        // Eliminar un empleado
        dao.eliminarEmpleado(2); // ID a eliminar
        System.out.println("Empleado eliminado.");
        
        // Leer y mostrar todos los empleados para verificar
        List<Empleado> empleados = dao.leerTodosEmpleados();
        System.out.println("Lista de empleados:");
        for (Empleado emp : empleados) {
            System.out.println("ID: " + emp.getIdEmpleado() + 
                               ", Nombre: " + emp.getPrimerNombre() + " " + emp.getSegundoNombre() + 
                               " " + emp.getPrimerApellido() + " " + emp.getSegundoApellido() + 
                               ", Celular: " + emp.getCelular() + 
                               ", Cargo: " + emp.getCargo() + 
                               ", Fecha Contratación: " + emp.getFechaContratacion());
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    }
}

    private List<Empleado> leerTodosEmpleados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
