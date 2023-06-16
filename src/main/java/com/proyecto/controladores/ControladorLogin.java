/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOLoginImpl;
import com.proyecto.entidades.Administrador;
import com.proyecto.entidades.Almacenero;
import com.proyecto.entidades.EncargadoCompras;
import com.proyecto.entidades.JefeFinanzas;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import com.proyecto.vista.VentanaDashboard;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author skiva
 */
public class ControladorLogin {

    private DAOLoginImpl dao;
    private Usuario user;

    public ControladorLogin() {
        this.dao = new DAOLoginImpl();
    }

    public void iniciarSesion(String username, String password) throws Exception {

        user = dao.obtenerUsuarioPorCredenciales(username, password);

        if (user != null) {
            //usuario.autenticar(username, password);
            // El usuario ha sido autenticado correctamente.
            // Realiza las acciones necesarias, como mostrar el menú principal, redireccionar a otra ventana, etc.
            if (user instanceof EncargadoCompras) {
                JOptionPane.showMessageDialog(null, "Rol es ENCARGADOCOMPRAS");
                VentanaDashboard ventanaDashboard = new VentanaDashboard(user);
                ventanaDashboard.setVisible(true);
                //ControladorPrincipal controladorPrincipal = new ControladorPrincipal(usuario);
                //ventanaLogin.cerrar();
            } else if (user instanceof Administrador) {
                JOptionPane.showMessageDialog(null, "Rol es Admin pro");
                VentanaDashboard ventanaDashboard = new VentanaDashboard(user);
                ventanaDashboard.setVisible(true);
                //ControladorPrincipal controladorPrincipal = new ControladorPrincipal(usuario);
                //ventanaLogin.cerrar();
            } else if (user instanceof Almacenero) {
                JOptionPane.showMessageDialog(null, "Rol es Almacenero");
                VentanaDashboard ventanaDashboard = new VentanaDashboard(user);
                ventanaDashboard.setVisible(true);
                //ControladorPrincipal controladorPrincipal = new ControladorPrincipal(usuario);
                //ventanaLogin.cerrar();
            } else if (user instanceof Tesorero) {
                JOptionPane.showMessageDialog(null, "Rol es Tesorero");
                VentanaDashboard ventanaDashboard = new VentanaDashboard(user);
                ventanaDashboard.setVisible(true);
                //ControladorPrincipal controladorPrincipal = new ControladorPrincipal(usuario);
                //ventanaLogin.cerrar();
            } else if (user instanceof JefeFinanzas) {
                JOptionPane.showMessageDialog(null, "Rol es JefeFinanzas");
                VentanaDashboard ventanaDashboard = new VentanaDashboard(user);
                ventanaDashboard.setVisible(true);
                //ControladorPrincipal controladorPrincipal = new ControladorPrincipal(usuario);
                // ventanaLogin.cerrar();
            } else {
                JOptionPane.showMessageDialog(null, "Rol de usuario no valido");
                // ventanaLogin.mostrarMensajeError("Rol de usuario no válido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con las credenciales proporcionadas");
            // Muestra un mensaje de error o realiza otras acciones apropiadas.
        }

    }

}