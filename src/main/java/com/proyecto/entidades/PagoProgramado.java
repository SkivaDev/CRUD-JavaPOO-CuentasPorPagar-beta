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
public class PagoProgramado {
    private int idPagoProgramado;
    private Factura factura;
    private String tipoPagoProgramado;
    private String metodoPago;
    private Cheque cheque;
    private Date fechaProgramada; 

    public PagoProgramado(int idPagoProgramado, Factura factura, String tipoPagoProgramado, String metodoPago, Cheque cheque, Date fechaProgramada) {
        this.idPagoProgramado = idPagoProgramado;
        this.factura = factura;
        this.tipoPagoProgramado = tipoPagoProgramado;
        this.metodoPago = metodoPago;
        this.cheque = cheque;
        this.fechaProgramada = fechaProgramada;
    }

    public int getIdPagoProgramado() {
        return idPagoProgramado;
    }

    public void setIdPagoProgramado(int idPagoProgramado) {
        this.idPagoProgramado = idPagoProgramado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getTipoPagoProgramado() {
        return tipoPagoProgramado;
    }

    public void setTipoPagoProgramado(String tipoPagoProgramado) {
        this.tipoPagoProgramado = tipoPagoProgramado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }
    
    
}
