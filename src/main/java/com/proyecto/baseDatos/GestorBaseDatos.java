/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author skiva
 */
public class GestorBaseDatos {

    private static final String URL = "jdbc:mysql://localhost:3306/bdpruebabetavip";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789";

    protected Connection conexion;

    public void Conectar() throws SQLException {
        try {
            conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    public void Cerrar() throws SQLException {
        try {
            if (conexion != null) {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
        }

    }

}
