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
import com.proyecto.entidades.MovimientoBancario;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import com.proyecto.utils.Utils;
import static com.proyecto.utils.Utils.generarNumeroRandom;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author skiva
 */
public class ControladorRegistroIngresoBancario {

    private DAOJefeFinanzasImpl dao;
    private CuentaBancaria bankAccount;
    private MovimientoBancario bankMovement;

    public ControladorRegistroIngresoBancario() {
        this.dao = new DAOJefeFinanzasImpl();
    }

    public void registrarIngresoBancario(CuentaBancaria cuentaBancaria, double montoAIngresar) throws Exception {
        
        //1) Agregar fondo a cuenta bancaria
        int idCuentaBancaria = cuentaBancaria.getIdCuentaBancaria();
        double saldoActualAntes = cuentaBancaria.getSaldoActual();
        double saldoPrevioAntes = cuentaBancaria.getSaldoPrevio();
        
        double saldoActualDespues = (saldoActualAntes + montoAIngresar);
        double saldoPrevioDespues = saldoActualAntes;
        
        dao.modificarSaldosCuentaBancariaPorId(idCuentaBancaria, saldoActualDespues, saldoPrevioDespues);
        
        //2) Registrar movimiento cuenta bancaria
        String tipoMovimiento = "Ingreso";
        Date fechaMovimiento = Utils.obtenerFechaActualDate();
        bankMovement = new MovimientoBancario(0, cuentaBancaria, tipoMovimiento, montoAIngresar, fechaMovimiento);
        
        dao.registrarMovimientoBancario(bankMovement);

    }


    /*
    public void editarProveedor(int idProveedor, String nombre, String direccion, String telefono, Double lineaCredito) throws Exception {
        
        supplier = new Proveedor(idProveedor, nombre, direccion, telefono, lineaCredito);
        dao.modificarProveedor(supplier);
        
    }*/
    public boolean confirmarDatosRegistroIngresoBancario(CuentaBancaria cuentaBancaria, double montoAIngresar) {
        String message;
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos del ingreso bancario *****";
        message += "\nEntidad Bancaria seleccionada: " + cuentaBancaria.getNombreBanco();
        message += "\nTipo de cuenta bancaria: " + cuentaBancaria.getTipoCuentaBancaria();
        message += "\nSaldo Actual: " + cuentaBancaria.getSaldoActual();
        message += "\n";
        message += "\nMonto a ingresar: " + montoAIngresar;
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
