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
import com.proyecto.entidades.MovimientoBancario;
import com.proyecto.entidades.MovimientoInventario;
import com.proyecto.entidades.PagoFactura;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.SolicitudPago;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.generarNumeroRandom;
import static com.proyecto.utils.Utils.obtenerFechaActual;
import static com.proyecto.utils.Utils.obtenerFechaActualDate;
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
public class ControladorRegistroPagoFactura {

    private DAOTesoreroImpl dao;
    private Factura invoice;
    private Cheque check;
    private Canje exchange;

    public ControladorRegistroPagoFactura() {
        this.dao = new DAOTesoreroImpl();
    }

    public Cheque registrarRegistroCheque(Factura invoice, double montoCheque, String nombreCuentaBancaria) throws Exception {

        CuentaBancaria cuentaBancaria = dao.obtenerCuentaBancariaPorNombre(nombreCuentaBancaria);

        Date fechaRegistroDate = obtenerFechaActualDate();

        String estadoCheque = "Emitido";

        check = new Cheque(0, invoice, montoCheque, cuentaBancaria, fechaRegistroDate, estadoCheque);
        int idChequeRegistrado = dao.registrarCheque(check);

        Cheque chequeRegistrado = dao.obtenerChequePorId(idChequeRegistrado);

        return chequeRegistrado;
    }

