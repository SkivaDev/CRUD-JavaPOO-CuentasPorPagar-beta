/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.entidades.Administrador;
import com.proyecto.entidades.Almacenero;
import com.proyecto.entidades.EncargadoCompras;
import com.proyecto.entidades.JefeFinanzas;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.generarNumeroRandom;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author skiva
 */
public class ControladorRegistroProveedor {

    private DAOEncargadoComprasImpl dao;
    private Proveedor supplier;

    public ControladorRegistroProveedor() {
        this.dao = new DAOEncargadoComprasImpl();
    }

    public void registrarProveedor(String nombre, String direccion, String telefono, Double lineaCredito) throws Exception {

        supplier = new Proveedor(0, nombre, direccion, telefono, lineaCredito);
        dao.registrarProveedor(supplier);
        
    }

    public void editarProveedor(int idProveedor, String nombre, String direccion, String telefono, Double lineaCredito) throws Exception {
        
        supplier = new Proveedor(idProveedor, nombre, direccion, telefono, lineaCredito);
        dao.modificarProveedor(supplier);
        
    }

    public boolean validarNumeroTelefono(String numeroTelefono) {
        if (numeroTelefono.length() != 9) {
            return false;
        }

        char primerDigito = numeroTelefono.charAt(0);
        if (primerDigito != '9') {
            return false;
        }

        // Verificar si todos los caracteres restantes son dígitos
        for (int i = 1; i < 9; i++) {
            char digito = numeroTelefono.charAt(i);
            if (!Character.isDigit(digito)) {
                return false;
            }
        }

        return true;
    }
    public boolean confirmarDatosProveedor(String nombre, String direccion, String telefono, Double lineaCredito) {
        String message;
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos del Proveedor *****";
        message += "\nNombre: " + nombre;
        message += "\nDirección: " + direccion;
        message += "\nTeléfono: " + telefono;
        message += "\nLinea de Credito: " + lineaCredito;
        message += "\n";
        message += "\nLos datos son correctos?";

        UIManager.put("Button.yesButtonText", "Aceptar");
        UIManager.put("Button.noButtonText", "Cancelar");

        int result = JOptionPane.showConfirmDialog(null, message, title, optionType);
        switch (result) {
            case JOptionPane.YES_OPTION:
                return true;
            case JOptionPane.NO_OPTION:
                return false;

            case JOptionPane.CLOSED_OPTION:
                return false;
            default:
                break;
        }
        return false;
    }
}
