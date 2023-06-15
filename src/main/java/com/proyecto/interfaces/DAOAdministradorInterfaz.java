/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.interfaces;

import com.proyecto.entidades.Usuario;
import java.util.List;

/**
 *
 * @author skiva
 */
public interface DAOAdministradorInterfaz {
    public void registrarUsuario(Usuario user) throws Exception;
    public void modificarUsuario(Usuario user) throws Exception;
    public void eliminarUsuario(int userId) throws Exception;
    public List<Usuario> listarUsuarios(String name) throws Exception;
   // public Users getUserById(int userId) throws Exception;
}
