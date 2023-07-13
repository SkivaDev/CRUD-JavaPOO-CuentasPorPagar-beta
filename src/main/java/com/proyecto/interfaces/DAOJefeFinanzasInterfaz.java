/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.interfaces;

import com.proyecto.entidades.Canje;
import com.proyecto.entidades.CategoriaProducto;
import com.proyecto.entidades.Cheque;
import com.proyecto.entidades.CuentaBancaria;
import com.proyecto.entidades.DetalleFactura;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Inventario;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.SolicitudPago;

import java.util.List;

/**
 *
 * @author skiva
 */
public interface DAOJefeFinanzasInterfaz {
    /*
    public void registrarProveedor(Proveedor supplier) throws Exception;
    
    public void modificarProveedor(Proveedor supplier) throws Exception;
    public void eliminarProveedor(int supplierId) throws Exception;
    public List<Proveedor> obtenerListaProveedores(String name) throws Exception;
    public Proveedor obtenerProveedorPorId(int supplierId) throws Exception;
    public int buscarIdProveedorPorNombre(String nombreProveedor) throws Exception;
   // public Users getUserById(int userId) throws Exception;
    public boolean supplierNameEnUso(String supplierName) throws Exception;
    //
    */
    
   // public void registrarFactura(Factura invoice) throws Exception;
    //public void modificarFactura(Factura invoice) throws Exception;
   // public void eliminarFactura(int invoiceId) throws Exception; //ya no se podria eliminar las facturas que ya han sido pagadas.
      public List<Factura> obtenerListaFacturas(String name) throws Exception;
    //public List<Factura> obtenerListaFacturasPorIdProveedor(int supplierId) throws Exception;
      public Factura obtenerFacturaPorId(int invoiceId) throws Exception;
    //
   // public void registrarProducto(Producto product) throws Exception;
   // public void modificarProducto(Producto product) throws Exception;
   //public void eliminarProductosPorIdFactura(int idFactura) throws Exception;
    //public List<Producto> obtenerListaProductosporFacturaId(int facturaId) throws Exception;
    //public int obtenerUltimaFacturaRegistrada() throws Exception;
   // public Users getUserById(int userId) throws Exception; 
    //public boolean supplierNameEnUso(String supplierName) throws Exception;
    
      public List<Producto> obtenerListaProductosPorProveedorId(int supplierId) throws Exception;
      public List<Factura> obtenerListaFacturasPorProveedorId(int supplierId) throws Exception;
      
      
      public List<CuentaBancaria> obtenerListaCuentasBancarias(String bankName) throws Exception;
      public CuentaBancaria obtenerCuentaBancariaPorNombre(String name) throws Exception;
      public CuentaBancaria obtenerCuentaBancariaPorId(int bankAccountId) throws Exception;
      
      public List<CategoriaProducto> obtenerListaCategoriasProducto() throws Exception;
      public CategoriaProducto obtenerCategoriaProductoPorId(int productCategoryId) throws Exception;
      
      public List<Producto> obtenerListaProductosPorNombreCategoria(String categoryName) throws Exception;
      public List<Producto> obtenerListaProductosDisponiblesInventarioPorNombreCategoria(String categoryName) throws Exception;
      
      public int registrarCheque(Cheque check) throws Exception; //devuelve el id del cheque cuando se registra
      public Cheque obtenerChequePorId(int checkId) throws Exception;
      
      public int registrarSolicitudPago(SolicitudPago paymentRequest) throws Exception; //devuelve el id del cheque cuando se registra
      
      public int registrarCanje(Canje exchange) throws Exception; //devuelve el id del canje cuando se registra
      //
      public List<SolicitudPago> obtenerListaSolicitudesPago() throws Exception;
      public Canje obtenerCanjePorId(int exchangeId) throws Exception;
      public Producto obtenerProductoPorId(int productId) throws Exception;
      
      public void modificarEstadoSolicitudPagoPorId(int requestId, String estadoSolicitud) throws Exception;
      
      public void registrarCuentaBancaria(CuentaBancaria banckAccount) throws Exception;
    //EXTRAS
   public String buscarNombreProveedorPorFactura(int invoiceId) throws Exception;
    
   public Inventario obtenerInventarioPorIdProducto(int productId) throws Exception;
   
    //VERIFICACIONES
   public boolean existeEntidadBancaria(String bankName) throws Exception;
   // public boolean existeRegistroPago(int idFactura) throws Exception;
}
