/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.baseDatos.consultas.DAOTesoreroImpl;
import com.proyecto.entidades.Administrador;
import com.proyecto.entidades.Almacenero;
import com.proyecto.entidades.EncargadoCompras;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.JefeFinanzas;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.generarNumeroRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorRegistroSolicitudPago {

    private DAOTesoreroImpl dao;
    private Factura invoice;

    public ControladorRegistroSolicitudPago() {
        this.dao = new DAOTesoreroImpl();
    }

    public void mostrarDatosFactura(int facturaId, JTextField idFacturaField, JTextField nombreProveedor, JTextField fechaRegistro,
            JTextField fechaVencimiento, JTextField descripcion, JTextField montoPendiente, JTextField montoPagado, JTextField montoTotal) {

        try {
            Factura factura = dao.obtenerFacturaPorId(facturaId);

            String nombreProveedorPorFacturaId = dao.buscarNombreProveedorPorFactura(facturaId);
            
            idFacturaField.setText(Integer.toString(factura.getIdFactura()));
            
            nombreProveedor.setText(nombreProveedorPorFacturaId);

            // Crea el formato deseado para la fecha
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            // Convierte la fecha a String
            String fechaRegistroString = formato.format(factura.getFechaRegistro());
            String fechaVencimientoString = formato.format(factura.getFechaVencimiento());

            fechaRegistro.setText(fechaRegistroString);
            fechaVencimiento.setText(fechaVencimientoString);
            descripcion.setText(factura.getDescripcion());
            montoPendiente.setText(Double.toString(factura.getMontoPendiente()));
            montoPagado.setText(Double.toString(factura.getMontoPagado()));
            montoTotal.setText(Double.toString(factura.getMontoTotal()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    
    
    

    public boolean confirmarDatosFactura(List<Producto> productosTemporales, String proveedor, String fechaRegistro, String fechaVencimiento, String montoTotal, String montoPagado, String montoPendiente) {
        String message;
        int cantidadProductos = productosTemporales.size();
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos del Proveedor *****";
        message += "\nProveedor: " + proveedor;
        message += "\nFecha Registro: " + fechaRegistro;
        message += "\nFecha Vencimiento: " + fechaVencimiento;
        message += "\nMonto Total: " + montoTotal;
        message += "\nMonto Pagado: " + montoPagado;
        message += "\nMonto Pendiente: " + montoPendiente;
        message += "\nCantidad total de productos: " + cantidadProductos;
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
