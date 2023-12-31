/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAlmaceneroImpl;
import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.baseDatos.consultas.DAOTesoreroImpl;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.Usuario;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaBETAAAExpedienteProveedor;
import com.proyecto.vista.VentanaDetalleExpedienteProveedor;
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
public class ControladorGestorPagosFacturas {

    private DAOTesoreroImpl dao;

    //private Usuario user;
    public ControladorGestorPagosFacturas() {
        this.dao = new DAOTesoreroImpl();
    }

    public DefaultTableModel listarPagosFacturas(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IDPago");
        model.addColumn("IDFactura");
        model.addColumn("Proveedor");
        model.addColumn("Tipo de pago");
        model.addColumn("Monto de pago");
        model.addColumn("Fecha de pago");

        try {
            //DAOUsers dao = new DAOUsersImpl();
            dao.obtenerListaPagosFacturas("").forEach((u) -> {
                String nombreProveedor = "Ninguno";
                try {
                    Proveedor proveedor = dao.obtenerProveedorPorId(u.getFactura().getIdProveedor());
                    nombreProveedor = proveedor.getNombre();
                } catch (Exception ex) {
                    Logger.getLogger(ControladorGestorPagosFacturas.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                model.addRow(new Object[]{u.getIdPagoFactura(), u.getFactura().getIdFactura(), nombreProveedor,
                    u.getTipoPagoFactura(), u.getMontoPago(), u.getFechaPago()});
            });
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
    public DefaultTableModel eliminarProveedores(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (table.getSelectedRows().length < 1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar uno o más proveedores a eliminar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i : table.getSelectedRows()) {
                try {
                    //dao.eliminar((int) jTable1.getValueAt(i, 0));
                    dao.eliminarProveedor((int) table.getValueAt(i, 0));
                    model.removeRow(i);
                    return model;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    public void editarProveedores(JTable table) {
        if (table.getSelectedRow() > -1) {
            try {
                int supplierId = (int) table.getValueAt(table.getSelectedRow(), 0);

                VentanaDashboard.ShowJPanelWindows(new VentanaRegistroProveedor(dao.obtenerProveedorPorId(supplierId)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar el proveedor a editar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    */
    
    public DefaultTableModel buscarProveedorPagosFacturas(JTable table, String name) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            dao.obtenerListaPagosFacturas(name).forEach((u) -> {
                String nombreProveedor = "Ninguno";
                try {
                    Proveedor proveedor = dao.obtenerProveedorPorId(u.getFactura().getIdProveedor());
                    nombreProveedor = proveedor.getNombre();
                } catch (Exception ex) {
                    Logger.getLogger(ControladorGestorPagosFacturas.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                model.addRow(new Object[]{u.getIdPagoFactura(), u.getFactura().getIdFactura(), nombreProveedor,
                    u.getTipoPagoFactura(), u.getMontoPago(), u.getFechaPago()});
            });
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
