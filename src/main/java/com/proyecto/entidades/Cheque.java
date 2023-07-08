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
public class Cheque {
    private int idCheque;
    private Factura factura;
    private double montoCheque;
    private CuentaBancaria cuentaBancaria;
    private Date fechaEmicion;
    private String estadoCheque;

    public Cheque(int idCheque, Factura factura, double montoCheque, CuentaBancaria cuentaBancaria, Date fechaEmicion, String estadoCheque) {
        this.idCheque = idCheque;
        this.factura = factura;
        this.montoCheque = montoCheque;
        this.cuentaBancaria = cuentaBancaria;
        this.fechaEmicion = fechaEmicion;
        this.estadoCheque = estadoCheque;
    }

    public int getIdCheque() {
        return idCheque;
    }

    public void setIdCheque(int idCheque) {
        this.idCheque = idCheque;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public double getMontoCheque() {
        return montoCheque;
    }

    public void setMontoCheque(double montoCheque) {
        this.montoCheque = montoCheque;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Date getFechaEmicion() {
        return fechaEmicion;
    }

    public void setFechaEmicion(Date fechaEmicion) {
        this.fechaEmicion = fechaEmicion;
    }

    public String getEstadoCheque() {
        return estadoCheque;
    }

    public void setEstadoCheque(String estadoCheque) {
        this.estadoCheque = estadoCheque;
    }
    
    
    
}
