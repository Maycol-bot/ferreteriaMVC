package Controlador;
import DAO.ClienteDAO;
import Modelo.Cliente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorCliente {
     private final ClienteDAO clienteDAO;

    public ControladorCliente(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

   

    // Método para crear un nuevo cliente
    public void crearCliente(String primerNombre, String segundoNombre, String primerApellido,
            String segundoApellido, String celular, String direccion, String cedula) {
        Cliente cliente = new Cliente();
        cliente.setPrimerNombre(primerNombre);
        cliente.setSegundoNombre(segundoNombre);
        cliente.setPrimerApellido(primerApellido);
        cliente.setSegundoApellido(segundoApellido);
        cliente.setCelular(celular);
        cliente.setDireccion(direccion);
        cliente.setCedula(cedula);
        clienteDAO.crearCliente(cliente);
        JOptionPane.showMessageDialog(null, "Cliente creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para obtener todos los clientes
    public List<Cliente> obtenerTodosClientes() {
        try {
            return clienteDAO.leerTodosClientes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para actualizar un cliente existente
    public void actualizarCliente(int idCliente, String primerNombre, String segundoNombre, String primerApellido,
            String segundoApellido, String celular, String direccion, String cedula) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.setPrimerNombre(primerNombre);
        cliente.setSegundoNombre(segundoNombre);
        cliente.setPrimerApellido(primerApellido);
        cliente.setSegundoApellido(segundoApellido);
        cliente.setCelular(celular);
        cliente.setDireccion(direccion);
        cliente.setCedula(cedula);
        clienteDAO.actualizarCliente(cliente);
        JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para eliminar un cliente
    public void eliminarCliente(int idCliente) {
         try {
             clienteDAO.eliminarCliente(idCliente);
         } catch (SQLException ex) {
             Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
         }
        JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método main para pruebas
    public static void main(String[] args) {
            ClienteDAO dao = new ClienteDAO();
        ControladorCliente controlador = new ControladorCliente(dao);

        // Crear un cliente
        controlador.crearCliente("Juan", "Antonio", "Pérez", "Gómez", "12345678", "Calle 123", "1234567890");

        // Leer todos los clientes
        List<Cliente> clientes = controlador.obtenerTodosClientes();
        if (clientes != null) {
            System.out.println("Lista de clientes:");
            for (Cliente c : clientes) {
                System.out.println("ID: " + c.getIdCliente()
                        + ", Nombre: " + c.getPrimerNombre() + " " + c.getPrimerApellido()
                        + ", Cédula: " + c.getCedula());
            }
        }

         try {
             // Actualizar un cliente (suponiendo que ID 1 existe)
             controlador.actualizarCliente(1, "Juan", "Manuel", "Pérez", "López", "87654321", "Calle 456", "0987654321");
         } catch (SQLException ex) {
             Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
         }

        // Eliminar un cliente
        controlador.eliminarCliente(1);
    }
}