    public Canje registrarRegistroCanje(Factura invoice, Producto productoSelecionado, String detalleCanje, String categoriaProductoSeleccionado,
            String nombreproductoSeleccionado, int cantidadProductosSeleccionado) throws Exception {

        Date fechaRegistroDate = obtenerFechaActualDate();

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

        Date fechaRegistroDate = obtenerFechaActualDate();

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

    public void registrarPagoFacturaSolicitud(Factura invoice, SolicitudPago paymentRequest, Cheque check, Canje exchange) throws Exception {
        PagoFactura pagoFactura = null;
        String tipoPago; // Manual

        Date fechaRegistroDate = obtenerFechaActualDate();

        if (check != null) { //Registro por cheque
            tipoPago = "Manual";
            double montoPago = check.getMontoCheque();
            pagoFactura = new PagoFactura(0, invoice, tipoPago, paymentRequest, null, montoPago, fechaRegistroDate);

            //Agregar monto pagado a la factura
            try {
                actualizarMontoPagadoFacturaPorPagoFactura(check);
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, "No se puede ejecutar el pago de la factura porque el monto a pagar supera el monto total de la factura.\n",
                        "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            //Descuesta el monto del cheque a la cuenta bancaria y actualiza sus datos
            descontarSaldoCuentaBancariaPorPagoFactura(check);

            //Registra el movimiento generado al descontar el monto de la cuenta bancaria
            registrarMovimientoBancarioPorPagoFactura(check);

            //Actualizar estado de cheque a Cobrado
            actualizarEstadoChequePorPagoFactura(check);

            //Actualizar monto cheque de los pagos programados de respaldo
            
        } else if (exchange != null) { // Registro por canje
            tipoPago = "Manual";
            double equivalenteDinero = exchange.getEquivalenteDinero();
            pagoFactura = new PagoFactura(0, invoice, tipoPago, paymentRequest, null, equivalenteDinero, fechaRegistroDate);

            //Agregar monto pagado a la factura
            try {
                actualizarMontoPagadoFacturaPorPagoFactura(exchange);
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, "No se puede ejecutar el pago de la factura porque el dinero equivalente a pagar supera el monto total de la factura.\n",
                        "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            //Descuenta la cantidad de productos en el inventario
            descontarProductoInventarioPorPagoFactura(exchange);

            //Registra el movimiento de inventario
            registrarMovimientoInventarioPorPagoFactura(exchange);

            //Actualizar estado de canje a Cobrado
            actualizarEstadoCanjePorPagoFactura(exchange);
        }

        int idPagoFacturaRegistrado = dao.registrarPagoFactura(pagoFactura);

    }

    public void actualizarMontoPagadoFacturaPorPagoFactura(Cheque check) throws Exception {

        Factura factura = check.getFactura();
        double montoAPagar = check.getMontoCheque();

        double montoTotal = factura.getMontoTotal();
        double montoPagadoAntes = factura.getMontoPagado();
        double montoPendienteAntes = factura.getMontoPendiente();

        double montoPagadoDespues = (montoPagadoAntes + montoAPagar);
        double montoPendienteDespues = (montoTotal - montoPagadoDespues);

        //VALIDAR QUE EL montoPagadoDespues no supere el  montoTotal de la factua, si lo hace detener en ese punto la funcion y mostrar ventana de alerta.
        if (montoPagadoDespues > montoTotal) {
            throw new Exception("No se puede ejecutar el pago de la factura porque el monto a pagar supera el monto total de la factura.");
        }

        dao.modificarMontosPagadosFacturaPorId(factura.getIdFactura(), montoPagadoDespues, montoPendienteDespues);
    }

    public void actualizarMontoPagadoFacturaPorPagoFactura(Canje exchange) throws Exception {

        Factura factura = exchange.getFactura();
        double equivalenteDinero = exchange.getEquivalenteDinero();

        double montoTotal = factura.getMontoTotal();
        double montoPagadoAntes = factura.getMontoPagado();
        double montoPendienteAntes = factura.getMontoPendiente();

        double montoPagadoDespues = (montoPagadoAntes + equivalenteDinero);
        double montoPendienteDespues = (montoTotal - montoPagadoDespues);

        //VALIDAR QUE EL montoPagadoDespues no supere el  montoTotal de la factua, si lo hace detener en ese punto la funcion y mostrar ventana de alerta.
        if (montoPagadoDespues > montoTotal) {
            throw new Exception("No se puede ejecutar el pago de la factura porque el monto a pagar supera el monto total de la factura.");
        }

        dao.modificarMontosPagadosFacturaPorId(factura.getIdFactura(), montoPagadoDespues, montoPendienteDespues);
    }

    public void registrarMovimientoBancarioPorPagoFactura(Cheque check) throws Exception {
        MovimientoBancario movimientoBancario = null;
        //PagoFactura pagoFactura = dao.obtenerPagoFacturaPorId(idPagoFactura);

        String tipoMovimiento = "Egreso"; // Manual

        Date fechaRegistroDate = obtenerFechaActualDate();

        CuentaBancaria cuentaBancaria = check.getCuentaBancaria();
        double montoMovimiento = check.getMontoCheque();

        movimientoBancario = new MovimientoBancario(0, cuentaBancaria, tipoMovimiento, montoMovimiento, fechaRegistroDate);

        int idMovimientoBancarioRegistrado = dao.registrarMovimientoBancario(movimientoBancario);
    }

    public void descontarSaldoCuentaBancariaPorPagoFactura(Cheque check) throws Exception {

        CuentaBancaria cuentaBancaria = check.getCuentaBancaria();
        double saldoActualAntes = cuentaBancaria.getSaldoActual();
        double saldoPrevioAntes = cuentaBancaria.getSaldoPrevio();

        double saldoActualDespues = (saldoActualAntes - check.getMontoCheque());
        double saldoPrevioDespues = saldoActualAntes;

        dao.modificarSaldosCuentaBancariaPorId(cuentaBancaria.getIdCuentaBancaria(), saldoActualDespues, saldoPrevioDespues);
    }

    public void actualizarEstadoChequePorPagoFactura(Cheque check) throws Exception {

        String nuevoEstadoCheque = "Cobrado";

        dao.modificarEstadoChequePorId(check.getIdCheque(), nuevoEstadoCheque);
    }

    public void descontarProductoInventarioPorPagoFactura(Canje exchange) throws Exception {

        Producto productoCanje = exchange.getProductoCanje();

        Inventario inventario = dao.obtenerInventarioPorIdProducto(productoCanje.getIdProducto());
        int cantidadProductosAntes = inventario.getCantidadProducto();

        int cantidadProductosDespues = (cantidadProductosAntes - exchange.getCantidadProducto());

        dao.modificarCantidadProductosInventarioPorIdProducto(productoCanje.getIdProducto(), cantidadProductosDespues);
    }

    public void registrarMovimientoInventarioPorPagoFactura(Canje exchange) throws Exception {
        MovimientoInventario movimientoInventario = null;

        Producto productoCanje = exchange.getProductoCanje();

        Inventario inventario = dao.obtenerInventarioPorIdProducto(productoCanje.getIdProducto());

        String tipoMovimiento = "Egreso";

        Date fechaRegistroDate = obtenerFechaActualDate();
        int cantidadProductos = exchange.getCantidadProducto();

        movimientoInventario = new MovimientoInventario(0, inventario, productoCanje,
                cantidadProductos, tipoMovimiento, fechaRegistroDate);

        int idMovimientoInventarioRegistrado = dao.registrarMovimientoInventario(movimientoInventario);
    }

    public void actualizarEstadoCanjePorPagoFactura(Canje exchange) throws Exception {

        String nuevoEstadoCanje = "Cobrado";

        dao.modificarEstadoCanjePorId(exchange.getIdCanje(), nuevoEstadoCanje);
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

    public void mostrarDatosCheque(Cheque check, JComboBox<String> bancosCbox, JTextField saldoActualBanco, JTextField montoChequeField) {

        try {

            bancosCbox.removeAllItems();

            CuentaBancaria cuentaBancaria = dao.obtenerCuentaBancariaPorId(check.getCuentaBancaria().getIdCuentaBancaria());
            bancosCbox.addItem(cuentaBancaria.getNombreBanco());

            saldoActualBanco.setText(Double.toString(cuentaBancaria.getSaldoActual()));

            montoChequeField.setText(Double.toString(check.getMontoCheque()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void mostrarDatosCanje(Canje exchange, JTextField detalleCanjeField, JComboBox<String> categoriaProductoCBox, JComboBox<String> productoCBox, JTextField cantidadProductosField) {

        try {

            categoriaProductoCBox.removeAllItems();
            productoCBox.removeAllItems();

            Producto producto = dao.obtenerProductoPorId(exchange.getProductoCanje().getIdProducto());
            String categoriaProducto = producto.getCategoriaProducto().getNombreCategoria();

            detalleCanjeField.setText(exchange.getDetalleCanje());
            categoriaProductoCBox.addItem(categoriaProducto);
            productoCBox.addItem(producto.getNombre());
            cantidadProductosField.setText(Integer.toString(exchange.getCantidadProducto()));

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
            String detalleCanje, String categoriaProductoSeleccionado, String productoSeleccionado, String cantidadProductosSeleccionado) {
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
