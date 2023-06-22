/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.interfaces;

import com.proyecto.entidades.DetalleFactura;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Producto;
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
    //
    public void registrarFactura(Factura invoice) throws Exception;
    public void modificarFactura(Factura invoice) throws Exception;
    public void eliminarFactura(int invoiceId) throws Exception; //ya no se podria eliminar las facturas que ya han sido pagadas.
    public List<Factura> obtenerListaFacturas(String name) throws Exception;
    public Factura obtenerFacturaPorId(int invoiceId) throws Exception;
    //
    public void registrarProducto(Producto product) throws Exception;
    public void eliminarProductosPorIdFactura(int idFactura) throws Exception;
   // public Users getUserById(int userId) throws Exception;
    //public boolean supplierNameEnUso(String supplierName) throws Exception;
}
