/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import com.proyecto.baseDatos.consultas.DAOTesoreroImpl;
import com.proyecto.entidades.Administrador;
import com.proyecto.entidades.Almacenero;
import com.proyecto.entidades.CuentaBancaria;
import com.proyecto.entidades.EncargadoCompras;
import com.proyecto.entidades.JefeFinanzas;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.generarNumeroRandom;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author skiva
 */
public class ControladorDetalleBancos {

    private DAOTesoreroImpl dao;
    private Usuario user;

    public ControladorDetalleBancos() {
        this.dao = new DAOTesoreroImpl();
    }

    public void llenarComboBoxCuentasBancarias(JComboBox<String> comboBox) {

        try {
            List<CuentaBancaria> cuentasBancarias = dao.obtenerListaCuentasBancarias();

            for (CuentaBancaria cuentaBancaria : cuentasBancarias) {
                comboBox.addItem(cuentaBancaria.getNombreBanco());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mostrarDatosBanco(String nombreBanco, JLabel saldoActual, JLabel saldoPrevio) {
        
        try {
            CuentaBancaria cuentaBancaria = dao.obtenerCuentaBancariaPorNombre(nombreBanco);
            
            saldoActual.setText(Double.toString(cuentaBancaria.getSaldoActual()));
            saldoPrevio.setText(Double.toString(cuentaBancaria.getSaldoPrevio()));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }






}
