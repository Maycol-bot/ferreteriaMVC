/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Estudiantes
 */
public class DetalleVenta {
        private int DetalleVenta;
    private int IdVenta;
    private int idProducto;
    private int cantidad;
    private float precioUnitario;

    public DetalleVenta(int DetalleVenta, int IdVenta, int idProducto, int cantidad, float precioUnitario) {
        this.DetalleVenta = DetalleVenta;
        this.IdVenta = IdVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public DetalleVenta() {
    }

    public int getDetalleVenta() {
        return DetalleVenta;
    }

    public void setDetalleVenta(int DetalleVenta) {
        this.DetalleVenta = DetalleVenta;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

  
    
}
