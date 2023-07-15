/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.proyecto.cuentasporpagarbeta;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.ui.FlatBorder;
import com.proyecto.vista.VentanaLogin;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

/**
 *
 * @author skiva
 */
public class Main {

    public static void main(String[] args) {
        // Configurar el tema Flat Light de macOS
        FlatMacLightLaf.setup();
        Color primaryColor = new Color(255, 51, 51);

        //UIManager.put("Table.gridColor", primaryColor);
        
        
        //Borde de focus
        UIManager.put("Component.focusedBorderColor", primaryColor);
        UIManager.put("Component.focusColor", primaryColor);
        UIManager.put("Component.focusedBorderColor", primaryColor);
        
        //TABLE
        UIManager.put("Table.selectionBackground", primaryColor);
        UIManager.put("Table.gridColor", primaryColor);
        UIManager.put("Table.gridColor", primaryColor);
        UIManager.put("Table.gridColor", primaryColor);
        
        //UIManager.put("TextField.border", primaryColor);
        //UIManager.put("Button.background", primaryColor);
        // Configurar el borde personalizado en el foco de los campos de texto
        // Configurar el borde personalizado en el foco de los campos de texto
        /*
        UIManager.put("TextField.border",
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(primaryColor),
                        BorderFactory.createEmptyBorder(2, 5, 2, 5)
                )
        );*/
        //UIManager.put( "Button.arc", 999 );

        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.setVisible(true);
    }
}
