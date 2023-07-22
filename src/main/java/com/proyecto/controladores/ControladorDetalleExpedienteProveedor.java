/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import com.proyecto.baseDatos.consultas.DAOTesoreroImpl;
import com.proyecto.entidades.Factura;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaRegistroUsuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorDetalleExpedienteProveedor {

    private DAOTesoreroImpl dao;

    //private Usuario user;
    public ControladorDetalleExpedienteProveedor() {
        this.dao = new DAOTesoreroImpl();
    }

    public DefaultTableModel listarProductosProveedor(JTable table, int supplierId) {
        DefaultTableModel model = new DefaultTableModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IDProducto");
        model.addColumn("IDFactura");
        model.addColumn("Nombre");
        model.addColumn("Descripcion");
        model.addColumn("Cantidad");
        model.addColumn("Precio Unitario");
        model.addColumn("Subtotal");

        try {
            dao.obtenerListaProductosPorProveedorId(supplierId).forEach((u) -> model.addRow(new Object[]{u.getIdProducto(), u.getIdFactura(), u.getNombre(),
                u.getDescripcion(), u.getCantidadTotal(), u.getPrecioUnitario(), u.getSubtotal()}));
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DefaultTableModel listarFacturasPendientesProveedor(JTable table, int supplierId) {

        DefaultTableModel model = new DefaultTableModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IDFactura");
        model.addColumn("Proveedor");
        model.addColumn("Fecha Registro");
        model.addColumn("Fecha Vencimiento");
        model.addColumn("Descripcion");
        model.addColumn("Monto Total");
        model.addColumn("Monto Pagado");
        model.addColumn("Monto Pendiente");

        try {
            List<Factura> listaFacturas = dao.obtenerListaFacturasPorProveedorId(supplierId);

            for (Factura u : listaFacturas) {
                if (u.getMontoPendiente() != 0) {
                    try {
                        model.addRow(new Object[]{u.getIdFactura(), dao.buscarNombreProveedorPorFactura(u.getIdFactura()), u.getFechaRegistro(),
                            u.getFechaVencimiento(), u.getDescripcion(), u.getMontoTotal(), u.getMontoPagado(), u.getMontoPendiente()});
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DefaultTableModel listarHistorialPagosProveedor(JTable table, int supplierId) {
        DefaultTableModel model = new DefaultTableModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IDPago");
        model.addColumn("IDFactura");
        model.addColumn("Tipo de pago");
        model.addColumn("Monto de pago");
        model.addColumn("Fecha de pago");

        try {
            dao.obtenerListaPagosFacturasPorIdProveedor(supplierId).forEach((u) -> {
                model.addRow(new Object[]{u.getIdPagoFactura(), u.getFactura().getIdFactura(),
                    u.getTipoPagoFactura(), u.getMontoPago(), u.getFechaPago()});
            });
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public double obtenerDeudaTotalDeProveedor(int supplierId) throws Exception {
        return dao.obtenerDeudaTotalPorProveedor(supplierId);
    }
}
