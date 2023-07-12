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
public class PagoFactura {
    private int idPagoFactura;
    private Factura factura;
    private String tipoPagoFactura;
    private SolicitudPago solicitudPago;
    private PagoProgramado pagoProgramado;
    private double montoPago;
    private Date fechaPago;

    public PagoFactura(int idPagoFactura, Factura factura, String tipoPagoFactura, SolicitudPago solicitudPago, PagoProgramado pagoProgramado, double montoPago, Date fechaPago) {
        this.idPagoFactura = idPagoFactura;
        this.factura = factura;
        this.tipoPagoFactura = tipoPagoFactura;
        this.solicitudPago = solicitudPago;
        this.pagoProgramado = pagoProgramado;
        this.montoPago = montoPago;
        this.fechaPago = fechaPago;
    }

    public int getIdPagoFactura() {
        return idPagoFactura;
    }

    public void setIdPagoFactura(int idPagoFactura) {
        this.idPagoFactura = idPagoFactura;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getTipoPagoFactura() {
        return tipoPagoFactura;
    }

    public void setTipoPagoFactura(String tipoPagoFactura) {
        this.tipoPagoFactura = tipoPagoFactura;
    }

    public SolicitudPago getSolicitudPago() {
        return solicitudPago;
    }

    public void setSolicitudPago(SolicitudPago solicitudPago) {
        this.solicitudPago = solicitudPago;
    }

    public PagoProgramado getPagoProgramado() {
        return pagoProgramado;
    }

    public void setPagoProgramado(PagoProgramado pagoProgramado) {
        this.pagoProgramado = pagoProgramado;
    }

    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }


    
    
}
