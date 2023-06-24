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
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.generarNumeroRandom;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

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

    public void registrarFacturaConProductos(List<Producto> productosTemporales, int idProveedor, Date fechaRegistro, Date fechaVencimiento, String direccion,
            Double montoTotal, Double montoPagado, Double montoPendiente) throws Exception {

        try {
            // Registrar la factura
            invoice = new Factura(0, idProveedor, fechaRegistro, fechaVencimiento, direccion, montoTotal, montoPagado, montoPendiente);
            dao.registrarFactura(invoice);

            // Obtener la ID de la factura registrada
            int idFactura = dao.obtenerUltimaFacturaRegistrada();

            // Registrar los productos asociados a la factura
            for (Producto producto : productosTemporales) {
                producto.setIdFactura(idFactura);
                dao.registrarProducto(producto);
            }

            System.out.println("Factura y productos registrados exitosamente.");
        } catch (Exception e) {
            throw new Exception("Error al registrar la factura con sus respectuvos productos", e);
        }

    }

    public void editarFacturaConProductos(List<Producto> productosTemporales, int idFactura, int idProveedor, Date fechaRegistro, Date fechaVencimiento, String direccion,
            Double montoTotal, Double montoPagado, Double montoPendiente) throws Exception {

        try {
            // Registrar la factura
            invoice = new Factura(idFactura, idProveedor, fechaRegistro, fechaVencimiento, direccion, montoTotal, montoPagado, montoPendiente);
            dao.modificarFactura(invoice);

            // Registrar o editar los productos asociados a la factura
            for (Producto producto : productosTemporales) {
                producto.setIdFactura(idFactura);
                if (producto.getIdProducto()!= 0) {
                    dao.modificarProducto(producto);
                } else {
                    dao.registrarProducto(producto);
                }
            }

            System.out.println("Factura y productos registrados exitosamente.");
        } catch (Exception e) {
            throw new Exception("Error al registrar la factura con sus respectuvos productos", e);
        }

    }

    public void llenarComboBoxProveedores(JComboBox<String> comboBox) {

        JComboBox<String> comboBoxProveedores = new JComboBox<>();

        try {
            List<Proveedor> proveedores = dao.obtenerListaProveedores("");

            for (Proveedor proveedor : proveedores) {
                comboBox.addItem(proveedor.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //comboBox = comboBoxProveedores;
    }

    public String buscarNombreProveedorPorFactura(int idFactura) throws Exception {
        try {
            String nombreProveedorPorFactura = dao.buscarNombreProveedorPorFactura(idFactura);
            return nombreProveedorPorFactura;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int buscarIdProveedorPorNombre(String nombreProveedor) throws Exception {
        try {
            int buscarIdProveedorPorNombre = dao.buscarIdProveedorPorNombre(nombreProveedor);
            return buscarIdProveedorPorNombre;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // aqui implementar lista de products temporales
    public DefaultTableModel agregarProductosTabla(JTable table, List<Producto> productosTemporales) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IdProducto");
        model.addColumn("IdFactura");
        model.addColumn("Nombre");
        model.addColumn("Descripcion");
        model.addColumn("Cantidad");
        model.addColumn("Precio Unitario");

        productosTemporales.forEach((u) -> model.addRow(new Object[]{u.getIdProducto(), u.getIdFactura(), u.getNombre(), u.getDescripcion(), u.getCantidad(), u.getPrecioUnitario()}));
        return model;
    }

    public void agregarProductosArrayProductos(List<Producto> productosTemporales, int facturaId) throws Exception {
        productosTemporales = dao.obtenerListaProductosporFacturaId(facturaId);
    }

    public void agregarNuevosProductosArrayProductos(List<Producto> productosTemporales, int productoId, int facturaId,
            String nombreProd, String descripcionProd, String cantidadProd, String precioUnitarioProd) throws Exception {

        int idProducto = productoId;
        int idFactura = facturaId;
        String nombre = nombreProd;
        String descripcion = descripcionProd;
        int cantidad = Integer.parseInt(cantidadProd);
        double precioUnitario = Double.parseDouble(precioUnitarioProd);
        double subtotal = (cantidad * precioUnitario);

        Producto producto = new Producto(idProducto, idFactura, nombre, descripcion, cantidad, precioUnitario, subtotal);
        productosTemporales.add(producto);

    }

    public Producto buscarProductoPorId(List<Producto> productosTemporales, int idProducto) {
        for (Producto producto : productosTemporales) {
            if (producto.getIdProducto() == idProducto) {
                return producto;
            }
        }
        return null; // Si no se encuentra el producto con el ID especificado
    }

    public void eliminarProductoPorId(List<Producto> productosTemporales, int idProducto) {
        Iterator<Producto> iterator = productosTemporales.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getIdProducto() == idProducto) {
                iterator.remove();
                return;
            }
        }
    }

    public double calcularMontoTotal(List<Producto> productosTemporales) {
        double montoTotal = 0.0;
        for (Producto producto : productosTemporales) {
            montoTotal += producto.getSubtotal();
        }
        return montoTotal;
    }

    public boolean validarFechas(Date fechaRegistro, Date fechaVencimiento) {  // valida que la fecha registro sea menor a la fecha vencimiento
        return fechaRegistro.before(fechaVencimiento);
    }

    public boolean confirmarDatosProducto(String nombre, String descripcion, String cantidad, String precioUnitario) {
        String message;
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos del Proveedor *****";
        message += "\nNombre: " + nombre;
        message += "\nDescripcion: " + descripcion;
        message += "\ncantidad: " + cantidad;
        message += "\nprecio Unitario: " + precioUnitario;
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

    public boolean confirmarDatosProveedores(List<Producto> productosTemporales, String proveedor, String fechaRegistro, String fechaVencimiento, String montoTotal, String montoPagado, String montoPendiente) {
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
