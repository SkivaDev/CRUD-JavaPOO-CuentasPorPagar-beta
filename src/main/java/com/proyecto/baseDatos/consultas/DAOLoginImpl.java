/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.baseDatos.consultas;

import com.proyecto.baseDatos.GestorBaseDatos;
import com.proyecto.entidades.Administrador;
import com.proyecto.entidades.Almacenero;
import com.proyecto.entidades.EncargadoCompras;
import com.proyecto.entidades.JefeFinanzas;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import com.proyecto.interfaces.DAOLoginInterfaz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author skiva
 */
public class DAOLoginImpl extends GestorBaseDatos implements DAOLoginInterfaz {

    @Override
    public Usuario obtenerUsuarioPorCredenciales(String username, String password) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idUsuario = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String apellido_p = resultSet.getString("apellido_p");
                String apellido_m = resultSet.getString("apellido_m");
                String dni = resultSet.getString("dni");
                String telefono = resultSet.getString("telefono");
                String rol = resultSet.getString("rol");

                switch (rol) {
                    case "Encargado de Compras":
                        return new EncargadoCompras(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Administrador":
                        return new Administrador(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Almacenero":
                        return new Almacenero(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Tesorero":
                        return new Tesorero(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Jefe de Finanzas":
                        return new JefeFinanzas(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    default:
                        break;
                }
            }

            throw new SQLException("Credenciales inválidas");
        } catch (SQLException e) {
            throw new SQLException("Error al consultar la base de datos", e);
        }
    }

    @Override
    public Usuario obtenerUsuarioPorDni(String dni) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM usuarios WHERE dni = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idUsuario = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String apellido_p = resultSet.getString("apellido_p");
                String apellido_m = resultSet.getString("apellido_m");
                String telefono = resultSet.getString("telefono");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String rol = resultSet.getString("rol");

                switch (rol) {
                    case "Encargado de Compras":
                        return new EncargadoCompras(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Administrador":
                        return new Administrador(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Almacenero":
                        return new Almacenero(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Tesorero":
                        return new Tesorero(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Jefe de Finanzas":
                        return new JefeFinanzas(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    default:
                        break;
                }
            }

            throw new SQLException("No se encontró ningún usuario con el DNI especificado");
        } catch (SQLException e) {
            throw new SQLException("Error al consultar la base de datos", e);
        }
    }

}
