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
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.JefeFinanzas;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.generarNumeroRandom;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author skiva
 */
public class ControladorRegistroFactura {

    private DAOEncargadoComprasImpl dao;
    private Factura invoice;

    public ControladorRegistroFactura() {
        this.dao = new DAOEncargadoComprasImpl();
    }

    public void registrarFactura(int idProveedor, Date fechaRegistro, Date fechaVencimiento, String direccion, 
            Double montoTotal, Double montoPagado, Double montoPendiente) throws Exception {

        
        invoice = new Factura(0, idProveedor, fechaRegistro, fechaVencimiento, direccion, montoTotal, montoPagado, montoPendiente);
        dao.registrarFactura(invoice);
        
    }

    public void editarFactura(int idFactura, int idProveedor, Date fechaRegistro, Date fechaVencimiento, String direccion, 
            Double montoTotal, Double montoPagado, Double montoPendiente) throws Exception {
        
        invoice = new Factura(idFactura, idProveedor, fechaRegistro, fechaVencimiento, direccion, montoTotal, montoPagado, montoPendiente);
        dao.modificarFactura(invoice);
        
    }


    public boolean confirmarDatosaasSsdads(String nombre, String direccion, String telefono, Double lineaCredito) {
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
