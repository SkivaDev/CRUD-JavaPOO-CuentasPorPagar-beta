/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.entidades;

import java.util.Date;

/**
 *
 * @author skiva
 */
public class MovimientoInventario {
    private int idMovimientoInventario;
    private Inventario inventario;
    private Producto producto;
    private int cantidadProducto;
    private String tipoMovimiento;
    private Date fechaMovimiento;

    public MovimientoInventario(int idMovimientoInventario, Inventario inventario, Producto producto, int cantidadProducto, String tipoMovimiento, Date fechaMovimiento) {
        this.idMovimientoInventario = idMovimientoInventario;
        this.inventario = inventario;
        this.producto = producto;
        this.cantidadProducto = cantidadProducto;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getIdMovimientoInventario() {
        return idMovimientoInventario;
    }

    public void setIdMovimientoInventario(int idMovimientoInventario) {
        this.idMovimientoInventario = idMovimientoInventario;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
    
    
}
