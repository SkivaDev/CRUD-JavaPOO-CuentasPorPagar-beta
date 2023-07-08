/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.entidades;

/**
 *
 * @author skiva
 */
public class Inventario {
    private int idInventario;
    private int idProductoInventariado;
    private String nombreProducto;
    private int cantidadProducto;

    public Inventario(int idInventario, int idProductoInventariado, String nombreProducto, int cantidadProducto) {
        this.idInventario = idInventario;
        this.idProductoInventariado = idProductoInventariado;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdProductoInventariado() {
        return idProductoInventariado;
    }

    public void setIdProductoInventariado(int idProductoInventariado) {
        this.idProductoInventariado = idProductoInventariado;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    
}
