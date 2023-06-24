/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOLoginImpl;
import com.proyecto.entidades.Usuario;
import com.proyecto.vista.VentanaLogin;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author skiva
 */
public class ControladorRecuperarContraseña {

    private DAOLoginImpl dao;
    private Usuario user;

    public ControladorRecuperarContraseña() {
        this.dao = new DAOLoginImpl();
    }

    public void recuperarContraseña(String dni, JLabel usernameField, JLabel passwordField) throws Exception {

        user = dao.obtenerUsuarioPorDni(dni);

        if (user != null) {
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el DNI proporcionado");
        }

    }
    
    public void regresarVentanaLogin() {
        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.setVisible(true);
    }
}
