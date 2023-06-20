/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.interfaces;

import com.proyecto.entidades.Proveedor;

import java.util.List;

/**
 *
 * @author skiva
 */
public interface DAOEncargadoComprasInterfaz {
    public void registrarProveedor(Proveedor supplier) throws Exception;
    public void modificarProveedor(Proveedor supplier) throws Exception;
    public void eliminarProveedor(int supplierId) throws Exception;
    public List<Proveedor> obtenerListaProveedores(String name) throws Exception;
    public Proveedor obtenerProveedorPorId(int supplierId) throws Exception;
   // public Users getUserById(int userId) throws Exception;
    public boolean supplierNameEnUso(String supplierName) throws Exception;
}
