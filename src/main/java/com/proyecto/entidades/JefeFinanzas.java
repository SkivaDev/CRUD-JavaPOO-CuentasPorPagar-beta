/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.entidades;

/**
 *
 * @author skiva
 */
public class JefeFinanzas extends Usuario {

    public JefeFinanzas(int idUsuario, String nombre, String apellido_p, String apellido_m, String dni, String telefono, String username, String password, String rol) {
        super(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
    }

    @Override
    public boolean autenticar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
