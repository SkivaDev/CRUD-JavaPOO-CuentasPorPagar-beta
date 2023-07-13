/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.entidades;

/**
 *
 * @author skiva
 */
public class CuentaBancaria {
    private int idCuentaBancaria;
    private String nombreBanco;
    private String tipoCuentaBancaria;
    private double saldoActual;
    private double saldoPrevio; 

    public CuentaBancaria(int idCuentaBancaria, String nombreBanco, String tipoCuentaBancaria, double saldoActual, double saldoPrevio) {
        this.idCuentaBancaria = idCuentaBancaria;
        this.nombreBanco = nombreBanco;
        this.tipoCuentaBancaria = tipoCuentaBancaria;
        this.saldoActual = saldoActual;
        this.saldoPrevio = saldoPrevio;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getTipoCuentaBancaria() {
        return tipoCuentaBancaria;
    }

    public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public double getSaldoPrevio() {
        return saldoPrevio;
    }

    public void setSaldoPrevio(double saldoPrevio) {
        this.saldoPrevio = saldoPrevio;
    }

    
}
