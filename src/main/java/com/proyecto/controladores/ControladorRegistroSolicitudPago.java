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
import com.proyecto.entidades.Canje;
import com.proyecto.entidades.CategoriaProducto;
import com.proyecto.entidades.Cheque;
import com.proyecto.entidades.CuentaBancaria;
import com.proyecto.entidades.EncargadoCompras;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Inventario;
import com.proyecto.entidades.JefeFinanzas;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.SolicitudPago;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.generarNumeroRandom;
import static com.proyecto.utils.Utils.obtenerFechaActual;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorRegistroSolicitudPago {

    private DAOTesoreroImpl dao;
    private Factura invoice;
    private Cheque check;
    private Canje exchange;

    public ControladorRegistroSolicitudPago() {
        this.dao = new DAOTesoreroImpl();
    }

    public Cheque registrarRegistroCheque(Factura invoice, double montoCheque, String nombreCuentaBancaria) throws Exception {

        CuentaBancaria cuentaBancaria = dao.obtenerCuentaBancariaPorNombre(nombreCuentaBancaria);

        String fechaActual = obtenerFechaActual();
        // Convierte el String a LocalDate
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaRegistroDate = formato.parse(fechaActual);

        String estadoCheque = "Emitido";

        check = new Cheque(0, invoice, montoCheque, cuentaBancaria, fechaRegistroDate, estadoCheque);
        int idChequeRegistrado = dao.registrarCheque(check);

        Cheque chequeRegistrado = dao.obtenerChequePorId(idChequeRegistrado);

        return chequeRegistrado;
    }

    public Canje registrarRegistroCanje(Factura invoice, Producto productoSelecionado, String detalleCanje, String categoriaProductoSeleccionado,
            String nombreproductoSeleccionado, int cantidadProductosSeleccionado) throws Exception {

        String fechaActual = obtenerFechaActual();
        // Convierte el String a LocalDate
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaRegistroDate = formato.parse(fechaActual);

        String estadoCanje = "Emitido";

        Double equivalenteDinero = (productoSelecionado.getPrecioUnitario() * cantidadProductosSeleccionado);

        exchange = new Canje(0, invoice, detalleCanje, productoSelecionado, cantidadProductosSeleccionado, equivalenteDinero, fechaRegistroDate, estadoCanje);
        int idCanjeRegistrado = dao.registrarCanje(exchange);

        Canje canjeRegistrado = dao.obtenerCanjePorId(idCanjeRegistrado);

        return canjeRegistrado;
    }

    public void registrarSolicitud(Factura invoice, Cheque check, Canje exchange) throws Exception {
        SolicitudPago solicitudPago = null;
        String metodoPago;
        String estadoSolicitud;

        String fechaActual = obtenerFechaActual();
        // Convierte el String a LocalDate
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaRegistroDate = formato.parse(fechaActual);

        if (check != null) { //Registro por cheque
            metodoPago = "Cheque";
            estadoSolicitud = "Pendiente";
            solicitudPago = new SolicitudPago(0, invoice, metodoPago, check, null, estadoSolicitud, fechaRegistroDate);

        } else if (exchange != null) { // Registro por canje
            metodoPago = "Canje";
            estadoSolicitud = "Pendiente";

            solicitudPago = new SolicitudPago(0, invoice, metodoPago, null, exchange, estadoSolicitud, fechaRegistroDate);
        }

        int idSolicitudPagoRegistrada = dao.registrarSolicitudPago(solicitudPago);

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

    public void llenarComboBoxCategoriasProducto(JComboBox<String> comboBox) {

        try {
            List<CategoriaProducto> categoriasProducto = dao.obtenerListaCategoriasProducto();

            for (CategoriaProducto categoriaProducto : categoriasProducto) {
                comboBox.addItem(categoriaProducto.getNombreCategoria());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mostrarSaldoBanco(String nombreBanco, JTextField saldoActualBancoField) {

        try {
            CuentaBancaria cuentaBancaria = dao.obtenerCuentaBancariaPorNombre(nombreBanco);

            saldoActualBancoField.setText(Double.toString(cuentaBancaria.getSaldoActual()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void mostrarCantidadDispinibleProducto(String nombreProducto, String nombreCategoriaProducto, JLabel cantidadProductosInvLabel) {

        int cantidadDispinibleProducto = cantidadTotalDispinibleProducto(nombreProducto, nombreCategoriaProducto);
        
        cantidadProductosInvLabel.setText("Cantidad Productos: (max " + cantidadDispinibleProducto + ")");
    }

    public void agregarProductosCboxFildrados2(String categoriaProducto, JComboBox<String> comboBox) {

        try {
            List<Producto> productosFiltrados = dao.obtenerListaProductosDisponiblesInventarioPorNombreCategoria(categoriaProducto);

            comboBox.removeAllItems();
            for (Producto producto : productosFiltrados) {
                comboBox.addItem(producto.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void agregarProductosCboxFildrados(String categoriaProducto, JComboBox<Producto> productComboBox) {

        try {
            List<Producto> productosFiltrados = dao.obtenerListaProductosDisponiblesInventarioPorNombreCategoria(categoriaProducto);
            productComboBox.removeAllItems();

            // Crear un DefaultComboBoxModel con la lista de productos
            DefaultComboBoxModel<Producto> model = new DefaultComboBoxModel<>(productosFiltrados.toArray(new Producto[0]));
            productComboBox.setModel(model);

            // Configurar el ListCellRenderer para mostrar solo el nombre del producto
            productComboBox.setRenderer(new ListCellRenderer<Producto>() {
                public Component getListCellRendererComponent(JList<? extends Producto> list, Producto value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    JLabel label = new JLabel();
                    if (value != null) {
                        label.setText(value.getNombre()); // Mostrar el nombre del producto si no es nulo
                    } else {
                        label.setText("Sin productos"); // Mostrar un texto predeterminado si es nulo
                    }
                    if (isSelected) {
                        label.setBackground(list.getSelectionBackground());
                        label.setForeground(list.getSelectionForeground());
                    } else {
                        label.setBackground(list.getBackground());
                        label.setForeground(list.getForeground());
                    }
                    label.setOpaque(true);
                    return label;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int cantidadTotalDispinibleProducto(String nombreProducto, String categoriaProducto) {
        try {
            List<Producto> productosFiltrados = dao.obtenerListaProductosDisponiblesInventarioPorNombreCategoria(categoriaProducto);
            for (Producto producto : productosFiltrados) {
                if (producto.getNombre().equals(nombreProducto)) {
                    Inventario inventario = dao.obtenerInventarioPorIdProducto(producto.getIdProducto());
                    return inventario.getCantidadProducto();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int cantidadTotalDispinibleProducto2 (int idProducto, String categoriaProducto) {
        try {
            List<Producto> productosFiltrados = dao.obtenerListaProductosDisponiblesInventarioPorNombreCategoria(categoriaProducto);
            for (Producto producto : productosFiltrados) {
                if (producto.getIdProducto() == idProducto) {
                    Inventario inventario = dao.obtenerInventarioPorIdProducto(producto.getIdProducto());
                    return inventario.getCantidadProducto();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public boolean confirmarDatosSolicitudPagoPorCheque(String idFactura, String proveedor, String fechaRegistro,
            String fechaVencimiento, String montoTotal, String montoPagado, String montoPendiente,
            String cuentaBancaria, String saldoCuenta, String montoPagar) {
        String message;
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos de la solicitud *****";
        message += "\nIDFactura: " + idFactura;
        message += "\nProveedor a pagar: " + proveedor;
        message += "\nFecha Registro: " + fechaRegistro;
        message += "\nFecha Vencimiento: " + fechaVencimiento;
        message += "\nMonto Total: " + montoTotal;
        message += "\nMonto Pagado: " + montoPagado;
        message += "\nMonto Pendiente: " + montoPendiente;
        message += "\n";
        message += "\nMetodo de Pago: Cheque";
        message += "\nCuenta bancaria: " + cuentaBancaria;
        message += "\nSaldo cuenta bancaria: " + saldoCuenta;
        message += "\nMonto a pagar: " + montoPagar;
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

    public boolean confirmarDatosSolicitudPagoPorCanje(String idFactura, String proveedor, String fechaRegistro,
            String fechaVencimiento, String montoTotal, String montoPagado, String montoPendiente,
            String detalleCanje, String categoriaProductoSeleccionado, String productoSeleccionado, String cantidadProductosSeleccionado, double montoDineroEquivalente) {
        String message;
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos de la solicitud *****";
        message += "\nIDFactura: " + idFactura;
        message += "\nProveedor a pagar: " + proveedor;
        message += "\nFecha Registro: " + fechaRegistro;
        message += "\nFecha Vencimiento: " + fechaVencimiento;
        message += "\nMonto Total: " + montoTotal;
        message += "\nMonto Pagado: " + montoPagado;
        message += "\nMonto Pendiente: " + montoPendiente;
        message += "\n";
        message += "\nMetodo de Pago: Canje";
        message += "\nDetalle de canje: " + detalleCanje;
        message += "\ncategoria Producto: " + categoriaProductoSeleccionado;
        message += "\nProducto: " + productoSeleccionado;
        message += "\nCantidad Productos a canjear: " + cantidadProductosSeleccionado;
        message += "\nMonto de dinero equivalente: " + montoDineroEquivalente;
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
