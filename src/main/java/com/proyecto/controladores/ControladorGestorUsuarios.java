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

    public void listarUsuarios(JTable table) {
        try {
            //DAOUsers dao = new DAOUsersImpl();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            dao.obtenerListaUsuarios("").forEach((u) -> model.addRow(new Object[]{u.getIdUsuario(), u.getNombre(), u.getApellido_p(), u.getApellido_m(), u.getDni(), u.getTelefono(), u.getUsername(), u.getPassword()}));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
