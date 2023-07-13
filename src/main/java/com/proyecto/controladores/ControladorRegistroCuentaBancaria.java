/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.baseDatos.consultas.DAOJefeFinanzasImpl;
import com.proyecto.entidades.Administrador;
import com.proyecto.entidades.Almacenero;
import com.proyecto.entidades.CuentaBancaria;
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
public class ControladorRegistroCuentaBancaria {

    private DAOJefeFinanzasImpl dao;
    private CuentaBancaria bankAccount;

    public ControladorRegistroCuentaBancaria() {
        this.dao = new DAOJefeFinanzasImpl();
    }

    public void registrarCuentaBancaria(String nombreCuenta, String tipoCuenta, double saldoInicial) throws Exception {

        bankAccount = new CuentaBancaria(0, nombreCuenta, tipoCuenta, saldoInicial, saldoInicial);
        dao.registrarCuentaBancaria(bankAccount);

    }

    public boolean validarNombreCuentaBancariaEnUso(String nombreCuenta) throws Exception {

        return dao.existeEntidadBancaria(nombreCuenta);

    }

    /*
    public void editarProveedor(int idProveedor, String nombre, String direccion, String telefono, Double lineaCredito) throws Exception {
        
        supplier = new Proveedor(idProveedor, nombre, direccion, telefono, lineaCredito);
        dao.modificarProveedor(supplier);
        
    }*/
    public boolean confirmarDatosCuentaBancaria(String nombreCuenta, String tipoCuenta, String saldoInicial) {
        String message;
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos de la cuenta bancaria *****";
        message += "\nNombre Entidad Bancaria: " + nombreCuenta;
        message += "\nTipo de cuenta bancaria: " + tipoCuenta;
        message += "\nSaldo Inicial: " + saldoInicial;
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
