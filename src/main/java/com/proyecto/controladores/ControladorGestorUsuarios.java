/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorGestorUsuarios {

    private DAOAdministradorImpl dao;

    //private Usuario user;
    public ControladorGestorUsuarios() {
        this.dao = new DAOAdministradorImpl();
    }

    public DefaultTableModel listarUsuarios(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido P");
        model.addColumn("Apellido M");
        model.addColumn("DNI");
        model.addColumn("Telefono");
        model.addColumn("Usarname");
        model.addColumn("Password");
        model.addColumn("Rol");

        try {
            //DAOUsers dao = new DAOUsersImpl();
            dao.obtenerListaUsuarios("").forEach((u) -> model.addRow(new Object[]{u.getIdUsuario(), u.getNombre(), u.getApellido_p(), u.getApellido_m(), u.getDni(), u.getTelefono(), u.getUsername(), u.getPassword(), u.getRol()}));
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

}
