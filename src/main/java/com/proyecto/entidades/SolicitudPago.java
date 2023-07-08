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
public class SolicitudPago {
    private int idSolicitudPago;
    private Factura factura;
    private String metodoPago;
    private Cheque cheque;
    private Canje canje;
    private String estadoSolicitud;
    private Date fechaRegistro; 

    public SolicitudPago(int idSolicitudPago, Factura factura, String metodoPago, Cheque cheque, Canje canje, String estadoSolicitud, Date fechaRegistro) {
        this.idSolicitudPago = idSolicitudPago;
        this.factura = factura;
        this.metodoPago = metodoPago;
        this.cheque = cheque;
        this.canje = canje;
        this.estadoSolicitud = estadoSolicitud;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdSolicitudPago() {
        return idSolicitudPago;
    }

    public void setIdSolicitudPago(int idSolicitudPago) {
        this.idSolicitudPago = idSolicitudPago;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
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

    public Canje getCanje() {
        return canje;
    }

    public void setCanje(Canje canje) {
        this.canje = canje;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
}
