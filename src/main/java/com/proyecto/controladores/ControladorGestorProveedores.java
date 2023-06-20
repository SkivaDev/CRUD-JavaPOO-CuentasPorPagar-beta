/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaRegistroUsuario;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorGestorProveedores {

    private DAOEncargadoComprasImpl dao;

    //private Usuario user;
    public ControladorGestorProveedores() {
        this.dao = new DAOEncargadoComprasImpl();
    }

    public DefaultTableModel listarUsuarios(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Dirección");
        model.addColumn("Teléfono");
        model.addColumn("Linea de Credito");

        try {
            //DAOUsers dao = new DAOUsersImpl();
            dao.obtenerListaProveedores("").forEach((u) -> model.addRow(new Object[]{u.getIdProveedor(), u.getNombre(), u.getDireccion(), u.getTelefono(), u.getLineaCredito()}));
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DefaultTableModel eliminarUsuarios(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (table.getSelectedRows().length < 1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar uno o más usuarios a eliminar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i : table.getSelectedRows()) {
                try {
                    //dao.eliminar((int) jTable1.getValueAt(i, 0));
                    dao.eliminarUsuario((int) table.getValueAt(i, 0));
                    model.removeRow(i);
                    return model;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    public void editarUsuarios(JTable table) {
        if (table.getSelectedRow() > -1) {
            try {
                int userId = (int) table.getValueAt(table.getSelectedRow(), 0);
                //DAOUsers dao = new DAOUsersImpl();

                VentanaDashboard.ShowJPanelWindows(new VentanaRegistroUsuario(dao.obtenerUsuarioPorId(userId)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar el usuario a editar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel buscarUsuarios(JTable table, String name) {

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

}
