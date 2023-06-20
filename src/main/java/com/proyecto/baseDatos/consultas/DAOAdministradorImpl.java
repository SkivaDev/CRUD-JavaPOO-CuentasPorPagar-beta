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
import com.proyecto.interfaces.DAOAdministradorInterfaz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                throw new SQLException("Error al obtener el ID generado para el Usuario");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el usuario en la base de datos", e);
        }
    }

    @Override
    public void modificarUsuario(Usuario user) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE usuarios SET nombre = ?, apellido_p = ?, apellido_m = ?, dni = ?, telefono = ?, username = ?, password = ?, rol = ? WHERE id_usuario = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido_p());
            statement.setString(3, user.getApellido_m());
            statement.setString(4, user.getDni());
            statement.setString(5, user.getTelefono());
            statement.setString(6, user.getUsername());
            statement.setString(7, user.getPassword());
            statement.setString(8, user.getRol());
            statement.setInt(9, user.getIdUsuario());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar el usuario en la base de datos", e);
        }
    }

    @Override
    public void eliminarUsuario(int userId) throws Exception {
        try {
            this.Conectar();
            String consulta = "DELETE FROM usuarios WHERE id_usuario = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el usuario de la base de datos", e);
        }
    }

    @Override
    public List<Usuario> obtenerListaUsuarios(String name) throws Exception {
        try {
            this.Conectar();
            String consulta = name.isEmpty() ? "SELECT * FROM usuarios" : "SELECT * FROM usuarios WHERE nombre LIKE '%" + name + "%';";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            List<Usuario> usuarios = new ArrayList<>();
            while (resultSet.next()) {
                int idUsuario = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String apellido_p = resultSet.getString("apellido_p");
                String apellido_m = resultSet.getString("apellido_m");
                String dni = resultSet.getString("dni");
                String telefono = resultSet.getString("telefono");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String rol = resultSet.getString("rol");
                Usuario usuario;

                switch (rol) {
                    case "Encargado de Compras":
                        usuario = new EncargadoCompras(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                        usuarios.add(usuario);
                        break;
                    case "Administrador":
                        usuario = new Administrador(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                        usuarios.add(usuario);
                        break;
                    case "Almacenero":
                        usuario = new Almacenero(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                        usuarios.add(usuario);
                        break;
                    case "Tesorero":
                        usuario = new Tesorero(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                        usuarios.add(usuario);
                        break;
                    case "JefeFinanzas":
                        usuario = new JefeFinanzas(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                        usuarios.add(usuario);
                        break;
                    default:
                        break;
                }
            }

            return usuarios;
        } catch (SQLException e) {
            throw new SQLException("Error al consultar la base de datos", e);
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(int userId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM usuarios WHERE id_usuario = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idUsuario = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String apellido_p = resultSet.getString("apellido_p");
                String apellido_m = resultSet.getString("apellido_m");
                String dni = resultSet.getString("dni");
                String telefono = resultSet.getString("telefono");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String rol = resultSet.getString("rol");

                // Construir y retornar el objeto Usuario correspondiente
                switch (rol) {
                    case "Encargado de Compras":
                        return new EncargadoCompras(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Administrador":
                        return new Administrador(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Almacenero":
                        return new Almacenero(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "Tesorero":
                        return new Tesorero(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    case "JefeFinanzas":
                        return new JefeFinanzas(idUsuario, nombre, apellido_p, apellido_m, dni, telefono, username, password, rol);
                    default:
                        break;
                }
            }

            // Si no se encuentra el usuario, puedes lanzar una excepción o retornar null, según tu necesidad
            throw new SQLException("No se encontró ningún usuario con el ID proporcionado");
        } catch (SQLException e) {
            throw new SQLException("Error al consultar la base de datos", e);
        }
    }

    @Override
    public boolean usernameEnUso(String username) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT COUNT(*) FROM usuarios WHERE id_usuario = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            // Cerrar la conexión y liberar recursos
            resultSet.close();
            statement.close();
            this.Cerrar();
        } catch (Exception e) {
            throw new SQLException("Error al consultar si existe un usuario con el username la base de datos", e);
        }

        // Si ocurre algún error, se asume que el nombre de usuario no está tomado
        return false;
    }
}
