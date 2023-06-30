/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaRegistroFactura;
import com.proyecto.vista.VentanaRegistroProveedor;
import com.proyecto.vista.VentanaRegistroUsuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorGestorFacturas {

    private DAOEncargadoComprasImpl dao;

    //private Usuario user;
    public ControladorGestorFacturas() {
        this.dao = new DAOEncargadoComprasImpl();
    }

    public DefaultTableModel listarFacturas(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

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
            //DAOUsers dao = new DAOUsersImpl();
            dao.obtenerListaFacturas("").forEach((u) -> {
                try {
                    model.addRow(new Object[]{u.getIdFactura(), dao.buscarNombreProveedorPorFactura(u.getIdFactura()), u.getFechaRegistro(),
                        u.getFechaVencimiento(), u.getDescripcion(), u.getMontoTotal(), u.getMontoPagado(), u.getMontoPendiente()});
                } catch (Exception ex) {
                    Logger.getLogger(ControladorGestorFacturas.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DefaultTableModel eliminarFacturas(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (table.getSelectedRows().length < 1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar uno o más Facturas a eliminar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i : table.getSelectedRows()) {
                int idFactura = (int) table.getValueAt(i, 0);
                try {
                    if (dao.existeRegistroPago(idFactura)) {
                        javax.swing.JOptionPane.showMessageDialog(null, "No se puede eliminar la factura debido a que existen registros de pago asociados.", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    } else {
                        dao.eliminarFactura(idFactura);
                        model.removeRow(i);
                        return model;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return null;
    }

    public void editarFacturas(JTable table) {
        if (table.getSelectedRow() > -1) {
            try {
                int invoiceId = (int) table.getValueAt(table.getSelectedRow(), 0);

                VentanaDashboard.ShowJPanelWindows(new VentanaRegistroFactura(dao.obtenerFacturaPorId(invoiceId)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar el proveedor a editar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel buscarFacturas(JTable table, String name) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            dao.obtenerListaFacturas("").forEach((u) -> model.addRow(new Object[]{u.getIdFactura(), u.getIdProveedor(), u.getFechaRegistro(),
                u.getFechaVencimiento(), u.getDescripcion(), u.getMontoTotal(), u.getMontoPagado(), u.getMontoPendiente()}));

            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
