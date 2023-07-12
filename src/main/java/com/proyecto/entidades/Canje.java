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
public class Canje {
    private int idCanje;
    private Factura factura;
    private String detalleCanje;
    private Producto productoCanje;
    private int cantidadProducto;
    private double equivalenteDinero;
    private Date fechaEmicion;
    private String estadoCanje;

    public Canje(int idCanje, Factura factura, String detalleCanje, Producto productoCanje, int cantidadProducto, double equivalenteDinero, Date fechaEmicion, String estadoCanje) {
        this.idCanje = idCanje;
        this.factura = factura;
        this.detalleCanje = detalleCanje;
        this.productoCanje = productoCanje;
        this.cantidadProducto = cantidadProducto;
        this.equivalenteDinero = equivalenteDinero;
        this.fechaEmicion = fechaEmicion;
        this.estadoCanje = estadoCanje;
    }

    public int getIdCanje() {
        return idCanje;
    }

    public void setIdCanje(int idCanje) {
        this.idCanje = idCanje;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getDetalleCanje() {
        return detalleCanje;
    }

    public void setDetalleCanje(String detalleCanje) {
        this.detalleCanje = detalleCanje;
    }

    public Producto getProductoCanje() {
        return productoCanje;
    }

    public void setProductoCanje(Producto productoCanje) {
        this.productoCanje = productoCanje;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public double getEquivalenteDinero() {
        return equivalenteDinero;
    }

    public void setEquivalenteDinero(double equivalenteDinero) {
        this.equivalenteDinero = equivalenteDinero;
    }

    public Date getFechaEmicion() {
        return fechaEmicion;
    }

    public void setFechaEmicion(Date fechaEmicion) {
        this.fechaEmicion = fechaEmicion;
    }

    public String getEstadoCanje() {
        return estadoCanje;
    }

    public void setEstadoCanje(String estadoCanje) {
        this.estadoCanje = estadoCanje;
    }
    
    
}
