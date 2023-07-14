/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import com.proyecto.baseDatos.consultas.DAOAlmaceneroImpl;
import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.baseDatos.consultas.DAOJefeFinanzasImpl;
import com.proyecto.entidades.Administrador;
import com.proyecto.entidades.Almacenero;
import com.proyecto.entidades.CategoriaProducto;
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
public class ControladorRegistroCategoriaProducto {

    private DAOAlmaceneroImpl dao;
    private CategoriaProducto productCategory;

    public ControladorRegistroCategoriaProducto() {
        this.dao = new DAOAlmaceneroImpl();
    }

    public void registrarCategoriaProducto(String nombreCategoria, String descripcionCategoria) throws Exception {

        productCategory = new CategoriaProducto(0, nombreCategoria, descripcionCategoria);
        dao.registrarCategoriaProducto(productCategory);

    }

    public boolean validarNombreCategoriaProductoEnUso(String nombreCategoria) throws Exception {

        return dao.existeCategoriaProducto(nombreCategoria);

    }

    /*
    public void editarProveedor(int idProveedor, String nombre, String direccion, String telefono, Double lineaCredito) throws Exception {
        
        supplier = new Proveedor(idProveedor, nombre, direccion, telefono, lineaCredito);
        dao.modificarProveedor(supplier);
        
    }*/
    public boolean confirmarDatosCategoriaProducto(String nombreCategoria, String descripcionCategoria) {
        String message;
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos de la categoría producto *****";
        message += "\nNombre de la Categoría: " + nombreCategoria;
        message += "\nDescripción: " + descripcionCategoria;
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
