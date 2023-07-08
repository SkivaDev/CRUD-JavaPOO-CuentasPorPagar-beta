/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.entidades;

/**
 *
 * @author skiva
 */
public class Producto {
    private int idProducto;
    private int idFactura;
    private String nombre;
    private String descripcion;
    private CategoriaProducto categoriaProducto;
    private int cantidadTotal; //cantidad
    private int cantidadIngresada;
    private int cantidadPendiente;
    private double precioUnitario;
    private double subtotal;

    public Producto(int idProducto, int idFactura, String nombre, String descripcion, CategoriaProducto categoriaProducto, int cantidadTotal, int cantidadIngresada, int cantidadPendiente, double precioUnitario, double subtotal) {
        this.idProducto = idProducto;
        this.idFactura = idFactura;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaProducto = categoriaProducto;
        this.cantidadTotal = cantidadTotal;
        this.cantidadIngresada = cantidadIngresada;
        this.cantidadPendiente = cantidadPendiente;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadIngresada() {
        return cantidadIngresada;
    }

    public void setCantidadIngresada(int cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

    public int getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(int cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    
}
