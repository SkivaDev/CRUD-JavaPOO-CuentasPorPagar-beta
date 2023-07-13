/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.baseDatos.consultas.DAOJefeFinanzasImpl;
import com.proyecto.entidades.Usuario;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaBETAAAExpedienteProveedor;
import com.proyecto.vista.VentanaDetalleExpedienteProveedor;
import com.proyecto.vista.VentanaRegistroIngresoBancario;
import com.proyecto.vista.VentanaRegistroProveedor;
import com.proyecto.vista.VentanaRegistroUsuario;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorGestorCuentasBancarias {

    private DAOJefeFinanzasImpl dao;

    //private Usuario user;
    public ControladorGestorCuentasBancarias() {
        this.dao = new DAOJefeFinanzasImpl();
    }

    public DefaultTableModel listarCuentasBancarias(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IDCuentaBancaria");
        model.addColumn("Entidad Bancaria");
        model.addColumn("Tipo Cuenta Bancaria");
        model.addColumn("Saldo Actual");
        model.addColumn("Saldo Previo");

        try {
            //DAOUsers dao = new DAOUsersImpl();
            dao.obtenerListaCuentasBancarias("").forEach((u) -> model.addRow(new Object[]{u.getIdCuentaBancaria(), u.getNombreBanco(), u.getTipoCuentaBancaria(), u.getSaldoActual(), u.getSaldoPrevio()}));
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
    }*/

    
    public void agregarFondosCuentaBancaria(JTable table) {
        if (table.getSelectedRow() > -1) {
            try {
                int bankAccountId = (int) table.getValueAt(table.getSelectedRow(), 0);

                VentanaDashboard.ShowJPanelWindows(new VentanaRegistroIngresoBancario(dao.obtenerCuentaBancariaPorId(bankAccountId)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar la cuenta bancaria para añadir fondos.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel buscarEntidadesBancarias(JTable table, String name) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            dao.obtenerListaCuentasBancarias(name).forEach((u) -> model.addRow(new Object[]{u.getIdCuentaBancaria(), u.getNombreBanco(), u.getTipoCuentaBancaria(), u.getSaldoActual(), u.getSaldoPrevio()}));
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
