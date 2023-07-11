/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.baseDatos.consultas.DAOTesoreroImpl;
import com.proyecto.entidades.Usuario;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaBETAAAExpedienteProveedor;
import com.proyecto.vista.VentanaDetalleExpedienteProveedor;
import com.proyecto.vista.VentanaRegistroProveedor;
import com.proyecto.vista.VentanaRegistroUsuario;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorGestorSolicitudesPago {

    private DAOTesoreroImpl dao;

    //private Usuario user;
    public ControladorGestorSolicitudesPago() {
        this.dao = new DAOTesoreroImpl();
    }

    public DefaultTableModel listarSolicitudes(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IDSolicitud");
        model.addColumn("IDFactura");
        model.addColumn("Metodo Pago");
        model.addColumn("IDCheque");
        model.addColumn("IDCanje");
        model.addColumn("Estado solicitud");

        try {
            //DAOUsers dao = new DAOUsersImpl();
            //dao.obtenerListaSolicitudesPago().forEach((u) -> model.addRow(new Object[]{u.getIdSolicitudPago(), 
             //   u.getFactura().getIdFactura(), u.getMetodoPago(), u.getCheque().getIdCheque(), u.getCanje().getIdCanje(), u.getEstadoSolicitud()}));
            
            dao.obtenerListaSolicitudesPago().forEach((u) -> model.addRow(new Object[]{u.getIdSolicitudPago(), 
                u.getFactura().getIdFactura(), u.getMetodoPago(), "null", "null", u.getEstadoSolicitud()}));
            
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

    public DefaultTableModel buscarProveedores(JTable table, String name) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            dao.obtenerListaProveedores(name).forEach((u) -> model.addRow(new Object[]{u.getIdProveedor(), u.getNombre(), u.getDireccion(), u.getTelefono(), u.getLineaCredito()}));
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void mostrarExpedienteProveedor(JTable table, Usuario currentUser) {
        if (table.getSelectedRow() > -1) {
            try {
                int supplierId = (int) table.getValueAt(table.getSelectedRow(), 0);

                VentanaDashboard.ShowJPanelWindows(new VentanaDetalleExpedienteProveedor(dao.obtenerProveedorPorId(supplierId), currentUser));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar el proveedor para mostrar su expediente.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
*/
}
