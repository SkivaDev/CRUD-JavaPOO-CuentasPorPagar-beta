/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.utils;

/**
 *
 * @author skiva
 */
public class Utils {

    public static String generateUsername(String nombre, String apellidoPaterno, String apellidoMaterno, String dni) {
        // Obtener las iniciales del nombre y apellido
        String initials = String.valueOf(nombre.charAt(0)) + String.valueOf(apellidoPaterno.charAt(0)) + String.valueOf(apellidoMaterno.charAt(0));

        // Obtener los primeros 3 dígitos del DNI
        String dniDigits = dni.substring(0, 3);

        // Concatenar las iniciales y los dígitos del DNI
        String username = initials + dniDigits;

        return username;
    }

    public static String generatePassword(String palabra) {
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

    public static void main(String[] args) {
        // Ejemplo de uso
        String nombre = "Fabrizio";
        String apellidoPaterno = "Ortiz";
        String apellidoMaterno = "Orellana";
        String dni = "72193292";

        String username = generateUsername(nombre, apellidoPaterno, apellidoMaterno, dni);
        System.out.println("Nombre de usuario: " + username);

        String password = generatePassword(nombre);
        System.out.println("Contraseña: " + password);
    }
}
