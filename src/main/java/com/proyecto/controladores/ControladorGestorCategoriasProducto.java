/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAlmaceneroImpl;
import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.entidades.Usuario;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaBETAAAExpedienteProveedor;
import com.proyecto.vista.VentanaDetalleExpedienteProveedor;
import com.proyecto.vista.VentanaRegistroCategoriaProducto;
import com.proyecto.vista.VentanaRegistroProveedor;
import com.proyecto.vista.VentanaRegistroUsuario;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorGestorCategoriasProducto {

    private DAOAlmaceneroImpl dao;

    //private Usuario user;
    public ControladorGestorCategoriasProducto() {
        this.dao = new DAOAlmaceneroImpl();
    }

    public DefaultTableModel listarCategoriasProducto(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IDCategoria");
        model.addColumn("Nombre Categoria");
        model.addColumn("Descripción");

        try {
            //DAOUsers dao = new DAOUsersImpl();
            dao.obtenerListaCategoriasProducto("").forEach((u) -> model.addRow(new Object[]{u.getIdCategoriaProducto(), u.getNombreCategoria(), u.getDescripcionCategoria()}));
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DefaultTableModel eliminarProveedores(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (table.getSelectedRows().length < 1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar uno o más proveedores a eliminar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i : table.getSelectedRows()) {
                try {
                    //dao.eliminar((int) jTable1.getValueAt(i, 0));
                    int idCategoria = (int) table.getValueAt(i, 0);
                    boolean categoriaEnUso = dao.categoriaRegistradaEnUso(idCategoria);
                    if (categoriaEnUso) {
                        javax.swing.JOptionPane.showMessageDialog(null, "La categoria seleccionada esta en uso, asi que no se puede eliminar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    } else {
                        dao.eliminarCategoriaProducto(idCategoria);
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

    public void editarCategoriaProducto(JTable table) {
        if (table.getSelectedRow() > -1) {
            try {
                int categoryId = (int) table.getValueAt(table.getSelectedRow(), 0);

                VentanaDashboard.ShowJPanelWindows(new VentanaRegistroCategoriaProducto(dao.obtenerCategoriaProductoPorId(categoryId)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar el proveedor a editar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel buscarCategoriasProducto(JTable table, String name) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            dao.obtenerListaCategoriasProducto(name).forEach((u) -> model.addRow(new Object[]{u.getIdCategoriaProducto(), u.getNombreCategoria(), u.getDescripcionCategoria()}));
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
