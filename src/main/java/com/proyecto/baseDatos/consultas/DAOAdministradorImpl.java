/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.baseDatos.consultas;

import com.proyecto.baseDatos.GestorBaseDatos;
import com.proyecto.entidades.Usuario;
import com.proyecto.interfaces.DAOAdministradorInterfaz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author skiva
 */
public class DAOAdministradorImpl extends GestorBaseDatos implements DAOAdministradorInterfaz {

    @Override
    public void registrarUsuario(Usuario user) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO usuarios (nombre, apellido_p, apellido_m, dni, telefono, username, password, rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido_p());
            statement.setString(3, user.getApellido_m());
            statement.setString(4, user.getDni());
            statement.setString(5, user.getTelefono());
            statement.setString(6, user.getUsername());
            statement.setString(7, user.getPassword());
            statement.setString(8, user.getRol());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idUsuario = generatedKeys.getInt(1);
                user.setIdUsuario(idUsuario);
            } else {
                throw new SQLException("Error al obtener el ID generado para la factura");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el usuario en la base de datos", e);
        }
    }

    @Override
    public void modificarUsuario(Usuario user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarUsuario(int userId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> listarUsuarios(String name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
