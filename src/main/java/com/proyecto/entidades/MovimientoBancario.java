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
public class MovimientoBancario {
    private int idMovimientoBancario;
    private CuentaBancaria cuentaBancaria;
    private String tipoMovimiento;
    private double montoMovimiento;
    private Date fechaMovimiento;

    public MovimientoBancario(int idMovimientoBancario, CuentaBancaria cuentaBancaria, String tipoMovimiento, double montoMovimiento, Date fechaMovimiento) {
        this.idMovimientoBancario = idMovimientoBancario;
        this.cuentaBancaria = cuentaBancaria;
        this.tipoMovimiento = tipoMovimiento;
        this.montoMovimiento = montoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getIdMovimientoBancario() {
        return idMovimientoBancario;
    }

    public void setIdMovimientoBancario(int idMovimientoBancario) {
        this.idMovimientoBancario = idMovimientoBancario;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getMontoMovimiento() {
        return montoMovimiento;
    }

    public void setMontoMovimiento(double montoMovimiento) {
        this.montoMovimiento = montoMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
    
    
}
