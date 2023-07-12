/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.utils;

import com.proyecto.cuentasporpagarbeta.Main;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author skiva
 */
public class Utils {

    public static String generarNumeroRandom() {
        // Generar un número aleatorio entre 0 y 999 (ajústalo según tus necesidades)
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return String.format("%03d", randomNumber); // Formatear el número con tres dígitos
    }

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

    public static String obtenerFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActualFormateada = fechaActual.format(formato);
        return fechaActualFormateada;
    }

    /*
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
     */
    public static void reiniciarPrograma() {
        try {
            String javaHome = System.getProperty("java.home");
            String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
            String classPath = System.getProperty("java.class.path");
            String className = Main.class.getName();

            // Crea el comando para reiniciar el programa
            List<String> command = new ArrayList<>();
            command.add(javaBin);
            command.add("-cp");
            command.add(classPath);
            command.add(className);

            // Ejecuta el comando para reiniciar el programa
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();

            // Cierra el programa actual
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
