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
import com.proyecto.entidades.MovimientoBancario;
import com.proyecto.entidades.MovimientoInventario;
import com.proyecto.entidades.PagoFactura;
import com.proyecto.entidades.PagoProgramado;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.SolicitudPago;

import java.util.List;

/**
 *
 * @author skiva
 */
public interface DAOTesoreroInterfaz {


    
   // public void registrarFactura(Factura invoice) throws Exception;
    //public void modificarFactura(Factura invoice) throws Exception;
   // public void eliminarFactura(int invoiceId) throws Exception; //ya no se podria eliminar las facturas que ya han sido pagadas.
      public List<Factura> obtenerListaFacturas(String name) throws Exception;
    //public List<Factura> obtenerListaFacturasPorIdProveedor(int supplierId) throws Exception;
      public Factura obtenerFacturaPorId(int invoiceId) throws Exception;

    
      public List<Producto> obtenerListaProductosPorProveedorId(int supplierId) throws Exception;
      public List<Factura> obtenerListaFacturasPorProveedorId(int supplierId) throws Exception;
      
      
      public List<CuentaBancaria> obtenerListaCuentasBancarias() throws Exception;
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
      
      public SolicitudPago obtenerSolicitudPagoPorId(int paymentRequestId) throws Exception;
      
      public int registrarPagoFactura(PagoFactura invoicePayment) throws Exception; //devuelve el id del pago de factura cuando se registra
      public PagoFactura obtenerPagoFacturaPorId(int invoicePaymentId) throws Exception;
      
      public PagoProgramado obtenerPagoProgramadoPorId(int programmedPayment) throws Exception;
      
      public int registrarMovimientoBancario(MovimientoBancario bankingMovement) throws Exception; //devuelve el id del movimiento bancario cuando se registra
      
      public void modificarSaldosCuentaBancariaPorId(int bankAccountId, double saldoActualDespues, double saldoPrevioDespues) throws Exception;
      public void modificarEstadoChequePorId(int checkId, String nuevoEstadoCheque) throws Exception;
      
      public void modificarCantidadProductosInventarioPorIdProducto(int productId, int cantidadProductosDespues) throws Exception;
      public int registrarMovimientoInventario(MovimientoInventario inventoryMovement) throws Exception; //devuelve el id del movimiento inventario cuando se registra
      public void modificarEstadoCanjePorId(int exchangeId, String nuevoEstadoCanje) throws Exception;
      
      public void modificarMontosPagadosFacturaPorId(int invoiceId, double montoPagadoDespues, double montoPendienteDespues) throws Exception;
      
      public Cheque obtenerChequeRespaldoPagoProgramadoPorIdFactura(int invoiceId) throws Exception;
      public void modificarMontoChequeRespaldoProgramadoPorIdCheque(int checkId, double nuevoMontoCheque) throws Exception;
      
      public List<MovimientoBancario> obtenerListaMovimientosBancarios(String bankName) throws Exception;
      public List<PagoFactura> obtenerListaPagosFacturas(String supplierName) throws Exception;
      public Proveedor obtenerProveedorPorId(int supplierId) throws Exception;
      
      public double obtenerDeudaTotalPorProveedor(int idProveedor) throws Exception;
      public List<PagoFactura> obtenerListaPagosFacturasPorIdProveedor (int supplierId) throws Exception;
    //EXTRAS
   public String buscarNombreProveedorPorFactura(int invoiceId) throws Exception;
    
   public Inventario obtenerInventarioPorIdProducto(int productId) throws Exception;
   
    //VERIFICACIONES
   // public boolean existeRegistroPago(int idFactura) throws Exception;
}
