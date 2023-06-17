/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import static com.proyecto.utils.Utils.generarNumeroRandom;

/**
 *
 * @author skiva
 */
public class ControladorRegistroUsuario {

    private DAOAdministradorImpl dao;

    public ControladorRegistroUsuario() {
        this.dao = new DAOAdministradorImpl();
    }

    public void registrarUsuario() {

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
