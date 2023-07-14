/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.interfaces;

import com.proyecto.entidades.CategoriaProducto;
import com.proyecto.entidades.DetalleFactura;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Inventario;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;

import java.util.List;

/**
 *
 * @author skiva
 */
public interface DAOAlmaceneroInterfaz {
    //public void registrarProductos(Producto product) throws Exception;
    //pub2lic void modificarProveedor(Proveedor supplier) throws Exception;
    //pub2lic void eliminarProveedor(int supplierId) throws Exception;
    //pub2lic List<Proveedor> obtenerListaProveedores(String name) throws Exception;
    //pub2lic Proveedor obtenerProveedorPorId(int supplierId) throws Exception;
    //publ2ic int buscarIdProveedorPorNombre(String nombreProveedor) throws Exception;
   // public Users getUserById(int userId) throws Exception;
    //publ2ic boolean supplierNameEnUso(String supplierName) throws Exception;
    //
    //pub2lic void registrarFactura(Factura invoice) throws Exception;
    //pu2blic void modificarFactura(Factura invoice) throws Exception;
    //pub2lic void eliminarFactura(int invoiceId) throws Exception; //ya no se podria eliminar las facturas que ya han sido pagadas.
    //pub2lic List<Factura> obtenerListaFacturas(String name) throws Exception;
    //pu2blic List<Factura> obtenerListaFacturasPorIdProveedor(int supplierId) throws Exception;
    //pu2blic Factura obtenerFacturaPorId(int invoiceId) throws Exception;
    //
    //pu2blic void registrarProducto(Producto product) throws Exception;
    //p2ublic void modificarProducto(Producto product) throws Exception;
    //pub2lic void eliminarProductosPorIdFactura(int idFactura) throws Exception;
    //publ2ic List<Producto> obtenerListaProductosporFacturaId(int facturaId) throws Exception;
    //pub2lic int obtenerUltimaFacturaRegistrada() throws Exception;
   // public Users getUserById(int userId) throws Exception; 
    //public boolean supplierNameEnUso(String supplierName) throws Exception;
    public List<Inventario> obtenerListaInventarios(String productName) throws Exception;
    public Producto obtenerProductoPorId(int productId) throws Exception;
    public CategoriaProducto obtenerCategoriaProductoPorId(int productCategoryId) throws Exception;
    public List<CategoriaProducto> obtenerListaCategoriasProducto(String categoryName) throws Exception;
    public boolean categoriaRegistradaEnUso(int productCategoryId) throws Exception;
    public void eliminarCategoriaProducto(int productCategoryId) throws Exception;
    public boolean existeCategoriaProducto(String productCategoryName) throws Exception;
     
    //EXTRAS
    //publ2ic String buscarNombreProveedorPorFactura(int idFactura) throws Exception;
    
    //VERIFICACIONES
    //publ2ic boolean existeRegistroPago(int idFactura) throws Exception;
}
