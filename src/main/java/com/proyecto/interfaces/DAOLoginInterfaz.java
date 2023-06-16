/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.interfaces;
import com.proyecto.entidades.Usuario;

/**
 *
 * @author skiva
 */
public interface DAOLoginInterfaz {
    public Usuario obtenerUsuarioPorCredenciales(String username, String password) throws Exception;
}
