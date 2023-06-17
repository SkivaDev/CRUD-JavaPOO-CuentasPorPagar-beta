/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import com.proyecto.entidades.Administrador;
import com.proyecto.entidades.Almacenero;
import com.proyecto.entidades.EncargadoCompras;
import com.proyecto.entidades.JefeFinanzas;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.generarNumeroRandom;

/**
 *
 * @author skiva
 */
public class ControladorRegistroUsuario {

    private DAOAdministradorImpl dao;
    private Usuario user;

    public ControladorRegistroUsuario() {
        this.dao = new DAOAdministradorImpl();
    }

    public void registrarUsuario(String nombre, String apP, String apM, String dni, String telefono, String username, String password, String rol) throws Exception {
        switch (rol) {
            case "Encargado de Compras":
                user = new EncargadoCompras(0, nombre, apP, apM, dni, telefono, username, password, rol);
                break;
            case "Administrador":
                user = new Administrador(0, nombre, apP, apM, dni, telefono, username, password, rol);
                break;
            case "Almacenero":
                user = new Almacenero(0, nombre, apP, apM, dni, telefono, username, password, rol);
                break;
            case "Tesorero":
                user = new Tesorero(0, nombre, apP, apM, dni, telefono, username, password, rol);
                break;
            case "JefeFinanzas":
                user = new JefeFinanzas(0, nombre, apP, apM, dni, telefono, username, password, rol);
                break;
            default:
                break;
        }
        dao.registrarUsuario(user);
    }

    public void editarUsuario(Usuario userEdition) throws Exception {
        dao.modificarUsuario(userEdition);
    }

    public String generarUsernameUsuario(String nombre, String apellidoPaterno, String apellidoMaterno, String dni) throws Exception {
        String initials = String.valueOf(nombre.charAt(0)) + String.valueOf(apellidoPaterno.charAt(0)) + String.valueOf(apellidoMaterno.charAt(0));
        String dniDigits = dni.substring(0, 3);

        String username = initials + dniDigits;

        while (dao.usernameEnUso(username)) {
            // Generar un nuevo username agregando un número aleatorio al final
            username = initials + dniDigits + generarNumeroRandom();
        }

        return username;
    }

    public String generarPassword(String palabra) {
        // Tomar la primera letra de cada palabra y combinar con caracteres especiales y números
        String password = String.valueOf(palabra.charAt(0)).toUpperCase();

        for (int i = 1; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);

            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    password += Character.toLowerCase(ch);
                } else {
                    password += Character.toUpperCase(ch);
                }
            } else {
                password += ch;
            }
        }

        // Agregar símbolos y números adicionales para mayor seguridad
        password += "@$!%&123";

        return password;
    }

}
