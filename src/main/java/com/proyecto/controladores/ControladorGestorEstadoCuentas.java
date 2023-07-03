/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.baseDatos.consultas.DAOTesoreroImpl;
import com.proyecto.entidades.Factura;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaRegistroProveedor;
import com.proyecto.vista.VentanaRegistroUsuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorGestorEstadoCuentas {

    private DAOTesoreroImpl dao;

    //private Usuario user;
    public ControladorGestorEstadoCuentas() {
        this.dao = new DAOTesoreroImpl();
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

    public DefaultTableModel filtrarPor30Dias(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            List<Factura> listaFacturas = dao.obtenerListaFacturas("");
            Calendar calendar = Calendar.getInstance();

            // Obtener la fecha actual
            Date fechaActual = calendar.getTime();

            // Añadir 30 días a la fecha actual
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date fechaLimite = calendar.getTime();

            for (Factura u : listaFacturas) {
                Date fechaVencimiento = u.getFechaVencimiento();
                if (fechaVencimiento != null && fechaVencimiento.after(fechaActual) && fechaVencimiento.before(fechaLimite)) {
                    try {
                        model.addRow(new Object[]{u.getIdFactura(), dao.buscarNombreProveedorPorFactura(u.getIdFactura()), u.getFechaRegistro(),
                            u.getFechaVencimiento(), u.getDescripcion(), u.getMontoTotal(), u.getMontoPagado(), u.getMontoPendiente()});
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

    public DefaultTableModel filtrarPor60Dias(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            List<Factura> listaFacturas = dao.obtenerListaFacturas("");
            Calendar calendar = Calendar.getInstance();

            // Obtener la fecha actual
            Date fechaActual = calendar.getTime();

            // Añadir 30 días a la fecha actual
            calendar.add(Calendar.DAY_OF_MONTH, 60);
            Date fechaLimite = calendar.getTime();

            for (Factura u : listaFacturas) {
                Date fechaVencimiento = u.getFechaVencimiento();
                if (fechaVencimiento != null && fechaVencimiento.after(fechaActual) && fechaVencimiento.before(fechaLimite)) {
                    try {
                        model.addRow(new Object[]{u.getIdFactura(), dao.buscarNombreProveedorPorFactura(u.getIdFactura()), u.getFechaRegistro(),
                            u.getFechaVencimiento(), u.getDescripcion(), u.getMontoTotal(), u.getMontoPagado(), u.getMontoPendiente()});
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

    public DefaultTableModel filtrarPor90Dias(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            List<Factura> listaFacturas = dao.obtenerListaFacturas("");
            Calendar calendar = Calendar.getInstance();

            // Obtener la fecha actual
            Date fechaActual = calendar.getTime();

            // Añadir 30 días a la fecha actual
            calendar.add(Calendar.DAY_OF_MONTH, 90);
            Date fechaLimite = calendar.getTime();

            for (Factura u : listaFacturas) {
                Date fechaVencimiento = u.getFechaVencimiento();
                if (fechaVencimiento != null && fechaVencimiento.after(fechaActual) && fechaVencimiento.before(fechaLimite)) {
                    try {
                        model.addRow(new Object[]{u.getIdFactura(), dao.buscarNombreProveedorPorFactura(u.getIdFactura()), u.getFechaRegistro(),
                            u.getFechaVencimiento(), u.getDescripcion(), u.getMontoTotal(), u.getMontoPagado(), u.getMontoPendiente()});
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

    public DefaultTableModel filtrarTodasFacturas(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
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

    public DefaultTableModel filtrarFacturasPendientes(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            List<Factura> listaFacturas = dao.obtenerListaFacturas("");

            for (Factura u : listaFacturas) {
                if (u.getMontoPendiente() != 0) {
                    try {
                        model.addRow(new Object[]{u.getIdFactura(), dao.buscarNombreProveedorPorFactura(u.getIdFactura()), u.getFechaRegistro(),
                            u.getFechaVencimiento(), u.getDescripcion(), u.getMontoTotal(), u.getMontoPagado(), u.getMontoPendiente()});
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

    public DefaultTableModel filtrarFacturasPagadas(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            List<Factura> listaFacturas = dao.obtenerListaFacturas("");

            for (Factura u : listaFacturas) {
                if (u.getMontoPendiente() == 0) {
                    try {
                        model.addRow(new Object[]{u.getIdFactura(), dao.buscarNombreProveedorPorFactura(u.getIdFactura()), u.getFechaRegistro(),
                            u.getFechaVencimiento(), u.getDescripcion(), u.getMontoTotal(), u.getMontoPagado(), u.getMontoPendiente()});
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
    public DefaultTableModel buscarFacturas(JTable table, String name) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            dao.obtenerListaFacturas(name).forEach((u) -> {
                try {
                    model.addRow(new Object[]{u.getIdFactura(), dao.buscarNombreProveedorPorFactura(u.getIdFactura()), u.getFechaRegistro(),
                        u.getFechaVencimiento(), u.getDescripcion(), u.getMontoTotal(), u.getMontoPagado(), u.getMontoPendiente()});
                } catch (Exception ex) {
                    Logger.getLogger(ControladorGestorEstadoCuentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
