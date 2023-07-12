/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.baseDatos.consultas.DAOJefeFinanzasImpl;
import com.proyecto.baseDatos.consultas.DAOTesoreroImpl;
import com.proyecto.entidades.Canje;
import com.proyecto.entidades.Cheque;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.SolicitudPago;
import com.proyecto.entidades.Usuario;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaBETAAAExpedienteProveedor;
import com.proyecto.vista.VentanaDetalleExpedienteProveedor;
import com.proyecto.vista.VentanaRegistroPagoFactura;
import com.proyecto.vista.VentanaRegistroProveedor;
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
public class ControladorGestorPagarFacturas {

    private DAOTesoreroImpl dao;

    //private Usuario user;
    public ControladorGestorPagarFacturas() {
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
        model.addColumn("Fecha Registro");
        model.addColumn("Estado solicitud");

        try {
            //DAOUsers dao = new DAOUsersImpl();
            //dao.obtenerListaSolicitudesPago().forEach((u) -> model.addRow(new Object[]{u.getIdSolicitudPago(), 
            //   u.getFactura().getIdFactura(), u.getMetodoPago(), u.getCheque().getIdCheque(), u.getCanje().getIdCanje(), u.getEstadoSolicitud()}));

            //dao.obtenerListaSolicitudesPago().forEach((u) -> model.addRow(new Object[]{u.getIdSolicitudPago(),
            //    u.getFactura().getIdFactura(), u.getMetodoPago(), "null", "null", u.getEstadoSolicitud()}));
            dao.obtenerListaSolicitudesPago().forEach((u) -> {
                Object idCheque = (u.getCheque() != null) ? u.getCheque().getIdCheque() : "null";
                Object idCanje = (u.getCanje() != null) ? u.getCanje().getIdCanje() : "null";

                model.addRow(new Object[]{u.getIdSolicitudPago(), u.getFactura().getIdFactura(), u.getMetodoPago(), idCheque, idCanje, u.getFechaRegistro(), u.getEstadoSolicitud()});
            });

            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void efectuarPagoSolicitud(JTable table) {
        if (table.getSelectedRow() > -1) {
            try {
                //VALIDACION: el estado actual de la solicitud debe ser pendiente
                String estadoActualSolicitud = (String) table.getValueAt(table.getSelectedRow(), 6);
                if (estadoActualSolicitud.equals("Aprobado")) {
                    int requestId = (int) table.getValueAt(table.getSelectedRow(), 0);
                    SolicitudPago solicitudPago = dao.obtenerSolicitudPagoPorId(requestId);
                    
                    int invoiceId = (int) table.getValueAt(table.getSelectedRow(), 1);
                    Factura factura = dao.obtenerFacturaPorId(invoiceId);
                    
                    Object valorChequeRegistrado = table.getValueAt(table.getSelectedRow(), 3);
                    Object valorCanjeRegistrado = table.getValueAt(table.getSelectedRow(), 4);
                    
                    if(valorChequeRegistrado instanceof Integer && valorCanjeRegistrado.equals("null")){
                        int checkId = (int) table.getValueAt(table.getSelectedRow(), 3);
                        Cheque check = dao.obtenerChequePorId(checkId);
                        
                        if(check.getEstadoCheque().equals("Emitido")){ //VALIDACION: Solo se puede pagar solicitudes que no han sido pagadas previamente
                            VentanaDashboard.ShowJPanelWindows(new VentanaRegistroPagoFactura(factura, solicitudPago, check));
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "El pago del cheque de la solicitud de pago selecionada ya ha sido realizada.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        int exchangeId = (int) table.getValueAt(table.getSelectedRow(), 4);
                        Canje exchange = dao.obtenerCanjePorId(exchangeId);
                        
                        if(exchange.getEstadoCanje().equals("Emitido")){
                            VentanaDashboard.ShowJPanelWindows(new VentanaRegistroPagoFactura(factura, solicitudPago, exchange));
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "El pago del canje de la solicitud de pago selecionada ya ha sido realizada.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }                        
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Solo puedes efectuar pago de solicitudes aprobadas.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar la solicitud a aprobar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel filtrarSolicitudesAprobadas(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            List<SolicitudPago> solicitudesPago = dao.obtenerListaSolicitudesPago();

            for (SolicitudPago u : solicitudesPago) {
                if (u.getEstadoSolicitud().equals("Aprobado")) {
                    try {
                        Object idCheque = (u.getCheque() != null) ? u.getCheque().getIdCheque() : "null";
                        Object idCanje = (u.getCanje() != null) ? u.getCanje().getIdCanje() : "null";
                        model.addRow(new Object[]{u.getIdSolicitudPago(), u.getFactura().getIdFactura(), u.getMetodoPago(), idCheque, idCanje, u.getFechaRegistro(), u.getEstadoSolicitud()});
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorGestorFacturas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DefaultTableModel filtrarSolicitudesDesaprobadas(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            List<SolicitudPago> solicitudesPago = dao.obtenerListaSolicitudesPago();

            for (SolicitudPago u : solicitudesPago) {
                if (u.getEstadoSolicitud().equals("Desaprobado")) {
                    try {
                        Object idCheque = (u.getCheque() != null) ? u.getCheque().getIdCheque() : "null";
                        Object idCanje = (u.getCanje() != null) ? u.getCanje().getIdCanje() : "null";
                        model.addRow(new Object[]{u.getIdSolicitudPago(), u.getFactura().getIdFactura(), u.getMetodoPago(), idCheque, idCanje, u.getFechaRegistro(), u.getEstadoSolicitud()});
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorGestorFacturas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DefaultTableModel filtrarSolicitudesPendientes(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            List<SolicitudPago> solicitudesPago = dao.obtenerListaSolicitudesPago();

            for (SolicitudPago u : solicitudesPago) {
                if (u.getEstadoSolicitud().equals("Pendiente")) {
                    try {
                        Object idCheque = (u.getCheque() != null) ? u.getCheque().getIdCheque() : "null";
                        Object idCanje = (u.getCanje() != null) ? u.getCanje().getIdCanje() : "null";
                        model.addRow(new Object[]{u.getIdSolicitudPago(), u.getFactura().getIdFactura(), u.getMetodoPago(), idCheque, idCanje, u.getFechaRegistro(), u.getEstadoSolicitud()});
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorGestorFacturas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DefaultTableModel filtrarTodasSolicitudes(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            List<SolicitudPago> solicitudesPago = dao.obtenerListaSolicitudesPago();

            for (SolicitudPago u : solicitudesPago) {
                try {
                    Object idCheque = (u.getCheque() != null) ? u.getCheque().getIdCheque() : "null";
                    Object idCanje = (u.getCanje() != null) ? u.getCanje().getIdCanje() : "null";
                    model.addRow(new Object[]{u.getIdSolicitudPago(), u.getFactura().getIdFactura(), u.getMetodoPago(), idCheque, idCanje, u.getFechaRegistro(), u.getEstadoSolicitud()});
                } catch (Exception ex) {
                    Logger.getLogger(ControladorGestorFacturas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
